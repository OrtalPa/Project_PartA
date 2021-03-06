package algorithms.search;



import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
//An interface of an object to search in
public interface ISearchable {

    /**
     *This function gets a point and a maze and returns a list with
     * all the points that can be accessed from the point
     *
     */
    ArrayList<AState> getAllPossibleStates(AState state);

    /**
     * The function returns the starting point
     * @return AState - starting point
     */

    AState getStart();

    /**
     * The function returns the end point
     * @return AState - the ending point
     */
    AState getEnd();



}
