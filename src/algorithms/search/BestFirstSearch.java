package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The department represents the algorithm that aims to find the shortest path between the starting point
 * and the end point, a path that includes diagonals if possible
 */
public class BestFirstSearch extends ASearchingAlgorithm {



    PriorityQueue<AState> heap;
    int numOfPOsition;


    public  BestFirstSearch(){
        AState a = new MazeState(0,0);
        Comparator<MazeState> Comparator = new Comparator<MazeState>() {
            @Override
            public int compare(MazeState s1, MazeState s2) {
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

        heap = new PriorityQueue(Comparator);

    }

    @Override
    public Solution solve(ISearchable SearchableMaze) {
        return null;
    }


    /**
     * The function returns the number of vertices developed by an algorithm
     * @return
     * @Override
     */

    public int getNumberOfNodesEvaluated() {

        return 0;
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
