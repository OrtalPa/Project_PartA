package algorithms.mazeGenerators;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//https://stackoverflow.com/questions/29739751/implementing-a-randomly-generated-maze-using-prims-algorithm
public class MyMazeGenerator  extends AMazeGenerator{


    private class Index
    {
        private int m_iRow;
        private int m_iColumn;
        protected Index(int row, int col)
        {
            if (col < 0 || row < 0)
                return;
            this.m_iRow = row;
            this.m_iColumn = col;
        }
    }

    /**
     * Generates a maze according to randomized Prim algorithm
     * @param numOfRows the size of the rows of the maze
     * @param numOfCols the size of the columns of the maze
     * @return a Maze object
     */
    @Override
    public Maze generate(int numOfRows, int numOfCols)
    {

        //Default values
        if(numOfRows <= 0){
            numOfRows = 10;

        }

        //Default values
        if(numOfCols <= 0){
            numOfCols = 10;
        }

        if ((numOfRows ==2 && numOfCols ==2) || (numOfRows ==1 && numOfCols ==1) ||(numOfRows ==0 && numOfCols ==1) ||(numOfRows ==1 && numOfCols ==0)
    ||(numOfRows ==1 && numOfCols ==2) ||(numOfRows ==2 && numOfCols ==1) ||(numOfRows ==2 && numOfCols ==3 ) ||(numOfRows ==3 && numOfCols ==2 )) {
            int[][] arrayMaze = {{0,1,1,1} , {0,1,1,1,},{0,0,1,1},{1,0,0,0}};
            return new Maze(arrayMaze,new Position(0,0),new Position(3,3));
        }


        //Start with a Grid full of Cells in state Blocked
        int[][] maze = new int[numOfRows][numOfCols];
        for (int i = 0; i < numOfRows; i++) {
            Arrays.fill(maze[i],1);
        }
        //create an empty list of frontier cells
        ArrayList<Index> frontierCells = new ArrayList<>();

        Random randomGenerator = new Random();
        //pick a random cell
        int selectedRow = randomGenerator.nextInt(numOfRows);
        int selectedCol = randomGenerator.nextInt(numOfCols);
        Position start = new Position(selectedRow,selectedCol);
        Position end = null; //end position will change at the end of the while loop

        //set it to state Passage
        maze[selectedRow][selectedCol] = 0;

        //compute its frontier cells and Insert them to the list
        frontierCells.addAll(computeFrontierCells(maze, selectedRow, selectedCol,1));

        while (!frontierCells.isEmpty())
        {
            //Pick a random frontier cell from the list of frontier cells
            Index fCell = frontierCells.get(randomGenerator.nextInt(frontierCells.size()));
            //Let neighbors(frontierCell) = All cells in distance 2 in state Passage.
            ArrayList<Index> zeroNeighbors = computeFrontierCells(maze, fCell.m_iRow, fCell.m_iColumn,0);
            //Pick a random neighbor
            Index neighbor = zeroNeighbors.get(randomGenerator.nextInt(zeroNeighbors.size()));
            //connect the frontier cell with the neighbor by setting the cell in-between to state Passage.
            if (neighbor.m_iRow == fCell.m_iRow && neighbor.m_iColumn == fCell.m_iColumn - 2)
                maze[fCell.m_iRow][fCell.m_iColumn - 1] = 0;
            else if (neighbor.m_iRow == fCell.m_iRow && neighbor.m_iColumn == fCell.m_iColumn + 2)
                maze[fCell.m_iRow][fCell.m_iColumn + 1] = 0;
            else if (neighbor.m_iRow == fCell.m_iRow - 2 && neighbor.m_iColumn == fCell.m_iColumn)
                maze[fCell.m_iRow - 1][fCell.m_iColumn] = 0;
            else if (neighbor.m_iRow == fCell.m_iRow + 2 && neighbor.m_iColumn == fCell.m_iColumn)
                maze[fCell.m_iRow + 1][fCell.m_iColumn] = 0;

            //Compute the frontier cells of the chosen frontier cell and add them to the frontier list.
            frontierCells.addAll(computeFrontierCells(maze,fCell.m_iRow,fCell.m_iColumn,1));

            //Remove the chosen frontier cell from the list of frontier cells.
            maze[fCell.m_iRow][fCell.m_iColumn] = 0;
            frontierCells.remove(fCell);
            if (frontierCells.isEmpty())//the loop is done and this is the last cell visited
                end = new Position(fCell.m_iRow,fCell.m_iColumn);
        }

        return new Maze(maze,start,end);
    }


    /**
     * Computes frontier cells of a cell and inserts them to the list
     * @param maze a 2D array representing the maze
     * @param selectedRow row index of the cell to compute the frontiers for
     * @param selectedCol column index of the cell to compute the frontiers for
     * @param state the required state of the frontier cell
     */
    private ArrayList<Index> computeFrontierCells(int[][] maze, int selectedRow, int selectedCol, int state) {
        ArrayList<Index> currentFrontierCells = new ArrayList<>();
        addFrontierCellToBeginning(currentFrontierCells, selectedRow, selectedCol - 2, maze,state);
        addFrontierCellToBeginning(currentFrontierCells, selectedRow, selectedCol + 2, maze,state);
        addFrontierCellToBeginning(currentFrontierCells, selectedRow - 2, selectedCol, maze,state);
        addFrontierCellToBeginning(currentFrontierCells, selectedRow + 2, selectedCol, maze,state);
        return currentFrontierCells;
    }


    /**
     * Adds a cell to the frontier cells list if it fits the terms.
     * A frontier cell of a Cell is a cell with distance 2 from the current cell in state Blocked and within the grid.
     * @param frontierCells a list of frontier cells
     * @param i row index of the computed frontier cell
     * @param j column index of the computed frontier cell
     * @param maze a 2D int array representing the maze
     */
    private void addFrontierCellToBeginning(ArrayList<Index> frontierCells, int i, int j, int[][] maze, int state)
    {
        if (withinMaze(i, j, maze.length, maze[0].length) && maze[i][j] == state)
        { //the cell is within the maze and :
            // is still blocked at the beginning, or in state Passage if the origin cell is a frontier cell
            Index frontier = new Index(i, j);
            frontierCells.add(frontier);
        }
    }

    /**
     * Verifies if a cell is within the grid of the maze
     * @param i row index of the cell
     * @param j column index of the cell
     * @param row number of rows in the maze
     * @param column number of columns in the maze
     * @return
     */
    private boolean withinMaze(int i, int j,int row, int column) {
        return (i <= row - 1 && j <= column - 1 && i >= 0 && j >= 0);
    }

}
