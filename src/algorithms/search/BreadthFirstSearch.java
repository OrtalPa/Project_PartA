package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm {


    PriorityQueue<AState> nodesList;
    //  PriorityQueue<MazeState> ans;
    //<AState> nodesList;
    //ArrayList<MazeState> ans;
    int countNodes;

    public  BreadthFirstSearch(){


        Comparator<AState> Comparator = new Comparator<AState>() {
            @Override
            public int compare(AState s1, AState s2) {
                return 0;
            }
        };
         nodesList = new PriorityQueue(Comparator);
        // ans =  new PriorityQueue(Comparator);

       //ans = new ArrayList<MazeState>();
        //nodesList = new ArrayList<>();
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

        //Adds the first point of the maze
        nodesList.add(start);
        AState StateNow =  start;
        countNodes++;

        //A loop that checks as long as we have points in queue and we not get to end
        while(nodesList.size() > 0 || !StateNow.equals(end)){

            //Only the points we remove are added to the ans
           // StateNow= nodesList.remove(0);

             StateNow= nodesList.remove();
            //You get a list of all the neighboring points to the current point
            ArrayList<MazeState> array =  SearchableMaze.getAllPossibleStates(StateNow);

            //As long as the list is not empty and we have not found the end point
            while(array.size() > 0 && !flagFound){
                if(!array.get(0).equals(end)){
                    AState state = array.remove(0);
                    if(!nodesList.contains(state)){
                        //Add the neighboring point to the list of points to check
                        state.setParent(StateNow);
                        nodesList.add(state);
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
                //ans.addAll(nodesList);
               //ans.add(array.remove(0));
               return new Solution(end);
            }
        }//while
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
        return "Breadth First Search";

    }

}
