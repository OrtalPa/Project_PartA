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
        //byte[] CompressArray = new byte[b.length];

        //in.read(CompressArray);

        //Enter the array Compress that we received in tempArray
        int countByteArray =0;
        byte[]  tempArray = new byte[b.length];
        int flag =-1;
        while((flag = in.read())!=-1){
            tempArray[countByteArray]=(byte)flag;
            //System.out.print( tempArray[countByteArray]);
            countByteArray++;
        }
       // System.out.println();

        int resultLength = b.length;
        Inflater decompressor = new Inflater();
        decompressor.setInput(tempArray,0,countByteArray);
        byte[] result = new byte[b.length];
        try {
            resultLength = decompressor.inflate(result);
        } catch (DataFormatException e) {
            e.printStackTrace();
        }finally {
            decompressor.end();

        }


        byte zero = 0;
        byte one = 1;
        numCuurent = 0;

        //Initialization of a reference list that will contain the uncompressed maze
        //Income of maze dimensions and start and exit point
        ArrayList<Byte> temp = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            temp.add(result[i]);
            //System.out.print(result[i]);
        }
        //System.out.println();
        for (int i = 30; i < resultLength ; i++) {
                int number = result[i];
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


        //The tempo contains all values of the maze with the dimensions
        // Array b contains the values of the maze including the dimensions
        //So their size is the same
        for (int i = 0; i < b.length && i < temp.size(); i++) {
            b[i] = temp.get(i);
            //System.out.print(b[i]);
        }
        //System.out.println();


        return 0;
    }



}
