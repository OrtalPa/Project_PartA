package algorithms.mazeGenerators;


import java.io.Serializable;

/**
 * A class representing a point in the maze by row and column index
 */
public class Position implements Serializable {

   private int m_row;
   private int m_column;

    /**
     * class constructor
     * @param m_row row index
     * @param m_column column index
     */
    public Position(int m_row, int m_column) {

        //The assumption situation can be negative
        this.m_row = m_row;
        this.m_column = m_column;
    }

    /**
     * copy constructor
     * @param p a position to copy
     */
    public Position (Position p)
    {
        if(p != null){
        m_column = p.m_column;
        m_row = p.m_row;
         }

    }

    /**
     * Returns the index of the row
     * @return int
     */
    public int getRowIndex() {
        return m_row;
    }

    /**
     * Returns the index of the column
     * @return int
     */
    public int getColumnIndex() {

        return m_column;
    }

    /**
     * A function that prints the position
     * @return
     */
    @Override
    public String toString() {

        return "{"+ m_row +","+ m_column +"}";
    }
}
