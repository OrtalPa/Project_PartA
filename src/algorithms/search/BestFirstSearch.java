package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * The department represents the algorithm that aims to find the shortest path between the starting point
 * and the end point, a path that includes diagonals if possible
 */
public class BestFirstSearch extends ASearchingAlgorithm {



    PriorityQueue<AState> open;
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

        open = new PriorityQueue(Comparator);
        numOfPOsition=0;

    }

    @Override
    public Solution solve(ISearchable SearchableMaze) {


        AState StartMaze = SearchableMaze.getStart();
        AState EndMaze = SearchableMaze.getEnd();
        StartMaze.setParent(null);
        StartMaze.setCost(0);
        SearchableMaze.setStateAsVisited(StartMaze);
        open.add(StartMaze);
        numOfPOsition++;
        boolean flagFound = false;
        AState current = null;
        while(open.size() > 0 && flagFound == false) {

            current = open.remove();

            if (current.equals(EndMaze)) {
                flagFound = true;
            } else {
                ArrayList<AState> Neighbors = SearchableMaze.getAllPossibleStates(current);
                while (Neighbors.size() > 0) {
                    AState stateNeighbors = Neighbors.remove(0);
                    if (SearchableMaze.getStateAsVisited(stateNeighbors) == false) {
                        SearchableMaze.setStateAsVisited(stateNeighbors);
                        stateNeighbors.setParent(current);
                        stateNeighbors.setCost(stateNeighbors.getCost() + current.getCost());
                        open.add(stateNeighbors);
                        numOfPOsition++;
                    }
                }
            }

        }
        if(flagFound ==  true){
            return new Solution(current);
        }

        /*
        AState StartMaze = SearchableMaze.getStart();
        AState EndMaze = SearchableMaze.getEnd();

        //Define lists CLOSED
        ArrayList<AState> close = new ArrayList<>();

        StartMaze.setParent(null);
        StartMaze.setCost(0);
        SearchableMaze.setStateAsVisited(StartMaze);
        //add the start node, s, to OPEN
        open.add(StartMaze);
        numOfPOsition++;

        boolean flagFound = false;
        AState current = null;
        //2
        while(open.size() > 0 && !flagFound){
            //Take from OPEN the node n with the best score, and move it to CLOSED.
            current =  open.poll();
            close.add(current);

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
                    //if(close.contains(stateNeighbors) == false){
                        //continue;
                    //}
                    // IF s is not in OPEN
                    if(open.contains(stateNeighbors) == false){
                        //update s parent node to be current
                        stateNeighbors.setParent(current);
                        stateNeighbors.setVisited(true);
                        // apply the cost of arrival to s node
                        stateNeighbors.setCost(stateNeighbors.getCost() + current.getCost());
                        //add s to OPEN
                        open.add(stateNeighbors);
                        numOfPOsition++;
                    }
                    //ELSE IF this new path shorter then previous one
                    else{
                        int cost1 = 0;
                        if(open.contains(stateNeighbors) == true){
                          Iterator<AState> iter = open.iterator();
                          while(iter.hasNext()){
                              if(iter.equals(stateNeighbors)){
                                  cost1 = iter.next().getCost();
                              }
                              iter.next();
                          }//while
                        }//if
                        int cost2 = 0;
                        if(close.contains(stateNeighbors) == true){
                            int i=0;
                            for(i=0;i<close.size();i++){
                                if(close.get(i).equals(stateNeighbors)){
                                    cost2 = close.get(i).getCost();
                                }
                            }
                        }//if
                        int maxCost = Math.max(cost1,cost2);
                        if(maxCost > stateNeighbors.getCost() + current.getCost()){
                            //update s with the shortest path
                            stateNeighbors.setCost(stateNeighbors.getCost() + current.getCost());
                            //add to open if its not there
                            if(open.contains(stateNeighbors)==false){
                                open.add(stateNeighbors);
                            }
                        }//if
                    }
                }
            }

        }
        //return  the solution by tracing the path from the goal node to n
        if(flagFound ==  true){
            return new Solution(current);
        }

        //IF OPEN is empty THEN Exit
        return null;

        */

        return null;
    }


    /**
     * The function returns the number of vertices developed by an algorithm
     * @return
     * @Override
     */

    public int getNumberOfNodesEvaluated(){

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
