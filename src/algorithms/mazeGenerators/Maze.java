package algorithms.mazeGenerators;


import java.util.ArrayList;

/**
 * This class represents a maze
 */

public class Maze {

    private int[][] m_maze;
    private Position m_start;
    private Position m_end;

    /**
     * The class constructor that accepts the row size and column size
     * @param m_maze
     * @param m_start
     * @param m_end
     */
    public Maze(int[][] m_maze, Position m_start, Position m_end)
    {
        //The integrity of the input
        if(m_start.getColumnIndex() <  m_maze[0].length && m_end.getColumnIndex()< m_maze[0].length
        && m_start.getRowIndex() < m_maze.length &&  m_end.getRowIndex() < m_maze.length
        && m_start.getRowIndex() >= 0 &&  m_end.getRowIndex() >=0 && m_start.getColumnIndex() >=0 && m_end.getColumnIndex() >=0 ){
            this.m_maze = m_maze;
            this.m_start = m_start;
            this.m_end = m_end;
        }
        else{
            int[][] arrayMaze = {{0,1,1,1} , {0,1,1,1,},{0,0,1,1},{1,0,0,0}};
            m_maze =arrayMaze;
            m_start = new Position(0,0);
            m_end = new Position(3,3);
        }

    }

    /**
     *
     * @param arrayByte
     */
    public Maze(byte[] arrayByte){






    }

    /**
     * A function that transforms the array into a one - dimensional array of byte
     * @return byte[]
     */
    public byte[] toByteArray(){

        byte[] ans = new byte[this.getNumberOfRows()*this.getNumberOfColumns()];
        int count = 0;
        for (int i = 0; i < m_maze.length; i++) {
            for (int j = 0; j < m_maze[0].length; j++) {
                Integer integer = m_maze[i][j];
                ans[count] =(byte)integer.byteValue();
                count++;
            }//for2
        }//for1
        return ans;
    }


    /**
     * Returns the starting point
     * @return start position
     */
    public Position getStartPosition() {
        return new Position(m_start);
    }

    /**
     * Returns the end point
     * @return end position
     */
    public Position getGoalPosition() {
        return new Position(m_end);
    }

    /**
     *
     * The function returns the length of the rows
     * @return  int
     */
    public int getNumberOfRows(){
        return m_maze.length;
    }

    /**
     *
     * The function returns the length of the columns
     * @return int
     */
    public int getNumberOfColumns(){

        return m_maze[0].length;
    }


    /**
     *The function returns the cell value
     * @return int value of the cell
     */
    public int getValue(int row,int col){
        if(row >= this.getNumberOfRows() || col >= this.getNumberOfColumns() || row <0 || col < 0){
            return -1;
        }
        return m_maze[row][col];
    }

    /**
     * A function that prints the maze
     */
    public void print()
    {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String s =  "";

        for (int i = 0; i < m_maze.length; i++)
        {
            //s = s + +i+")";
            s = s + "{";
            for(int j = 0; j < m_maze[0].length; j++)
            {
                if (j!=0)
                    s = s + " ";
                if (getStartPosition().getRowIndex() == i && getStartPosition().getColumnIndex() == j)
                    s = s + "S";
                else if (getGoalPosition().getRowIndex() == i && getGoalPosition().getColumnIndex() == j)
                    s = s + "E";
                else
                    s = s + m_maze[i][j];
            }
            s = s + "}";
            if (i!= getNumberOfRows()-1)
                s = s + ",\n";
            else
                s = s +"\n";
        }
        /*
         s = s+ "m_start=" + m_start +
                ", m_end=" + m_end +
                '}';*/

        return s;
    }

}
