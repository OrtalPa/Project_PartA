package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        int previousI = 0;
        int j = 100;
        for (int i = 10; i <= 1000; i += 10) {
            //for (int i = 1; i <= 1000; i += 1) {


                for (; j <= 1000; j += Math.random() * 100) {

                    String mazeFileName = "savedMaze.maze";
                    AMazeGenerator mazeGenerator = new MyMazeGenerator();
                    Maze maze = mazeGenerator.generate(i, j); //Generate new maze
                    byte[] b = maze.toByteArray();
                    //maze.print();
                    //System.out.println();
                    try {
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
//read maze from file
                        InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
                        savedMazeBytes = new byte[maze.toByteArray().length];
                        in.read(savedMazeBytes);
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Maze loadedMaze = new Maze(savedMazeBytes);
                    //loadedMaze.print();
                    boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());

                    if (!areMazesEquals) {
                        System.out.println(String.format("Mazes (%d,%d) equal: %s", i, j, areMazesEquals));
                        loadedMaze.print();
                        maze.print();
                    } else if (i != previousI) {
                        System.out.println(String.format("Still good (%d,%d)", i, j));
                        previousI = i;
                    }
//maze should be equal to loadedMaze
                }
                j = (int) (600 + Math.random() * 100);
            }
        }
    }




/*
package test;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {

    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(1000, 200); //Generate new maze
        //System.out.println("1");

        try {
            // save maze to a file

            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            //maze.print();
            //System.out.println("2");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            //System.out.println("3");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        //maze.print();
        //loadedMaze.print();

        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals));
//maze should be equal to loadedMaze
    }
}

*/
