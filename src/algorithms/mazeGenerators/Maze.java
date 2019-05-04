package algorithms.mazeGenerators;


import java.io.Serializable;

/**
 * This class represents a maze
 */

public class Maze implements Serializable {

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

        //start point
        int[] rowStart = new int[5];
        for (int i = 0; i < 5; i++) {
            rowStart[i] = arrayByte[i];
        }
       int rowStartnum =unitesTheNumber(rowStart);

        int[] colStart = new int[5];
        for (int i = 0; i < 5; i++) {
            colStart[i] = arrayByte[i+5];
        }
        int colStartnum =unitesTheNumber(colStart);
        m_start = new Position(rowStartnum,colStartnum);

        //end point
        int[] rowEnd = new int[5];
        for (int i = 0; i < 5; i++) {
            rowEnd[i] = arrayByte[i+10];
        }
        int rowEndnum =unitesTheNumber(rowEnd);

        int[] colEnd = new int[5];
        for (int i = 0; i < 5; i++) {
            colEnd[i] = arrayByte[i+15];
        }
        int colEndnum =unitesTheNumber(colEnd);
        m_end = new Position(rowEndnum,colEndnum);

        //row Number
        int[] rowNum = new int[5];
        for (int i = 0; i < 5; i++) {
            rowNum[i] = arrayByte[i+20];
        }
        int rowNUmberOfMaze =unitesTheNumber(rowNum);

        //col Number
        int[] colNum = new int[5];
        for (int i = 0; i < 5; i++) {
            colNum[i] = arrayByte[i+25];
        }
        int colNUmberOfMaze =unitesTheNumber(colNum);

        m_maze = new int[rowNUmberOfMaze][colNUmberOfMaze];

        int counter =30;
        for (int i = 0; i < m_maze.length; i++) {
            for (int j = 0; j < m_maze[0].length; j++) {
                m_maze[i][j] = arrayByte[counter];
                counter++;
            }
        }
    }

    /**
     * A function that transforms the array into a one - dimensional array of byte
     * @return byte[]
     */
    public byte[] toByteArray(){
        byte[] ans = new byte[this.getNumberOfRows()*this.getNumberOfColumns() +30];
        Integer integer = 0;
        int[] temp = null;

        int RowStart   =m_start.getRowIndex();//0-4 start
        temp = SeparatesTheNumber(RowStart);
        for (int i = 0; i <5 ; i++) {
            integer = temp[i];
            ans[i] = integer.byteValue();
        }

        int ColStart  = m_start.getColumnIndex();//5-9 start
        temp = SeparatesTheNumber(ColStart);
        int k=0;
        for (int i = 5; i <10 ; i++) {
            integer = temp[k];
            k++;
            ans[i] = integer.byteValue();
        }

        int RowEnd   =m_end.getRowIndex();//10-14 end
        temp = SeparatesTheNumber(RowEnd);
        k=0;
        for (int i = 10; i <15 ; i++) {
            integer = temp[k];
            k++;
            ans[i] = integer.byteValue();
        }

        int ColEnd  = m_end.getColumnIndex();//15-19 end
        k=0;
        temp = SeparatesTheNumber(ColEnd);
        for (int i = 15; i <20 ; i++) {
            integer = temp[k];
            k++;
            ans[i] = integer.byteValue();
        }

        int numOfRow = this.getNumberOfRows();//20-24 numOfRow
        k=0;
        temp = SeparatesTheNumber(numOfRow);
        for (int i = 20 ;i <25 ; i++) {
            integer = temp[k];
            k++;
            ans[i] = integer.byteValue();
        }

        int numOfcOL = this.getNumberOfColumns(); // 25 -29 numOfCol
        temp = SeparatesTheNumber(numOfcOL);
        k=0;
        for (int i = 25 ;i <30 ; i++) {
            integer = temp[k];
            k++;
            ans[i] = integer.byteValue();
        }

        int count = 30;
        for (int i = 0; i < m_maze.length; i++) {
            for (int j = 0; j < m_maze[0].length; j++) {
                Integer integerMaze = m_maze[i][j];
                ans[count] =(byte)integerMaze.byteValue();
                count++;
            }//for2
        }//for1
        return ans;
    }

    private int[] SeparatesTheNumber(int num){
        int[] ans = new int[5];
        for (int i = 0; i <5 ; i++) {
            ans[i] = -1;
        }
        int count = 4;
        if(num == 0){
            ans[0] =-1;
            ans[2] =-1;
            ans[2] =-1;
            ans[3] =-1;
            ans[4] =0;
            return ans;
        }
        while(num > 0){
            ans[count] = num%10;
            count--;
            num = num/10;
        }
        return ans;
    }

    /**
     * A function that unites the number
     */
    private int unitesTheNumber(int[] array){
        int num = 0;
        int count = 10000;
        for (int i = 0; i < 5; i++) {
            if(array[i] != -1){
                num = num + array[i]*count;
            }
            count =count/10;

        }
        return num;

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
