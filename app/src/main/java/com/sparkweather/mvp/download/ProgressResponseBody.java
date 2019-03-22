package com.sparkweather.mvp.download;

import android.os.RecoverySystem;

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
 * 日期：2019/3/11 12:51
 * 创建：李加蒙
 * 描述：
 */
public class ProgressResponseBody extends ResponseBody {
    private ResponseBody responseBody;
    private BufferedSource bufferedSource;
    private ProgressListener progressListener;

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
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
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead;
                progressListener.onProgress(responseBody.contentLength(), totalBytesRead);
                return bytesRead;
            }
        };
    }

    public interface ProgressListener {
        void onProgress(long totalSize, long downSize);
    }
}
