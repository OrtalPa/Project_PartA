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


        TestBestFirstSearch();
        //firstTest();

       // testRuntime();

       // testRuntimeParentsArray();


       // testEquals();
       // testRuntime();

        //testTimeGetAllPossibleStates();
        //testRuntimeParentsArray();


        Maze maze = generateMaze(6,6);
        System.out.println(maze);
        Solution sol = searchMaze(new DepthFirstSearch(), maze);
        printSolution(sol);
        sol = searchMaze(new BreadthFirstSearch(), maze);
        printSolution(sol);

    }

    private static Solution searchMaze(ASearchingAlgorithm searchingAlgorithm, Maze maze)
    {
        SearchableMaze search = new SearchableMaze(maze);
        long startTime = System.currentTimeMillis();
        Solution sol = searchingAlgorithm.solve(search);
        long end = System.currentTimeMillis();
        System.out.println("Runtime of "+searchingAlgorithm.getName()+": "+(end - startTime));
        return sol;
    }

    private static void printSolution(Solution sol) {
        if (sol!=null) {
            ArrayList<AState> arraysol = sol.getSolutionPath();
            System.out.println(arraysol);
        }
        else
            System.out.println("Solution is null");
    }

    private static Maze generateMaze(int rowNum, int colNum)
    {
        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = (m).generate(rowNum,colNum);
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


    private static void firstTest() {
        // Maze maze = (new SimpleMazeGenerator()).generate(3,5);
        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = (m).generate(10 ,10);
        //System.out.println(m.measureAlgorithmTimeMillis(1000,1000));
        System.out.println(maze);
        System.out.println("DONE create maze");


        SearchableMaze search = new SearchableMaze(maze);
        long startTime = System.currentTimeMillis();
        BreadthFirstSearch BreadthFirstSearch =new BreadthFirstSearch();
        System.out.println("DONE create BreadthFirstSearch");
        Solution sol = BreadthFirstSearch.solve(search);
        long end = System.currentTimeMillis();
        System.out.println(end - startTime);
        ArrayList<AState> arraysol = sol.getSolutionPath();
        System.out.println("Solution");
        System.out.println(arraysol.toString());
    }

    private static void TestBestFirstSearch() {
        // Maze maze = (new SimpleMazeGenerator()).generate(3,5);
        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = (m).generate(10 ,10);
        //System.out.println(m.measureAlgorithmTimeMillis(1000,1000));
        System.out.println(maze);
        System.out.println("DONE create maze");


        SearchableMaze search = new SearchableMaze(maze);
        long startTime = System.currentTimeMillis();
        BestFirstSearch BreadthFirstSearch =new BestFirstSearch();
        System.out.println("DONE create BreadthFirstSearch");
        Solution sol = BreadthFirstSearch.solve(search);
        long end = System.currentTimeMillis();
        System.out.println(end - startTime);
        ArrayList<AState> arraysol = sol.getSolutionPath();
        System.out.println("Solution");
        System.out.println(arraysol.toString());
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


    private static void testTimeGetAllPossibleStates() {

        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = (m).generate(20   ,20);
        SearchableMaze search = new SearchableMaze(maze);
        long startTime = System.currentTimeMillis();
        search.getAllPossibleStates(new MazeState(0,8));
        long End = System.currentTimeMillis();
        System.out.println(End-startTime + " test time of getAllPossibleStates");

    }


}
