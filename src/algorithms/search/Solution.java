package algorithms.search;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution implements Serializable {


    protected ArrayList<AState> pathSolution;


    public  Solution (AState StateEnd){
        pathSolution = new ArrayList<>();
        while(StateEnd != null){
            pathSolution.add(0,StateEnd);
            StateEnd = StateEnd.getParent();
            }
    }


    public ArrayList<AState> getSolutionPath(){
        return pathSolution;
    }




}
