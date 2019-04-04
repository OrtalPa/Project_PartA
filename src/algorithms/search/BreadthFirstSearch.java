package algorithms.search;


import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {


    protected Queue<AState> nodesQueue;
    protected HashMap<AState,AState> VisitedNodes;


    public  BreadthFirstSearch(){
        super();
        nodesQueue = new LinkedList<>();
        VisitedNodes = new HashMap<>();
    }


    @Override
    public Solution solve(ISearchable SearchableMaze)
    {
        if(SearchableMaze == null){
            return null;
        }
        //Get the start and end point of the maze
        AState start =SearchableMaze.getStart();
        AState end =SearchableMaze.getEnd();
        if(start == null || end == null ) {
            return null;
        }


        VisitedNodes.put(start,start);

        //Adds the first point of the maze
        nodesQueue.add(start);
        countNodes++;
        while(!nodesQueue.isEmpty())
        {

            AState current = nodesQueue.remove();
            if (current.equals(end))
            {
                return new Solution(current);
            }
            else
            {
                ArrayList<AState> neighbors = SearchableMaze.getAllPossibleStates(current);
                if(neighbors != null){
                    for (AState currNeighbor:neighbors)
                    {//if the neighbor was not visited
                        if (!VisitedNodes.containsKey(currNeighbor))
                        {//set it as visited
                            VisitedNodes.put(currNeighbor,currNeighbor);
                            currNeighbor.setParent(current);
                            //insert to the stack
                            nodesQueue.add(currNeighbor);
                            countNodes++;
                        }
                    }
                }

            }
        }
        return null;

    }
    /*
    @Override
    public Solution solve(ISearchable SearchableMaze)
    {
        if(SearchableMaze == null){
            return null;
        }
        //Get the start and end point of the maze
        AState start =SearchableMaze.getStart();
        AState end =SearchableMaze.getEnd();
        if(start == null || end == null ) {
            return null;
        }

        ArrayList<AState> SaveNodes = new ArrayList<>();
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
                if(neighbors != null){
                    for (AState currNeighbor:neighbors)
                    {//if the neighbor was not visited
                        if (!SearchableMaze.getStateAsVisited(currNeighbor))
                        {//set it as visited
                            try {
                                SearchableMaze.setStateAsVisited(currNeighbor);
                            }catch (IndexOutOfBoundsException e) {return null;}
                            currNeighbor.setParent(current);
                            //insert to the stack
                            nodesQueue.add(currNeighbor);
                            countNodes++;
                        }
                    }
                }

            }
        }
        return null;


    }

    */

    /**
     * The function returns the name of the algorithm
     *
     * @return string
     */
    public String getName(){
        return "Breadth First Search";

    }

    private ArrayList<AState> flipList(ArrayList<AState> listToFlip)
    {
        ArrayList<AState> neighbors = new ArrayList<>();
        for (int i = listToFlip.size()-1; i >=0 ; i--)
        {
            neighbors.add(listToFlip.remove(i));
        }
        return neighbors;
    }

}
