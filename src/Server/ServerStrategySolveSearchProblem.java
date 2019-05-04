package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem  implements IServerStrategy {

    private static int count = 0;

    @Override
    public void serverStrategy(InputStream inputStream, OutputStream outputStream) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
            toClient.flush();
            Object obFromClient = fromClient.readObject();
            if (obFromClient == null || !(obFromClient instanceof Maze)) return;
            //setting variables
            Maze mazeFromClient = (Maze)obFromClient;
            String tempFolder = System.getProperty("java.io.tmpdir");
            Solution solToReturn = null;
            //search if the maze exists
            byte[] byteMazeFromClient = mazeFromClient.toByteArray();
            boolean stop = false;
            int i = 0;
            for (; i < count && !stop; i++) {
                Path fileLocation = Paths.get(tempFolder + "/m" + i + ".txt");
                byte[] currByteArray = Files.readAllBytes(fileLocation);
                stop = Arrays.equals(currByteArray, byteMazeFromClient);
            }
            //if exists - return solution
            if (stop) {
                i--;
                solToReturn = returnSolution(i, tempFolder);
            } else {//save and solve:
                //save the maze
                Path file = Paths.get(tempFolder + "/m" + count + ".txt");
                Files.write(file, mazeFromClient.toByteArray());
                //solve
                SearchableMaze searchableMaze = new SearchableMaze(mazeFromClient);
                //ASearchingAlgorithm searcher = Configurations.getAlgorithm();
                ASearchingAlgorithm searcher = new BestFirstSearch();
                solToReturn = searcher.solve(searchableMaze);
                //save solution
                writeSolution(solToReturn, tempFolder, count);
                count++;//increase counter
            }
            //send solution

            toClient.writeObject(solToReturn);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Solution returnSolution(int i, String tempFolder) throws IOException {
        FileReader fileReader = new FileReader(tempFolder + "/s" + i + ".txt");
        BufferedReader br = new BufferedReader(fileReader);
        MazeState last = null;
        for (String line; (line = br.readLine()) != null; ) {
            int x = Integer.parseInt(line.substring(1, 2));
            int y = Integer.parseInt(line.substring(3, 4));
            MazeState curr = new MazeState(x, y);
            curr.setParent(last);
            last = curr;
        }
        fileReader.close();
        br.close();
        return new Solution(last);
    }

    private void writeSolution(Solution solution, String tempFolder, int count) throws IOException {
        Path file = Paths.get(tempFolder + "/s" + count + ".txt");
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<AState> ListOfSolution = solution.getSolutionPath();
        for (int i = 0; i < ListOfSolution.size(); i++) {
            lines.add((ListOfSolution.get(i)).toString());
        }
        Files.write(file, lines, Charset.forName("UTF-8"));
    }
}
