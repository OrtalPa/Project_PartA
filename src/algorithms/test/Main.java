package algorithms.test;




import algorithms.mazeGenerators.Maze;

import algorithms.mazeGenerators.MyMazeGenerator;

import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import algorithms.search.*;


import java.util.*;


public class Main {





    public static void main (String args[])

    {

        // testRuntime();



        // testRuntimeParentsArray();



        // testEquals();

        // testRuntime();



        //testTimeGetAllPossibleStates();

        //testRuntimeParentsArray();

        //testRuntimeHash();
        //testRuntimeHashMap();
        //testRuntime();

        Maze maze = generateMaze(10,1);

        //print(maze);
        //System.out.println(maze);
       Solution sol = searchMaze(new DepthFirstSearch(), maze);

        //printSolution(sol);
         sol = searchMaze(new BreadthFirstSearch(), maze);

       // printSolution(sol);

       sol =  searchMaze(new BestFirstSearch(), maze);

       // printSolution(sol);



    }

    private static void print (Maze maze) {
        for (int i = 0; i < maze.getNumberOfRows(); i++) {
            for (int j = 0; j < maze.getNumberOfColumns(); j++) {
                if (i == maze.getStartPosition().getRowIndex() && j == maze.getStartPosition().getColumnIndex()) {//startPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (i ==  maze.getGoalPosition().getRowIndex() && j == maze.getGoalPosition().getColumnIndex()) {//goalPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (maze.getValue(i,j) == 1) System.out.print(" " + "\u001B[45m" + " ");
                else System.out.print(" " + "\u001B[107m" + " ");
            }
            System.out.println(" " + "\u001B[107m");
        }

    }

    private static Solution searchMaze(ASearchingAlgorithm searchingAlgorithm, Maze maze)

    {

        SearchableMaze search = new SearchableMaze(maze);

        long startTime = System.currentTimeMillis();

        Solution sol = searchingAlgorithm.solve(search);

        long end = System.currentTimeMillis();

        System.out.println("Runtime of "+searchingAlgorithm.getName()+": "+(end - startTime));
        System.out.println("Number Of Nodes Evaluated "+searchingAlgorithm.getNumberOfNodesEvaluated());

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


    private static void testRuntimeHash() {
        long time = System.nanoTime();

        Hashtable<AState,AState> VisitedNodes = new Hashtable();

        System.out.println("time to create Hashtable: "+ (System.nanoTime() -  time));

        time = System.nanoTime();

        AState s1 = new MazeState(0,0);
        VisitedNodes.put(s1,s1);

        System.out.println("time to add: "+ (System.nanoTime() -  time));

        time = System.nanoTime();

        VisitedNodes.contains(s1);

        System.out.println("time to contains: "+ (System.nanoTime() -  time));



    }

    private static void testRuntimeHashMap() {
        long time = System.nanoTime();

        HashMap<AState,AState> VisitedNodes = new HashMap();

        System.out.println("time to create HashtableMAP: "+ (System.nanoTime() -  time));

        time = System.nanoTime();

        AState s1 = new MazeState(0,0);
        VisitedNodes.put(s1,s1);

        System.out.println("time to add in HashMAP: "+ (System.nanoTime() -  time));

        time = System.nanoTime();

        VisitedNodes.containsKey(s1);

        System.out.println("time to contains in HashMAP: "+ (System.nanoTime() -  time));



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