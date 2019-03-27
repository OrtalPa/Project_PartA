package algorithms.mazeGenerators;

public interface IMazeGenerator {

    //creates a maze
    Maze generate (int row, int column);
    //measures how long it takes to create a maze
    long measureAlgorithmTimeMillis(int row, int column);

}
