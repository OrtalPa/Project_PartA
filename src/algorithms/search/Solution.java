package algorithms.search;


import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {


    ArrayList<AState> pathSolution;


    public  Solution (AState StateEnd){

        pathSolution = new ArrayList<>();
        pathSolution.add(StateEnd);
        while(StateEnd != null){
            if(StateEnd.getParent() != null){
                pathSolution.add(0,StateEnd.getParent());
            }
              StateEnd = StateEnd.getParent();



            }



    }


    public ArrayList<AState> getSolutionPath(){
        return pathSolution;
    }




}
