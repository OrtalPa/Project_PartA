package algorithms.test;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {


    public static void main (String args[])
    {
       // Maze maze = (new SimpleMazeGenerator()).generate(3,5);
        MyMazeGenerator m = new MyMazeGenerator();
         Maze maze = (m).generate(500,500);
        //System.out.println(m.measureAlgorithmTimeMillis(1000,1000));
        System.out.println(maze.print());
        SearchableMaze  search = new SearchableMaze(maze);


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


        long startTime = System.currentTimeMillis();
        BreadthFirstSearch BreadthFirstSearch =new BreadthFirstSearch();
        Solution sol = BreadthFirstSearch.solve(search);
        long end = System.currentTimeMillis();
        System.out.println(end - startTime);
        ArrayList<AState> arraysol = sol.getSolutionPath();
        System.out.println("Solution");
       System.out.println(arraysol.toString());


    }

}
