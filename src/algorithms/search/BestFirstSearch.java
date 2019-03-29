package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The department represents the algorithm that aims to find the shortest path between the starting point
 * and the end point, a path that includes diagonals if possible
 */
public class BestFirstSearch extends ASearchingAlgorithm {



    PriorityQueue<AState> nodesList;
    int numOfPOsition;


    public  BestFirstSearch(){
        AState a = new MazeState(0,0);
        Comparator<MazeState> Comparator = new Comparator<MazeState>() {
            @Override
            public int compare(MazeState s1, MazeState s2){
                if(s1.getPriority() > s2.getPriority() ){
                    return 1;
                }
                else if(s1.getPriority() == s2.getPriority() ){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        };

        nodesList = new PriorityQueue(Comparator);
        numOfPOsition=0;

    }

    @Override
    public Solution solve(ISearchable SearchableMaze) {

        AState StartMaze = SearchableMaze.getEnd();
        AState EndMaze = SearchableMaze.getStart();

        StartMaze.setParent(null);
        SearchableMaze.setStateAsVisited(StartMaze);
        nodesList.add(StartMaze);
        boolean flagFound = false;
        AState current = null;
        //2
        while(nodesList.size() > 0 && !flagFound){
            //Take from OPEN the node n with the best score, and move it to CLOSED.
            current =  nodesList.poll();
            //and move it to CLOSED.


            //IF n is the goal state
            if(current.equals(EndMaze)){
                flagFound = true;
            }
            else{
                //Expand node n by getting his successors
                ArrayList<AState> Neighbors = SearchableMaze.getAllPossibleStates(current);
                //FOR each successor s:
                while(Neighbors.size() > 0){
                    AState stateNeighbors = Neighbors.remove(0);
                    //IF s is in not in CLOSE continue
                    if(){
                        continue;

                    }
                    // IF s is not in OPEN
                    if(nodesList.contains(stateNeighbors) == false){
                        //update s parent node to be current
                        stateNeighbors.setParent(current);
                        stateNeighbors.setVisited(true);
                        // apply the cost of arrival to s node
                        stateNeighbors.setCost((stateNeighbors).getCost() + current.getCost());
                        //add s to OPEN
                        nodesList.add(stateNeighbors);
                    }
                    else if()

                }
            }

        }
        //return  the solution by tracing the path from the goal node to n
        if(flagFound ==  true){
            return new Solution(current);
        }

        //IF OPEN is empty THEN Exit
        return null;
    }


    /**
     * The function returns the number of vertices developed by an algorithm
     * @return
     * @Override
     */

    public int getNumberOfNodesEvaluated() {

        return numOfPOsition;
    }

    /**
     * The function returns the name of the algorithm
     * @Override
     * @return string
     */

    public String getName(){
        return "Best First Search";

    }


}
