package com.wang.mvvmcore.crash;

import android.os.Build;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wang.mvvmcore.BuildConfig;
import com.wang.mvvmcore.utils.common.CoreLogUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static com.wang.mvvmcore.constant.FileConstant.DIR_CRASH;

/**
 * author: wtg
 * date:2020/3/31 0031
 * desc: 异常信息收集
 */
public class CrashHandlerUtils implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandlerUtils";
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandlerUtils INSTANCE;
    //用来存储设备信息和异常信息
    private Map<String, Object> infos = new LinkedHashMap<>();
    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private String crashTip = "很抱歉,程序出现异常,即将退出.";
    private UploadListener uploadListener;

    private CrashHandlerUtils() {

    }

    /**
     * 单例
     *
     * @return CrashHandlerUtils
     */
    public static CrashHandlerUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (CrashHandlerUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CrashHandlerUtils();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 设置crash目录
     *
     * @param crashDir 闪退目录
     */
    public CrashHandlerUtils setCrashName(String crashDir) {
        DIR_CRASH = crashDir;
        return INSTANCE;
    }

    public CrashHandlerUtils setUploadListener(UploadListener uploadListener) {
        this.uploadListener = uploadListener;
        return INSTANCE;
    }

    /**
     * 必须最后调用
     */
    public void init() {
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public String getCrashTip() {
        return crashTip;
    }

    public CrashHandlerUtils setCrashTip(String crashTip) {
        this.crashTip = crashTip;
        return INSTANCE;
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                CoreLogUtils.logE("error : ", e.getMessage());
                e.printStackTrace();
            }
            //退出程序
            //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            AppUtils.exitApp();
            //从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param throwable 异常
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(final Throwable throwable) {
        if (throwable == null) {
            return false;
        }
        //使用Toast来显示异常信息
        ThreadUtils.runOnUiThread(()->{
            throwable.printStackTrace();
            ToastUtils.showShort(getCrashTip());
        });
        //收集设备参数信息
        collectDeviceInfo();
        //保存日志文件
        saveCrashInfo2File(throwable);
        return true;
    }

    /**
     * 收集设备参数信息
     */
    private void collectDeviceInfo() {
        infos.put("app名称", AppUtils.getAppName());
        infos.put("versionCode", AppUtils.getAppVersionCode());
        infos.put("versionName", AppUtils.getAppVersionName());
        infos.put("sdk", Build.VERSION.SDK_INT);
        infos.put("androidVersion", Build.VERSION.RELEASE);
        StringBuilder builderCpu = new StringBuilder();
        for (String supportedAbi : Build.SUPPORTED_ABIS) {
            builderCpu.append(supportedAbi);
            builderCpu.append(",");
        }
        infos.put("手机设备支持的cpu类型", builderCpu);
        infos.put("cpu_abi", Build.CPU_ABI);
        infos.put("cpu_abi2", Build.CPU_ABI2);
        infos.put("id", Build.ID);
        infos.put("device", Build.DEVICE);
        infos.put("品牌", Build.BRAND);
        infos.put("型号", Build.MODEL);
        infos.put("时间", formatter.format(new Date()));
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex 异常
     */
    private void saveCrashInfo2File(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            sb.append(key).append("=").append(value).append("\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        CoreLogUtils.logE(sb.toString());
        if (BuildConfig.DEBUG) {
            return;
        }
        /*
        这个 crashInfo 就是我们收集到的所有信息，可以做一个异常上报的接口用来提交用户的crash信息
         */
        String crashInfo = sb.toString();
        String name = formatter.format(new Date());
        //写到sd卡上
        if (SDCardUtils.isSDCardEnableByEnvironment()) {
            FileUtils.createOrExistsDir(DIR_CRASH);
            String fileName = name + ".txt";
            File crashFile = new File(DIR_CRASH, fileName);
            FileUtils.createOrExistsFile(crashFile);
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(crashFile, "rwd");
                randomAccessFile.seek(crashFile.length());
                randomAccessFile.write(crashInfo.getBytes());
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (uploadListener != null) {
                uploadListener.upload(crashFile, crashInfo);
            }
        }

    }

    public interface UploadListener {
        /**
         * 上传crash信息 实现需要重写该方法
         *
         * @param crashFile 保存的文件
         * @param crashInfo crash信息
         */
        void upload(File crashFile, String crashInfo);
    }

}
