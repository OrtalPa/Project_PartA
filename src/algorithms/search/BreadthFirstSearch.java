package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {


     protected Queue<AState> nodesQueue;
    protected int countNodes;

    public  BreadthFirstSearch(){

        nodesQueue = new LinkedList<>();
        countNodes=0;
    }

    @Override
    public Solution solve(ISearchable SearchableMaze)
    {
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
        nodesQueue.add(start);
        countNodes++;

        while(!nodesQueue.isEmpty())
        {
            AState current = nodesQueue.poll();
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
                        nodesQueue.add(currNeighbor);
                        countNodes++;
                    }
                }
            }
        }
        return null;

    }

    /**
     * The function returns the number of vertices developed by an algorithm
     * @return int
     *
     */
    public int getNumberOfNodesEvaluated() {
        return countNodes;
    }
    /**
     * The function returns the name of the algorithm
     *
     * @return string
     */
    public String getName(){
        return "Breadth First Search";

    }

}
