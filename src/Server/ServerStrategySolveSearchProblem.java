package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ServerStrategySolveSearchProblem  implements IServerStrategy {

    private int count = 0;
    private HashMap<Integer,Integer> sizes = new HashMap<>();//key = count, value = sizeOfByteArray

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
                //Path fileLocation = Paths.get(tempFolder + "/m" + i + ".txt");
                String fileLocation = tempFolder + "/m" + i + ".txt";
                //System.out.println(Thread.currentThread().getId()+" reading maze from "+fileLocation);
                //byte[] currByteArray = Files.readAllBytes(fileLocation);
                synchronized (this) {
                    InputStream in = new MyDecompressorInputStream(new FileInputStream(fileLocation));
                    byte currByteArray[] = new byte[sizes.get(i)];
                    in.read(currByteArray);
                    in.close();
                    Maze maze = new Maze(currByteArray);
                    stop = Arrays.equals(maze.toByteArray(), byteMazeFromClient);
                }

            }
            //if exists - return solution
            if (stop) {
                i--;
                //System.out.println(Thread.currentThread().getId()+" found solution: "+i);
                solToReturn = returnSolution(i, tempFolder);
            } else {//save and solve:
                //save the maze
                synchronized (this) {
                    //Path file = Paths.get(tempFolder + "/m" + count + ".txt");
                    //Files.write(file, mazeFromClient.toByteArray());
                    String path = tempFolder + "/m" + count + ".txt";
                    //System.out.println(Thread.currentThread().getId()+" writing maze to "+path);
                    String file = tempFolder + "/m" + count + ".txt";

                    OutputStream out = new MyCompressorOutputStream(new FileOutputStream(file));
                    byte[] toWrite = mazeFromClient.toByteArray();
                    out.write(toWrite);
                    out.flush();
                    out.close();
                    sizes.put(count,toWrite.length);
                    //System.out.println(Thread.currentThread().getId()+" finished writing maze, count=="+count);
                    //solve
                    SearchableMaze searchableMaze = new SearchableMaze(mazeFromClient);
                    ASearchingAlgorithm searcher = Configurations.getAlgorithm();
                   // ASearchingAlgorithm searcher = new BestFirstSearch();
                    solToReturn = searcher.solve(searchableMaze);
                    //save solution
                    writeSolution(solToReturn, tempFolder, count);
                    count++;//increase counter
                }
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
        //System.out.println(Thread.currentThread().getId()+" reading sol from "+tempFolder + "/s" + i + ".txt");
        for (String line; (line = br.readLine()) != null; ) {
            String[] result = line.split(",");
            int x = Integer.parseInt(result[0]);
            int y = Integer.parseInt(result[1]);
            MazeState curr = new MazeState(x, y);
            curr.setParent(last);
            last = curr;
        }
        fileReader.close();
        br.close();
        //System.out.println(Thread.currentThread().getId()+" finished reading sol from "+tempFolder + "/s" + i + ".txt");
        Solution sol = new Solution(last);
        //System.out.println(Thread.currentThread().getId()+" Solution received "+i+":"+sol);
        return sol;
    }

    private void writeSolution(Solution solution, String tempFolder, int count) throws IOException {
        Path file = Paths.get(tempFolder + "/s" + count + ".txt");
        //System.out.println(Thread.currentThread().getId()+" writing sol to "+file);
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<AState> ListOfSolution = solution.getSolutionPath();
        for (int i = 0; i < ListOfSolution.size(); i++) {
            String state = ListOfSolution.get(i).toString();
            lines.add(state.substring(1,state.length()-1));
        }
        Files.write(file, lines, Charset.forName("UTF-8"));
       // System.out.println(Thread.currentThread().getId()+" finished writing sol, count=="+count);
    }
}
