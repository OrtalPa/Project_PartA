package algorithms.mazeGenerators;



import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int column) {

        //Default values
        if(row <= 0){
            row = 10;

        }

        //Default values
        if(column <= 0){
            column = 10;
        }
        if ((row ==2 && column ==2) || (row ==1 && column ==1) ||(row ==0 && column ==1) ||(row ==1 && column ==0)
                ||(row ==1 && column ==2) ||(row ==2 && column ==1)) {
            int[][] arrayMaze = {{0,1,1,1} , {0,1,1,1,},{0,0,1,1},{1,0,0,0}};
            return new Maze(arrayMaze,new Position(0,0),new Position(3,3));
        }
        /*
         * 1. find random entry
         * 2. find random path in maze until getting to the other side (?)
         * 3. save end position
         * 4. change to -1
         * 5. randomly put 1 or 0 if not -1
         * 6. switch all -1 to 0
         */

        //maze filled with 0
        int[][] maze = new int [row][column];
        //random entry
        Random rand = new Random();
        int startRow = rand.nextInt(row);
        int startCol = rand.nextInt(column);
        /*
        int startCol = 0;
        if (startRow != 0 && startRow != row-1) //one of the middle rows
            startCol = rand.nextInt(1);
        else //first or last row
            startCol = rand.nextInt(column);
*/
        Position start = new Position(startRow,startCol);

        //finding a random path in the maze
        int i = startRow;
        int j = startCol;
        int countSteps = 0;
        maze[i][j] = -1;
        //the path will have at least 3 steps
        Position end = new Position(startRow,startCol); //default value
        while (countSteps < 4  || !onFrame(i,j,row,column))
        {
            do
            {//find a step within the boundaries of the maze
                int turn = rand.nextInt(4);
                if (turn == 0 && i < row-1)
                    i++;
                else if (turn == 1 && i > 0)
                    i--;
                else if (turn == 2 && j > 0)
                    j--;
                else if (turn == 3 && j < row-1)
                    j++;
            }while (!withinMaze(i,j,row,column));
            if (i != startRow && j != startCol) {
                maze[i][j] = -1;
                countSteps++;
                end = new Position(i,j);
                //last position will be the end position
            }
        }

        //    testMaze(maze, start, end);

        //randomly put walls
        for(i = 0 ; i < row; i++)
        {
            for (j = 0; j < column; j++)
            {
                if (maze[i][j] != -1)
                {
                    maze[i][j] = (int)(Math.random()*2);//rand.nextInt(1);
                }
            }
        }

        // testMaze(maze, start, end);
        //mark the path, back to 0
        for(i = 0 ; i < row; i++)
        {
            for (j = 0; j < column; j++)
            {
                if (maze[i][j] == -1)
                {
                    maze[i][j] = 0;
                }
            }
        }
        return new Maze(maze,start,end);
    }

    private void testMaze(int[][] maze, Position start, Position end) {
        Maze test = new Maze(maze, start,end);
        System.out.println(test);
    }

    private boolean onFrame(int i, int j, int row, int column) {
        return (i == row-1 || i == 0 || j == 0 || j == column-1);
    }

    private boolean withinMaze(int i, int j,int row, int column) {
        return (i <= row - 1 && j <= column - 1 && i >= 0 && j >= 0);
    }
}
