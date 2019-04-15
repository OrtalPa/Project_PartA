package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

    protected InputStream in;

    public MyDecompressorInputStream(InputStream InputStream){
        if(InputStream != null){
            in =InputStream;
        }

    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    //optional
    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }
}
