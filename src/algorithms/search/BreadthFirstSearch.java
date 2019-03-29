package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {


    Queue<AState> nodesQueue;
    int countNodes;

    public  BreadthFirstSearch(){
        /*
        Comparator<AState> Comparator = new Comparator<AState>() {
            @Override
            public int compare(AState s1, AState s2) {
                return 0;
            }
        };*/
        nodesQueue = new LinkedList<>();
        countNodes=0;
    }

    @Override
    public Solution solve(ISearchable SearchableMaze)
    {
        // Symbolizes that we have reached the end point
        boolean flagFound = false;

        //Get the start and end point of the maze
        AState start =SearchableMaze.getStart();
        AState end =SearchableMaze.getEnd();
        //set first point as visited
        SearchableMaze.setStateAsVisited(start);
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

        /*
        AState StateNow =  start;
        //A loop that checks as long as we have points in queue and we do not get to the end
        while(nodesQueue.size() > 0 || !StateNow.equals(end)){

             StateNow= nodesQueue.remove();
             SearchableMaze.setStateAsVisited(StateNow);

            //You get a list of all the neighboring points to the current point
            ArrayList<AState> array =  SearchableMaze.getAllPossibleStates(StateNow);
            //As long as the list is not empty and we have not found the end point
            while(array.size() > 0 && !flagFound){
                if(!array.get(0).equals(end)){
                    AState state = array.remove(0);
                    if(!nodesQueue.contains(state) && !SearchableMaze.getStateAsVisited(state)){
                        //Add the neighboring point to the list of points to check
                        state.setParent(StateNow);
                        //Marks I've been to a point
                        SearchableMaze.setStateAsVisited(state);
                        nodesQueue.add(state);
                        countNodes++;
                    }
                }
                //break the loop
                else{
                    flagFound= true;
                }
            }
            if(flagFound == true){
                countNodes++;
                end.setParent(StateNow);
               return new Solution(end);
            }
        }//while
        return null;

        */
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
        return "Breadth First Search";

    }

}
