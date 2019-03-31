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

     /*
    public Solution solve(ISearchable SearchableMaze, Collection<AState> Collection) {

        countNodes = 0;
        if(SearchableMaze == null){
            return null;
        }

        // Symbolizes that we have reached the end point
        boolean flagFound = false;

        //Get the start and end point of the maze
        AState start =SearchableMaze.getStart();
        AState end =SearchableMaze.getEnd();

        if(start == null || end == null ) {
            return null;
        }
        if(!(start instanceof  MazeState) ||  !(end instanceof  MazeState)){
            return null;
        }

        //We know that the type of StartMaze and EndMaze is MazeState
        if(((MazeState)end).getRow() < 0  || ((MazeState)start).getRow() < 0 || ((MazeState)start).getCol() < 0 || ((MazeState)end).getCol() < 0){
            return null;
        }
        //set first point as visited
        SearchableMaze.startSearch(start);
        //Adds the first point of the maze
        Collection.add(start);
        countNodes++;

        while(!Collection.isEmpty())
        {
            AState current = Collection.remove();
            if (current.equals(end))
            {
                return new Solution(current);
            }
            else
            {
                ArrayList<AState> neighbors = SearchableMaze.getAllPossibleStates(current);
                for (AState currNeighbor:neighbors)
                {//if the neighbor was not visited
                    if (!SearchableMaze.getStateAsVisited(currNeighbor))
                    {//set it as visited
                        SearchableMaze.setStateAsVisited(currNeighbor);
                        currNeighbor.setParent(current);
                        //insert to the stack
                        Collection.add(currNeighbor);
                        countNodes++;
                    }
                }
            }
        }
        return null;
    }

    */

}
