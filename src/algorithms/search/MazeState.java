package algorithms.search;

/**
 * A department that inherits ASstate and its role in stabilizing a specific maze that will help
 * us solve the problem.
 * Let's define the situation as a point in the maze.
 */
public class MazeState extends  AState {

    private int row;
    private int col;
    private int Priority;

    /**
     * A constructor that accepts the index of the row and column
     * @param row
     * @param col
     */
    public MazeState(int row, int col) {
        super();
        if(row > 0 && col > 0){
            this.row = row;
            this.col = col;
            this.cost =10;
            Priority = 0;
        }

    }

    public MazeState(int row, int col,int Priority) {
        super();
        if(row > 0 && col > 0 && (Priority == 0 || Priority == 10 || Priority == 15) ){
            this.row = row;
            this.col = col;
            this.cost =10;
            this.Priority = Priority;
        }

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
     *
     * @param parent the parents to set for the state
     */
    @Override
    public void setParent(AState parent) {
        if (parent instanceof MazeState) {
            this.parent = parent;
        }

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
     * A function that returns the priority of the given point
     * @return int Priority
     */
    public int getPriority() {
        return Priority;
    }


    /**
     * A function that compares two points according to their priority
     * @param obj
     * @return int
     */
    public int compareTo(AState obj){
        if(obj instanceof AState){
            if(this.Priority > ((MazeState)obj).Priority ){
                return 1;
            }
            else if(this.Priority == ((MazeState)obj).Priority ){
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
        return "{ " + row + ", " + col +"}";
    }
}
