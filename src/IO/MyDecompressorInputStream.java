package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream {

    protected InputStream in;

    private volatile static  int numCuurent;

    public MyDecompressorInputStream(InputStream InputStream){
        super();
        if(InputStream != null){
            in =InputStream;
            numCuurent =0;

        }

    }


    @Override
    public int read() throws IOException {
        return numCuurent;

    }

    //optional
    @Override
    public int read(byte[] b) throws IOException {
        byte[] CompressArray = new byte[b.length];
        in.read(CompressArray);

        byte zero = 0;
        byte one = 1;
        numCuurent = 0;

        ArrayList<Byte> temp = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            temp.add(CompressArray[i]);
        }
        for (int i = 30; i < b.length; i++) {
                int number = CompressArray[i];
                if(number != 0){
                    number = number + 128;
                }
                while(number > 0){
                    if(numCuurent == 0){
                        temp.add(zero);
                        number--;
                    }
                    else if(numCuurent == 1){
                        temp.add(one);
                        number--;
                    }

                }
                if(numCuurent == 1){
                    numCuurent = 0;
                }
                else{
                    numCuurent = 1;
                }
        }

        for (int i = 0; i < temp.size(); i++) {
            b[i] = temp.get(i);
        }
        return 0;
    }



}
