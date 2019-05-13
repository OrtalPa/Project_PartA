package Server;

import java.io.*;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;


public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inputStream, OutputStream outputStream) {
  //      System.out.println("in server strategy");
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
            toClient.flush();
            ByteArrayOutputStream toClientArray = new ByteArrayOutputStream();
            int[] array = (int[])fromClient.readObject();
            int numOfRow = array[0];
            int numOfCol= array[1];
           Maze maze = (new MyMazeGenerator()).generate(numOfRow,numOfCol);
          // Maze maze = Configurations.getMazeGenerator().generate(numOfRow,numOfCol);
            MyCompressorOutputStream compressorOutputStream = new MyCompressorOutputStream(toClientArray);
            compressorOutputStream.write(maze.toByteArray());
            toClient.writeObject(toClientArray.toByteArray());
            toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
