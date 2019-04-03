package algorithms.test;

import algorithms.mazeGenerators.*;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;
import org.junit.jupiter.api.Test;

import static com.sun.javafx.fxml.expression.Expression.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {


    //The AssertNotNull() method means "a passed parameter must not be null": if it is null then the test case fails.

    BestFirstSearch BestFirstSearch = new BestFirstSearch();


    /*
    The test checks that if the algorithm receives a null it does not return a solution
     */
    @Test
    void solve() {

        assertEquals(BestFirstSearch.solve(null),null);
    }

    /**
     * The department SearchableMaze receives an empty maze(null) and the solution should return a list of empty nodes == null
     */
    @Test
    void solveMazwNull() {

        Maze MazeNull = new Maze(null,new Position(0,0), new Position(1,1));
        SearchableMaze SearchableMaze = new SearchableMaze(MazeNull);
        //The AssertNull() method means "a passed parameter must be null": if it is not null then the test case fails.
        assertNull(BestFirstSearch.solve(SearchableMaze));

    }

    /**
     * The department SearchableMaze receives an empty maze(null) and the solution should return a list of empty nodes == null
     */
    @Test
    void solveMazwPonitIllegal() {
        int[][] ArrayMaze = {{0,0},{1,0}};
        Maze MazeNull = new Maze(ArrayMaze,new Position(0,-2), new Position(1,1));
        SearchableMaze SearchableMaze = new SearchableMaze(MazeNull);
        //The AssertNull() method means "a passed parameter must be null": if it is not null then the test case fails.
        assertNull(BestFirstSearch.solve(SearchableMaze));
    }

    /**
     * The test checks that the time to solve the labyrinth is not more than a minute
     */
    @Test
    void solveTimeTest() {
        solveTime(new EmptyMazeGenerator());
        solveTime(new SimpleMazeGenerator());
        solveTime(new MyMazeGenerator());
    }


     void solveTime(IMazeGenerator MazeGenerator) {
        Maze maze = MazeGenerator.generate(1000/*rows*/, 1000/*columns*/);
        SearchableMaze SearchableMaze = new SearchableMaze(maze);
        long startTime = System.currentTimeMillis();
        BestFirstSearch.solve(SearchableMaze);
        long endTime = System.currentTimeMillis();
        ////assertTrue will fail if the First parameter evaluates to false
        assertTrue(endTime- startTime <= 60000, "Error, The maze solution takes more than a minute");
    }

    /**
     * The test verifies that the number of vertices developed is not negative
     */
    @Test
    void getNumberOfNodesEvaluatedTest() {
        getNumberOfNodesEvaluated(new EmptyMazeGenerator());
        getNumberOfNodesEvaluated(new SimpleMazeGenerator());
        getNumberOfNodesEvaluated(new MyMazeGenerator());
    }


    void getNumberOfNodesEvaluated(IMazeGenerator MazeGenerator) {
        Maze maze = MazeGenerator.generate(1000/*rows*/, 1000/*columns*/);
        SearchableMaze SearchableMaze = new SearchableMaze(maze);
        BestFirstSearch.solve(SearchableMaze);
        //assertTrue will fail if the First parameter evaluates to false
        assertTrue(BestFirstSearch.getNumberOfNodesEvaluated()>0, "Error, The number of vertices developed can not be negative");

    }

    /**
     * The test verifies that the class name is correct
     */
    @Test
    void getName() {

        assertEquals(BestFirstSearch.getName(),"Best First Search");
    }
}