package IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.Deflater;

public class MyCompressorOutputStream extends OutputStream {

    protected OutputStream out;

    public MyCompressorOutputStream(OutputStream  OutputStream ){
        //super();
        out = OutputStream;

    }//System.MyCompressorOutputStream

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        //super.write(b);
        ArrayList<Byte> temp = new ArrayList<>();
        byte[] mazeSpread = b;
        int numFound = mazeSpread[30];
        int countUntil256 =0;
        int i = 30;
        byte zero = -128;
        byte numAdd = 0;

        //For a case where the first number found is not zero
        if(numFound != 0){
            temp.add(zero);
        }

        //Move all array
        while (i < mazeSpread.length){
            if(mazeSpread[i] == numFound){
                if(countUntil256 <255){
                    i++; //increase the index in array
                    countUntil256++; // increase the num of appearance
                }
                else{
                    numAdd = (byte)(countUntil256 -128);
                    temp.add(numAdd); //Add the value
                    temp.add(zero);// add zero to the different value
                    countUntil256=1;
                    i++; //increase the index in array
                }
            }//not different
            else{
                numAdd = (byte)(countUntil256 -128);
                temp.add(numAdd);
                numFound = mazeSpread[i];
                countUntil256 = 1;
                i++;
            }
            //If we reached the end of the array
            if(i == mazeSpread.length){
                numAdd = (byte)(countUntil256 -128);
                temp.add(numAdd);
            }
        }//i < sizeOfArray

        //Write the dimensions of the maze and start and end point
        for (int j = 0; j < 30; j++) {
            write(b[j]);
            System.out.print(b[j]);
        }
        System.out.println();

        for (int j = 0; j < temp.size(); j++) {
            write(temp.get(j));
            System.out.print(temp.get(j));
        }
        System.out.println();
        //System.out.println(temp.toString());


    }

}
