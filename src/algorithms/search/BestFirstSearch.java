package algorithms.search;


import java.util.*;

/**
 * The department represents the algorithm that aims to find the shortest path between the starting point
 * and the end point, a path that includes diagonals if possible
 */
public class BestFirstSearch extends ASearchingAlgorithm {

    protected PriorityQueue<AState> Open;
    protected HashMap<AState,AState> Close;


    public  BestFirstSearch(){
        super();
        Close = new HashMap();
    }

    public Solution solve(ISearchable SearchableMaze) {

        if (SearchableMaze == null) {
            return null;
        }
        AState Start = SearchableMaze.getStart();
        AState End = SearchableMaze.getEnd();
        if (Start == null || End == null) {
            return null;
        }


        Open = new PriorityQueue();

        Start.setParent(null);
        Start.setCost(0);
        Open.add(Start);
        countNodes++;

        //IF OPEN is empty THEN Exit
        while(Open.size() > 0){
            //Take from OPEN the node n with the best score, and move it to CLOSED.
            AState current = Open.remove();
            Close.put(current,current);
            //System.out.println(current.getCost() + " cost Of Neighbors " + current.toString());

            //IF n is the goal state
            if(current.equals(End)){
                //return  the solution by tracing the path from the goal node to n
                return new Solution(current);
            }
            else{
                //Expand node n by getting his successors
                ArrayList<AState> Neighbors = SearchableMaze.getAllPossibleStates(current);
                //FOR each successor s:
                while(Neighbors.size() > 0){
                    AState NeighborsCurrent = Neighbors.remove(0);
                    //IF s is in not in CLOSE continue
                    if(!Close.containsKey(NeighborsCurrent)){
                        //IF s is not in OPEN
                        if(!Open.contains(NeighborsCurrent)){
                            //apply the cost of arrival to s node
                            NeighborsCurrent.setCost(NeighborsCurrent.getCost() + current.getCost());
                            //update s parent node to be n
                            NeighborsCurrent.setParent(current);
                            //add s to OPEN
                            //System.out.println(NeighborsCurrent.getCost() + "cost Of Neighbors " + NeighborsCurrent.toString());
                            Open.add(NeighborsCurrent);
                            countNodes++;
                        }//not open
                    }//if not close
                    else{
                        AState InClose =Close.get(NeighborsCurrent);
                      //ELSE IF this new path shorter then previous one
                      if(InClose.getCost() > NeighborsCurrent.getCost() + current.getCost()){
                          //System.out.println(NeighborsCurrent.getCost() + "cost Of Neighbors " + NeighborsCurrent.toString());

                          //update s with the shortest path
                          InClose.setCost(NeighborsCurrent.getCost() + current.getCost());

                          //add to open if its not there
                          if(!Open.contains(InClose)){
                              Open.add(NeighborsCurrent);
                          }

                      }
                    }
                }
            }
        }
        return null;
    }

    /*
    @Override
    public Solution solve(ISearchable SearchableMaze) {

        if(SearchableMaze == null){
            return null;
        }
        AState StartMaze = SearchableMaze.getStart();
        AState EndMaze = SearchableMaze.getEnd();
        if(StartMaze == null || EndMaze == null ){
            return null;
        }

        Comparator<AState> Comparator = new Comparator<AState>() {
            @Override
            public int compare(AState s1, AState s2){
            if(s1 instanceof MazeState && s2 instanceof MazeState){
                int costS1 = Math.abs(((MazeState)s1).getRow() - ((MazeState)EndMaze).getRow())+ Math.abs(((MazeState)s1).getCol() - ((MazeState)EndMaze).getCol());
                int costS2 = Math.abs(((MazeState)s2).getRow() - ((MazeState)EndMaze).getRow())+ Math.abs(((MazeState)s2).getCol() - ((MazeState)EndMaze).getCol());
                if(costS1 == costS2){
                    return 0;
                }
                if(costS1 > costS2){
                    return 1;
                }
                if(costS1 < costS2){
                    return -1;
                }
                return 0;
            }//if
            else{
                if(s1.getCost() > s2.getCost()){
                    return 1;
                }
                else if(s1.getCost() == s2.getCost()){
                    return 0;
                }
                return -1;

            }//else

            }

        };

        nodeList = new PriorityQueue(Comparator);

        //Set up a parent to start the maze
        StartMaze.setParent(null);
        //The starting point cost is 0
        StartMaze.setCost(0);
        //Update that we visited the point
        SearchableMaze.startSearch(StartMaze);
        //Add to the list
        nodeList.add(StartMaze);
        //Raising the number of vertices we have found
        countNodes++;
        boolean flagFound = false;
        AState current = null;

        while(nodeList.size() > 0 && flagFound == false) {
            current = nodeList.remove();

            //we fond the end
            if (current.equals(EndMaze)) {
                flagFound = true;
            }
            else {
                ArrayList<AState> Neighbors = SearchableMaze.getAllPossibleStates(current);
                if(Neighbors != null){
                    while (Neighbors.size() > 0) {
                        AState stateNeighbors = Neighbors.remove(0);
                        if (SearchableMaze.getStateAsVisited(stateNeighbors) == false) {
                            try {
                                SearchableMaze.setStateAsVisited(stateNeighbors);
                            }catch (IndexOutOfBoundsException e) {return null;}
                            stateNeighbors.setParent(current);
                            stateNeighbors.setCost(stateNeighbors.getCost() + current.getCost());
                            //Add to the list
                            nodeList.add(stateNeighbors);
                            //Raising the number of vertices we have found
                            countNodes++;
                        }//if getStateAsVisited
                    }//while Neighbors.size()
                }
            }//else

        }//while found
        if(flagFound ==  true){
            return new Solution(current);
        }
        return null;


    }

    */



    /**
     * The function returns the name of the algorithm
     * @Override
     * @return string
     */

    public String getName(){
        return "Best First Search";

    }


}
