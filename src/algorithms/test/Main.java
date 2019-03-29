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
        firstTest();

       // testRuntime();

        //testTimeGetAllPossibleStates();
        //testRuntimeParentsArray();


        /*DFS
        long startTime = System.currentTimeMillis();
        Solution sol = (new DepthFirstSearch()).solve(search);
        long end = System.currentTimeMillis();
        System.out.println(end - startTime);
        ArrayList<MazeState> arraysol = sol.getSolutionPath();
        System.out.println(arraysol.toString());

        *DES_END/
        /*Print neighbors
        ArrayList<List<MazeState>> array =  search.getList();
        for(int i=0; i< array.size();i++){
            List<MazeState> list = array.get(i);
            if (list != null){
                for(int j=0; j<list.size();j++){
                    System.out.print(list.get(j).toString());
                }
            }

            System.out.println();
        }
        Print neighbors end*/





    }

    private static void testRuntimeParentsArray() {
        long time = System.nanoTime();
        MazeState[][] Parents = new MazeState[10][10];
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
