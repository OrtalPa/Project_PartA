package algorithms.mazeGenerators;

public class Main {

    public static void main (String args[]){

        //testByteArray();
        //testByteArray256();
        testToCconstructor();
    }

    private static void testToCconstructor() {
        byte[] array = {-1,-1,-1,-1,0,-1,-1,-1,-1,1,-1,-1,-1,-1,1,-1,-1,-1,-1,0,-1,-1,-1,0,3,0,0,0,0,3,1,0,1,0,1,1,1,0,0};
        Maze Maze = new Maze(array);
        System.out.println(Maze.getGoalPosition()); //end (1,0)
        System.out.println(Maze.getStartPosition()); //start(0,1)
        System.out.println(Maze.getNumberOfRows()); //row(3)
        System.out.println(Maze.getNumberOfColumns()); //col(3)
        Maze.print();
    }


    public static void testByteArray(){

        System.out.println("Test TO Byte Array:");
        MyMazeGenerator m = new MyMazeGenerator();
        Maze maze = (m).generate(10,10);
        maze.print();
        System.out.println("");
        System.out.println("The Byte array of maze:");
        byte[] ansToByteArray = maze.toByteArray();
        for(int i=0; i<ansToByteArray.length; i++){
            if(i == ansToByteArray.length - 1){
                System.out.print(ansToByteArray[i]);
            }
            else{
                System.out.print(ansToByteArray[i] + " , ");
            }
        }
        System.out.println("");

    }//testOfSpreadingTheArray




  /*  public static void testByteArray256(){
        System.out.println("Test TO Byte Array 256:");
        int[][] maze256 = new int[1][260];
        for (int i = 0; i < 260 ; i++) {
            maze256[0][i] = 1;
        }
        maze256[0][258] = 0;
        maze256[0][259] = 0;
        Maze Maze = new Maze(maze256, new Position(0,258),new Position(0,259));
        Maze.print();

        System.out.println("The Byte array of maze:");
        byte[] ansToByteArray = Maze.toByteArray();
        for(int i=0; i<ansToByteArray.length; i++){
            if(i == ansToByteArray.length - 1){
                System.out.print(ansToByteArray[i]);
            }
            else{
                System.out.print(ansToByteArray[i] + " , ");
            }

        }

    }*/


}

