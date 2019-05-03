package Server;

import java.io.*;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inputStream, OutputStream outputStream) {
  //      System.out.println("in server strategy");
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
//            System.out.println("passed input stream");
            int[] array = (int[])fromClient.readObject();
            int numOfRow = array[0];
            int numOfCol= array[1];
            Maze maze = (new MyMazeGenerator()).generate(numOfRow,numOfCol);
            byte[] byteArray = maze.toByteArray(); //maze is compressed
            MyCompressorOutputStream compressorOutputStream = new MyCompressorOutputStream(outputStream);
            compressorOutputStream.flush();
            compressorOutputStream.write(byteArray);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
