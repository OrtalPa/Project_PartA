package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem  implements IServerStrategy{

    private static int count=0;


    @Override
    public void serverStrategy(InputStream inputStream, OutputStream outputStream) {

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            Maze mazeFromClient = (Maze)fromClient.readObject();
            String tempFolder = System.getProperty("java.io.tmpdir");


            //search if the maze exists
            byte [] byteMazeFromClient = mazeFromClient.toByteArray();
            boolean stop = false;
            int i = 0;
            for (; i < count && !stop; i++) {
                Path fileLocation = Paths.get("tempFolder"+"/m"+i); //.txt???
                byte[] currByteArray = Files.readAllBytes(fileLocation);
                stop = Arrays.equals(currByteArray,byteMazeFromClient);
            }

            //if exists - return solution
            if (stop)
            {
                i--;
                Path fileLocation = Paths.get("tempFolder"+"/s"+i); //.txt???

                BufferedReader br=new BufferedReader(new FileReader("tempFolder"+"/s"+i));
                String x=br.readLine(); //it return second line as a string
                String[] x_value=x.split(",");
                br.readLine(); //it will omit first line Array x


            }
            else {
                //save the maze
                FileOutputStream mazeFile = new FileOutputStream((new File(tempFolder + "/s" + count)));
                mazeFile.write(mazeFromClient.toByteArray());
                mazeFile.close();
                //solve
                SearchableMaze searchableMaze = new SearchableMaze(mazeFromClient);
                BestFirstSearch searcher = new BestFirstSearch();
                Solution solution = searcher.solve(searchableMaze);
                //save solution
                writeSolution(solution, tempFolder);
                count++;//increase counter
            }

            //send solution
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
            toClient.flush();
            toClient.writeObject(solution);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeSolution(Solution solution,String tempFolder) throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(tempFolder+"/s"+count)));
        ArrayList<AState> ListOfSolution = solution.getSolutionPath();
        for (int i = 0; i < ListOfSolution.size(); i++) {
            outputWriter.write((ListOfSolution.get(i)).toString());
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }



}
