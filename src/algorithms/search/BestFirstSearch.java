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

    PriorityQueue<AState> nodeList;


    public  BestFirstSearch(){
        super();
    }

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
                if(s1 instanceof  MazeState &&  s2 instanceof  MazeState){
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
                }
                return 0;
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



    /**
     * The function returns the name of the algorithm
     * @Override
     * @return string
     */

    public String getName(){
        return "Best First Search";

    }


}
