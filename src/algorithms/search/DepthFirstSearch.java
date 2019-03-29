package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    Stack<AState> stackOfNodes;
    AState startPoint;
    AState endPoint;
    int countNodes;

    public  DepthFirstSearch()
    {
        countNodes = 0;
        stackOfNodes = new Stack<>();
    }


    @Override
    public Solution solve(ISearchable SearchableMaze) {
        startPoint = SearchableMaze.getStart();
        endPoint = SearchableMaze.getEnd();
        //setting the start point as visited
        SearchableMaze.setStateAsVisited(startPoint);

        //insert start position to the stack
        stackOfNodes.push(startPoint);

        while(!stackOfNodes.empty())
        {
            AState current = stackOfNodes.pop();
            if (current.equals(endPoint))
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
                        stackOfNodes.push(currNeighbor);
                    }
                }
            }
        }
        return null;
    }

    /**
     * The function returns the number of vertices developed by an algorithm
     * @return
     * @Override
     */

    public int getNumberOfNodesEvaluated() {
        return countNodes;
    }
    /**
     * The function returns the name of the algorithm
     *  @Override
     * @return string
     */
    public String getName(){
        return "Depth First Search";

    }
}
