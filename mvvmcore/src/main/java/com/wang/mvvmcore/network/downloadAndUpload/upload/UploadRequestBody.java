package com.wang.mvvmcore.network.downloadAndUpload.upload;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * author: wtg
 * date:2020/5/25 0025
 * desc: 上传带进度的requestBody
 */
public class UploadRequestBody extends RequestBody {
    private RequestBody mRequestBody;
    private LoadingListener mLoadingListener;
    private long mContentLength;

    public UploadRequestBody(RequestBody requestBody, LoadingListener loadingListener) {
        mRequestBody = requestBody;
        mLoadingListener = loadingListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
        ByteSink byteSink = new ByteSink(bufferedSink);
        BufferedSink mBufferedSink = Okio.buffer(byteSink);
        mRequestBody.writeTo(mBufferedSink);
        mBufferedSink.flush();
    }

    //文件的总长度
    @Override
    public long contentLength() {
        try {
            if (mContentLength == 0)
                mContentLength = mRequestBody.contentLength();
            return mContentLength;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private final class ByteSink extends ForwardingSink {
        //已经上传的长度
        private long mByteLength = 0L;

        ByteSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(@NotNull Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            mByteLength += byteCount;
            mLoadingListener.onProgress(mByteLength, contentLength());
        }
    }

    /**
     * 上传监听
     */
    public interface LoadingListener {
        void onProgress(long currentLength, long contentLength);
    }
}
