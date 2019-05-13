package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class MyDecompressorInputStream extends InputStream {

    protected InputStream in;

    private volatile static  int numCuurent;

    public MyDecompressorInputStream(InputStream InputStream){
        super();
        if(InputStream != null){
            in = InputStream;
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
        //Create an array the size of the array of b
        byte[] CompressArray = new byte[b.length];

        //Enter the array Compress that we received in CompressArray
        in.read(CompressArray);

        byte zero = 0;
        byte one = 1;
        numCuurent = 0;

        //Initialization of a reference list that will contain the uncompressed maze
        //Income of maze dimensions and start and exit point
        ArrayList<Byte> temp = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            temp.add(CompressArray[i]);
        }
        for (int i = 30; i < b.length; i++) {
                int number = CompressArray[i];
                //if(number !=-128){
                number = number + 128;
                //}
                while(number > 0){
                    if(numCuurent == 0){
                        //Write the relevant value to the list temp
                        temp.add(zero);
                        // decrease the value
                        number--;
                    }
                    else if(numCuurent == 1){
                        //Write the relevant value to the list temp
                        temp.add(one);
                        //decrease the value
                        number--;
                    }

                }
                //Changes the value to write now
                if(numCuurent == 1){
                    numCuurent = 0;
                }
                else{
                    numCuurent = 1;
                }
        }

        for (int i = 0; i < b.length; i++) {//temp.size???????????????????
            b[i] = temp.get(i);
            //System.out.println(b[i]);
        }


        return 0;
    }



}
