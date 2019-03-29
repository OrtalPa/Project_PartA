package algorithms.test;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {


    public static void main (String args[])
    {
       // testRuntime();

       // testRuntimeParentsArray();

       // testEquals();

        Maze maze = generateMaze(1000,1000);
       // System.out.println(maze);
        searchMaze(new DepthFirstSearch(), maze);
    }

    private static void searchMaze(ASearchingAlgorithm searchingAlgorithm, Maze maze)
    {
        SearchableMaze search = new SearchableMaze(maze);
        long startTime = System.currentTimeMillis();
        Solution sol = searchingAlgorithm.solve(search);
        long end = System.currentTimeMillis();
        System.out.println("Runtime of "+searchingAlgorithm.getName()+": "+(end - startTime));
        if (sol!=null) {
            ArrayList<AState> arraysol = sol.getSolutionPath();
         //   System.out.println(arraysol);
        }
        else
            System.out.println("Solution is null");
    }

    private static Maze generateMaze(int rowNum, int colNum)
    {
        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = (m).generate(rowNum   ,colNum);
        return maze;
    }

    private static void testEquals() {
        AState state1 = new MazeState(3,4);
        AState state2 = new MazeState(3,4);
        AState state3 = new MazeState(5,4);
        System.out.println(state1.equals(state2));
        System.out.println(state2.equals(state3));
    }

    private static void testRuntimeParentsArray() {
        long time = System.nanoTime();
        MazeState[][] Parents = new MazeState[1000][1000];
        for (int i = 0; i < Parents.length; i++) {
            for (int j = 0; j < Parents[0].length; j++) {
                Parents[i][j] = new MazeState(i,j);
            }
        }
        System.out.println("time to fill array: "+ (System.nanoTime() -  time));
    }

    private static void testRuntime() {

        long time = System.nanoTime();
        Stack<Integer> test = new Stack<>();
        System.out.println("time to create stack: "+ (System.nanoTime() -  time));
        time = System.nanoTime();
        test.push(5);
        System.out.println("time to push: "+ (System.nanoTime() -  time));
        time = System.nanoTime();
        test.pop();
        System.out.println("time to pop: "+ (System.nanoTime() -  time));
    }


}
