package cn.jack.netu;

import java.io.IOException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * @创建者 Jack
 * @创建时间 2023/7/16
 * @描述
 */
public class ProgressRequestBody extends RequestBody {
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    private final RequestBody requestBody;
    private final ProgressListener progressListener;

    public ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.requestBody = requestBody;
        this.progressListener = progressListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        BufferedSink bufferedSink = Okio.buffer(new CForwardingSink(sink));
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private class CForwardingSink extends ForwardingSink {
        private long totalBytesWritten = 0;

        CForwardingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(@NonNull Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            totalBytesWritten += byteCount;
            progressListener.onProgress(totalBytesWritten, contentLength());
        }
    }

    public interface ProgressListener {
        void onProgress(long bytesWritten, long contentLength);
    }
}

