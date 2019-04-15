package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

    protected InputStream in;
    private  int numFound;
    private int counter;

    public MyDecompressorInputStream(InputStream InputStream){
        super();
        if(InputStream != null){
            in =InputStream;
            counter =0;
            numFound=0;

        }

    }


    @Override
    public int read() throws IOException {



    }

    //optional
    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }
}
