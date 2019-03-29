package algorithms.mazeGenerators;


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
        this.m_maze = m_maze;
        this.m_start = m_start;
        this.m_end = m_end;
    }

    /**
     * Returns the starting point
     * @return start position
     */
    public Position getStartPosition() {
        return new Position(m_start);
        //returning deep copy
    }

    /**
     * Returns the end point
     * @return end position
     */
    public Position getGoalPosition() {
        return new Position(m_end);
        //returning deep copy
    }

    /**
     *
     * The function returns the length of the rows
     * @return  int
     */
    public int getRowLength(){

        return m_maze.length;

    }

    /**
     *
     * The function returns the length of the columns
     * @return int
     */
    public int getColLength(){
        return m_maze[0].length;
    }


    /**
     *The function returns the cell value
     * @return int value of the cell
     */
    public int getValue(int row,int col){
        if(row >= this.getRowLength() || col >= this.getColLength() || row <0 || col < 0){
            return -1;
        }
        return m_maze[row][col];
    }

    public void print()
    {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String s =  "";

        for (int i = 0; i < m_maze.length; i++)
        {
            s = s + +i+")";
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
            if (i!=getRowLength()-1)
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
