package algorithms.mazeGenerators;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("1");
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(5621, 2605); //Generate new maze
        // int[][] shir = {{0,1,0,1,0},{0,0,0,0,0}};
        //Maze maze =  new Maze(shir,new Position(0,0),new Position(1,0));
        try {
            System.out.println("2");
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
            System.out.println("3");
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
            System.out.println("4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("5");
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals));
//maze should be equal to loadedMaze
    }
}


/*
package algorithms.mazeGenerators;

public class Main {

    public static void main (String args[]){

        //testByteArray();
        //testByteArray256();
        testToCconstructor();
    }

    private static void testToCconstructor() {
        byte[] array = {-1,-1,-1,-1,0,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,0,-1,-1,-1,0,3,0,0,0,0,3,1,0,1,0,1,1,1,0,0};
        Maze Maze = new Maze(array);
        System.out.println(Maze.getGoalPosition()); //end (1,0)
        System.out.println(Maze.getStartPosition()); //start(0,1)
        System.out.println(Maze.getNumberOfRows()); //row(3)
        System.out.println(Maze.getNumberOfColumns()); //col(3)
        Maze.print();
    }


    public static void testByteArray(){

        System.out.println("Test TO Byte Array:");
        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = (m).generate(10,10);
        maze.print();
        System.out.println("");
        System.out.println("The Byte array of maze:");
        byte[] ansToByteArray = maze.toByteArray();
        for(int i=0; i<ansToByteArray.length; i++){
            if(i == ansToByteArray.length - 1){
                System.out.print(ansToByteArray[i]);
            }
            else{
                System.out.print(ansToByteArray[i] + " , ");
            }
        }
        System.out.println("");

    }//testOfSpreadingTheArray




  public static void testByteArray256(){
        System.out.println("Test TO Byte Array 256:");
        int[][] maze256 = new int[1][260];
        for (int i = 0; i < 260 ; i++) {
            maze256[0][i] = 1;
        }
        maze256[0][258] = 0;
        maze256[0][259] = 0;
        Maze Maze = new Maze(maze256, new Position(0,258),new Position(0,259));
        Maze.print();

        System.out.println("The Byte array of maze:");
        byte[] ansToByteArray = Maze.toByteArray();
        for(int i=0; i<ansToByteArray.length; i++){
            if(i == ansToByteArray.length - 1){
                System.out.print(ansToByteArray[i]);
            }
            else{
                System.out.print(ansToByteArray[i] + " , ");
            }

        }

    }



}

*/
