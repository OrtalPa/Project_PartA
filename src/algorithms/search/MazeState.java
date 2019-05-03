package algorithms.search;



/**
 * A department that inherits ASstate and its role in stabilizing a specific maze that will help
 * us solve the problem.
 * Let's define the situation as a point in the maze.
 */
public class MazeState extends  AState {

    private int row;
    private int col;

    /**
     * A constructor that accepts the index of the row and column
     * @param row
     * @param col
     */
    public MazeState(int row, int col) {
        super();
        if(row >= 0 && col >= 0){
            this.row = row;
            this.col = col;
        }

    }

    public MazeState(int row, int col,int cost) {
        super(cost);
        this.row = row;
        this.col = col;

    }


    /**
     * Returns the index of the row
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the index of the column
     */
    public int getCol() {
        return col;
    }


    /**
     * A function that compares two points according to the indexes of the row and the column
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MazeState){
            if(this.row == ((MazeState)obj).row && this.col == ((MazeState)obj).col){
                return true;
            }
        }
        return false;
    }


    /**
     * Function hash
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 23;
        hash = hash * 31 + row;
        hash = hash * 31 + col;
        return hash;



    }

    /**
     * A function that compares two points according to their cost
     * @param obj
     * @return int
     */
    public int compareTo(AState obj){
        if(obj instanceof AState){
            if(this.cost > ((MazeState)obj).cost ){
                return 1;
            }
            else if(this.cost == ((MazeState)obj).cost ){
                return 0;
            }
            else{
                return -1;
            }
        }
        return -1;
    }

    /**
     * A function that defines how to print the point
     * @return
     */
    @Override
    public String toString() {
        return "{" + row + "," + col +"}";
    }
}
