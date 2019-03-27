package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class DepthFirstSearch extends ASearchingAlgorithm {
   // Stack<AState> stackOfNodes;
    AState startPoint;
    AState endPoint;
    int countNodes;

    public  DepthFirstSearch()
    {
        countNodes = 0;
       // stackOfNodes = new Stack<>();
    }


    @Override
    public Solution solve(ISearchable SearchableMaze) {


        //Think about whether it will be in our interface or that we will need to be converted
        // add by shir
      //  ListOfAllPoints = ((SearchableMaze)SearchableMaze).ListOfAllPoints();

        startPoint = SearchableMaze.getStart();
        endPoint = SearchableMaze.getEnd();

       // DepthFirstSearch(startPoint,SearchableMaze);
        return null;

    }


    public AState DepthFirstSearch(MazeState State,ISearchable SearchableMaze){

        return null;
    }

    /*

    // add by shir
    public int CheckIfVisited(int numRow,int numCol){
        return ListOfAllPoints[numRow][numCol];
    }


    // add by shir
    public void setVisit(int numRow,int numCol){
        ListOfAllPoints[numRow][numCol] = 1;
    }
*/
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
