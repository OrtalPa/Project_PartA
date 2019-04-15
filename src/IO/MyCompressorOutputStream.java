package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    protected OutputStream out;

    public MyCompressorOutputStream(OutputStream  OutputStream ){
        if(OutputStream != null){
            out =OutputStream;
        }
    }//System.MyCompressorOutputStream


    @Override
    public void write(int b) throws IOException {

    }

    //optional
    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }
}
