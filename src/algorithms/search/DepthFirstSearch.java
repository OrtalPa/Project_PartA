package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    Stack<AState> stackOfNodes;
    int countNodes;

    public  DepthFirstSearch()
    {
        countNodes = 0;
        stackOfNodes = new Stack<>();
    }


    @Override
    public Solution solve(ISearchable SearchableMaze) {
        AState startPoint = SearchableMaze.getStart();
        AState endPoint = SearchableMaze.getEnd();
        //setting the start point as visited
        SearchableMaze.startSearch(startPoint);
        //insert start position to the stack
        stackOfNodes.push(startPoint);
        countNodes++;

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
                neighbors = flipList(neighbors);
                for (AState currNeighbor:neighbors)
                {//if the neighbor was not visited
                    if (!SearchableMaze.getStateAsVisited(currNeighbor))
                    {//set it as visited
                        SearchableMaze.setStateAsVisited(currNeighbor);
                        currNeighbor.setParent(current);
                        //insert to the stack
                        stackOfNodes.push(currNeighbor);
                        countNodes++;
                    }
                }
            }
        }
        return null;
    }

    /**
     * flips the order of the list so the searching algorithm
     * will go over the neighbors in the required order
     * @param listToFlip the list to flip
     * @return a flipped list
     */
    private ArrayList<AState> flipList(ArrayList<AState> listToFlip)
    {
        ArrayList<AState> neighbors = new ArrayList<>();
        for (int i = listToFlip.size()-1; i >=0 ; i--)
        {
            neighbors.add(listToFlip.remove(i));
        }
        return neighbors;
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
