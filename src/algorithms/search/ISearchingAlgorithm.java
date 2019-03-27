package algorithms.search;

public interface ISearchingAlgorithm {

     Solution solve(ISearchable SearchableMaze);

     //The function returns the name of the algorithm
     String getName();

     //The function returns the number of vertices developed by an algorithm
     int getNumberOfNodesEvaluated();

}



