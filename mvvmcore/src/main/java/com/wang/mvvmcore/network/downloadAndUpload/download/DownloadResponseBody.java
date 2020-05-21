package com.wang.mvvmcore.network.downloadAndUpload.download;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * author: wtg  2019/9/2 0002
 * desc: 现在进度的监听
 */
public class DownloadResponseBody extends ResponseBody {
    private ResponseBody responseBody;
    private BufferedSource bufferedSource;
    private DownloadListener downloadListener;
    private long startPoint = -1;

    public DownloadResponseBody(ResponseBody responseBody, DownloadListener progressListener) {
        this.responseBody = responseBody;
        this.downloadListener = progressListener;
    }

    public DownloadResponseBody(ResponseBody responseBody, DownloadListener progressListener, long startPoint) {
        this.responseBody = responseBody;
        this.downloadListener = progressListener;
        this.startPoint = startPoint;
    }


    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @NotNull
    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(@NotNull Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                if (totalBytesRead == 0) {
                    downloadListener.onStartDownload(responseBody.contentLength());
                }
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (null != downloadListener) {
                    if (startPoint == -1) {
                        downloadListener.onProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    } else {
                        downloadListener.onProgress(totalBytesRead + startPoint, responseBody.contentLength(), bytesRead == -1);
                    }
                }
                return bytesRead;
            }
        };
    }

    public interface DownloadListener {
        void onStartDownload(long length);

        void onProgress(long progress, long total, boolean done);

        void onFail(String errorInfo);
    }
}
