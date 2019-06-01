/*

package test;

import IO.MyDecompressorInputStream;
import Server.*;
import Client.*;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.Solution;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


*/
/**
 * Created by Aviadjo on 3/27/2017.
 *//*



public class RunCommunicateWithServers {

    public static void main(String[] args) {
        //Initializing servers


        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());

        //Server stringReverserServer = new Server(5402, 1000, new ServerStrategyStringReverser());

        //Starting  servers
        solveSearchProblemServer.start();
        mazeGeneratingServer.start();

        Thread t1 = new Thread1();
        t1.start();
        Thread t2 = new Thread1();
        t2.start();
        Thread t3 = new Thread1();
        t3.start();
        Thread t4 = new Thread1();
        t4.start();
        Thread t5 = new Thread1();
        t5.start();
        Thread t6 = new Thread1();
        t6.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //CommunicateWithServer_MazeGenerating();
        //stringReverserServer.start();

        //Communicating with servers
        //CommunicateWithServer_SolveSearchProblem();
        //CommunicateWithServer_StringReverser();

        //Stopping all servers
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
        //stringReverserServer.stop();
    }


    protected static void CommunicateWithServer_MazeGenerating() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int[] mazeDimensions = new int[]{10, 10};
                        toServer.writeObject(mazeDimensions); //send maze dimensions to server
                        toServer.flush();
                        byte[] compressedMaze = null;
                        try{
                            compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server

                        }
                        catch(OptionalDataException e){
                            System.out.println(e.eof + "," + e.length);
                        }
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[30+10*10];
*/
/*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*//*


//allocating byte[] for the decompressed maze -
                        is.read(decompressedMaze); //Fill decompressedMaze with bytes
                        Maze maze = new Maze(decompressedMaze);
                        maze.print();
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

    protected static void CommunicateWithServer_SolveSearchProblem() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {

                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();

                        String tempDirectoryPath = System.getProperty("java.io.tmpdir");


                        MyMazeGenerator mg = new MyMazeGenerator();
                        Maze maze = mg.generate(10, 10);
                        maze.print();
                        toServer.writeObject(maze); //send maze to server
                        toServer.flush();
                        Solution mazeSolution = (Solution) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server

                        //Print Maze Solution retrieved from the server
                        System.out.println(String.format("Solution steps: %s", mazeSolution));
                        ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
                        for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                            System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
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
            Client client = new Client(InetAddress.getLocalHost(), 5402, new IClientStrategy() {

                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        BufferedReader fromServer = new BufferedReader(new InputStreamReader(inFromServer));
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

*/

package test;

import Client.*;
import IO.MyDecompressorInputStream;
import Server.*;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class RunCommunicateWithServers {

    public static void main(String[] args) {

        /*Initializing servers*/

        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        //Server stringReverserServer = new Server(5402, 1000, new ServerStrategyStringReverser());
        /*test 1
        mazeGeneratingServer.start();
        solveSearchProblemServer.start();
        solveSavedMazesParallel();
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
        */

/*

        mazeGeneratingServer.start();
        solveSearchProblemServer.start();
        CommunicateWithServer_MazeGenerating();
        CommunicateWithServer_SolveSearchProblem();
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
        */





        mazeGeneratingServer.start();
        solveSearchProblemServer.start();
        CommunicateWithServer_MazeGenerating(50,50);
        CommunicateWithServer_MazeGenerating(111,111);
        CommunicateWithServer_MazeGenerating(76,80);
        CommunicateWithServer_MazeGenerating(25,80);
        //CommunicateWithServer_MazeGenerating(50,20);
        CommunicateWithServer_MazeGenerating(100,90);
        CommunicateWithServer_MazeGenerating(20,20);
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();




/*  mazeGeneratingServer.start();
        solveSearchProblemServer.start();
        CommunicateWithServer_MazeGenerating();
        CommunicateWithServer_SolveSearchProblem();
       // CommunicateWithServer_StringReverser();

/*Stopping all servers*/



        //  mazeGeneratingServer.start();*//*

        //solveSearchProblemServer.start();
        // CommunicateWithServer_MazeGenerating();
        // CommunicateWithServer_SolveSearchProblem();
        // mazeGeneratingServer.stop();
        // solveSearchProblemServer.stop();
    }// main

    private static void solveSavedMazesParallel() {
        ArrayList<Maze> mazes = new ArrayList<>();
        MyMazeGenerator generator = new MyMazeGenerator();
        for (int i = 100; i < 400; i+=100) {
            System.out.println(i);
            mazes.add(generator.generate(i,i+5));
        }
        solvingThreads(mazes);//saving to disk
        solvingThreads(mazes);//reading solutions from disk
    }

    private static void solvingThreads(ArrayList<Maze> mazes) {
        ArrayList<Thread> threadList = new ArrayList<>();
        for (Maze m :mazes) {
            Thread t = new Thread(() -> CommunicateWithServer_SolveSavedSearchProblem(m));
            threadList.add(t);
        }
        for (Thread t:threadList) {
            t.start();
        }
        for (Thread t:threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void CommunicateWithServer_MazeGenerating(int x, int y) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        toServer.flush();
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);

                        int[] mazeDimensions = new int[]/*{x, y};*/{x,y};
                        toServer.writeObject(mazeDimensions); //send maze dimensions to server
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                        InputStream is = new MyDecompressorInputStream(new
                                ByteArrayInputStream(compressedMaze));
                        //CHANGE SIZE ACCORDING TO YOU MAZE SIZE
                        byte[] decompressedMaze = new byte[x*y+30];
                        //allocating byte[] for the decompressed maze -
                        is.read(decompressedMaze); //Fill decompressedMaze with bytes
                        Maze maze = new Maze(decompressedMaze);
                        maze.print();
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


    private static void CommunicateWithServer_SolveSavedSearchProblem(Maze maze) {
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
                                Maze maze = mg.generate(1000, 1000);
                                //maze.print();
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




