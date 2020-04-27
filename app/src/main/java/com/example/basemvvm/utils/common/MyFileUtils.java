package com.example.basemvvm.utils.common;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * author: wtg
 * date:2020/4/2 0002
 * desc:
 */
public class MyFileUtils {
    /**
     * 将文件写入本地
     *
     * @param inputString  请求结果全体
     * @param destFileDir  目标文件夹
     * @param destFileName 目标文件名
     * @return 写入完成的文件
     */
    public static File saveFile(InputStream inputString, String destFileDir, String destFileName) {
        FileUtils.createOrExistsDir(destFileDir);
        File file = new File(destFileDir, destFileName);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        FileUtils.createOrExistsFile(file);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }
}
