package test;

import Client.*;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import Server.*;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;


public class RunCommunicateWithServers {

    public static void main(String[] args) {
        /*Initializing servers*/
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        Server stringReverserServer = new Server(5402, 1000, new ServerStrategyStringReverser());

        /*Starting servers*/
        mazeGeneratingServer.start();
        solveSearchProblemServer.start();
        //stringReverserServer.start();

        /*Communicating with servers*/
        //CommunicateWithServer_MazeGenerating(50, 50);
        //CommunicateWithServer_SolveSearchProblem();
        //CommunicateWithServer_StringReverser();

        //anotherTestMazeGenerating();



        /*Stopping all servers*/
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
        //stringReverserServer.stop();
    }

    public static void anotherTestMazeGenerating() {
        int j = 10;
        for (int i = 11; i <= 1000; i += 10) {
            for (; j <= 1000; j += Math.random() * 100) {
                int row = i + (int) (Math.random() * 10);
                System.out.println("row = "+row+", col = "+j);
                try{
                    CommunicateWithServer_MazeGenerating(row, j);
                }catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }
    }




    private static void CommunicateWithServer_MazeGenerating(int row, int col) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                        try {
                            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                            toServer.flush();
                            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                            int[] mazeDimensions = new int[]{row, col};
                            toServer.writeObject(mazeDimensions); //send maze dimensions to server
                            toServer.flush();
                            byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                            InputStream is = new MyDecompressorInputStream(new
                                    ByteArrayInputStream(compressedMaze));
                            //CHANGE SIZE ACCORDING TO YOU MAZE SIZE
                            int numOfRows = row;
                            int numOfCols = col;
                            if(numOfRows <= 0)
                                numOfRows = 10;
                            //Default values
                            if(numOfCols <= 0)
                                numOfCols = 10;
                            if ((numOfRows ==2 && numOfCols ==2) || (numOfRows ==1 && numOfCols ==1) ||(numOfRows ==0 && numOfCols ==1) ||(numOfRows ==1 && numOfCols ==0)
                                    ||(numOfRows ==1 && numOfCols ==2) ||(numOfRows ==2 && numOfCols ==1) ||(numOfRows ==2 && numOfCols ==3 ) ||(numOfRows ==3 && numOfCols ==2 )
                                    ||(numOfRows ==3 && numOfCols ==3 )) {
                                numOfRows = 3;
                                numOfCols = 4;
                            }
                            byte[] decompressedMaze = new byte[numOfRows*numOfCols+30];
                            //allocating byte[] for the decompressed maze -
                            is.read(decompressedMaze); //Fill decompressedMaze with bytes
                            Maze maze = new Maze(decompressedMaze);
                            if (maze.getNumberOfRows()!=numOfRows || maze.getNumberOfColumns()!=numOfCols)
                                throw new Exception("Wrong number of rows or columns");
                            //maze.print();
                        } catch (Exception e) {
                            //e.printStackTrace();
                            System.out.println(e);
                        }
                    }
                });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void CommunicateWithServer_SolveSearchProblem() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new
                    IClientStrategy() {
                        @Override
                        public void clientStrategy(InputStream inFromServer,
                                                   OutputStream outToServer) {
                            try {
                                ObjectOutputStream toServer = new
                                        ObjectOutputStream(outToServer);
                                ObjectInputStream fromServer = new
                                        ObjectInputStream(inFromServer);
                                toServer.flush();
                                MyMazeGenerator mg = new MyMazeGenerator();
                                Maze maze = mg.generate(50, 50);
                                maze.print();
                                toServer.writeObject(maze); //send maze to server
                                toServer.flush();
                                Solution mazeSolution = (Solution)
                                        fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                                //Print Maze Solution retrieved from the server
                                System.out.println(String.format("Solution steps: %s", mazeSolution));
                                ArrayList<AState> mazeSolutionSteps =
                                        mazeSolution.getSolutionPath();
                                for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                                    System.out.println(String.format("%s. %s", i,
                                            mazeSolutionSteps.get(i).toString()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void CommunicateWithServer_StringReverser() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5402, new
                    IClientStrategy() {
                        @Override
                        public void clientStrategy(InputStream inFromServer,
                                                   OutputStream outToServer) {
                            try {
                                BufferedReader fromServer = new BufferedReader(new
                                        InputStreamReader(inFromServer));
                                PrintWriter toServer = new PrintWriter(outToServer);
                                String message = "Client Message";
                                String serverResponse;
                                toServer.write(message + "\n");
                                toServer.flush();
                                serverResponse = fromServer.readLine();
                                System.out.println(String.format("Server response: %s", serverResponse));
                                toServer.flush();
                                fromServer.close();
                                toServer.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
