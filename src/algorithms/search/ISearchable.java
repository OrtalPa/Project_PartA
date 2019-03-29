package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
//An interface of an object to search in
public interface ISearchable {

    //The function returns the route as a solution to the maze
    ArrayList<MazeState> getSolutionPath();

    /**
     *This function gets a point and a maze and returns a list with
     * all the points that can be accessed from the point
     *
     */
    ArrayList<AState> getAllPossibleStates(AState state);

    //The function returns the starting point
    AState getStart();

    //The function returns the end point
    AState getEnd();

    void setStateAsVisited(AState state);

    boolean getStateAsVisited(AState state);
}
