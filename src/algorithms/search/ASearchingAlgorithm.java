package algorithms.search;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * The department is an abstract class that implements the algorithm search interface
 */

//Does each search problem solve an algorithm of the form we have written???????


public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

   protected int countNodes;


    public ASearchingAlgorithm(){
        countNodes = 0;

    }


   //The function returns the name of the algorithm
    public abstract String getName();


    /**
     * The function returns the number of vertices developed by an algorithm
     * @return int Number Of Nodes Evaluated
     *
     */
    public int getNumberOfNodesEvaluated(){
        return countNodes;
    }

    public abstract Solution solve(ISearchable SearchableMaze);
    

}
