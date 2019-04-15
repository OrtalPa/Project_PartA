package test;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;

public class Main_Part_B {

    public static void main(String[] args) {
        String tempFolder = System.getProperty("java.io.tmpdir");
        try {
            simpleTest(tempFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private static void testMaze() {
        try {
            ArrayList<Maze> mazes = new ArrayList<>();
            System.out.println("Creating mazes...");
            String tempFolder = System.getProperty("java.io.tmpdir");
            int count = saveMazes(mazes, tempFolder);
            //search if the maze exists
            byte[] byteMazeFromClient = mazes.get(1).toByteArray();
            boolean stop = false;
            int i = 0;
            System.out.println("Searching for existing maze...");
            for (; i < count && !stop; i++) {
                Path fileLocation = Paths.get(tempFolder + "/m" + i + ".txt");
                byte[] currByteArray = Files.readAllBytes(fileLocation);
                stop = Arrays.equals(currByteArray, byteMazeFromClient);
            }
            //if exists - return solution
            if (stop) {
                System.out.println("Found maze - search successful");
                Solution sol = returnSolution(i, tempFolder);
                System.out.println("The solution:");
                System.out.println(sol.getSolutionPath());
            } else
                System.out.println("Did not find maze - error in search");


            MyMazeGenerator generator = new MyMazeGenerator();
            Maze doesntExist = generator.generate(10, 10);
            byte[] doesntExistByte = doesntExist.toByteArray();
            stop = false;
            i = 0;
            System.out.println("Searching for non existing maze...");
            for (; i < count && !stop; i++) {
                Path fileLocation = Paths.get(tempFolder + "/m" + i + ".txt");
                byte[] currByteArray = Files.readAllBytes(fileLocation);
                stop = Arrays.equals(currByteArray, doesntExistByte);
            }
            //if exists - return solution
            if (stop) {
                System.out.println("Found maze - error in search");
                Solution sol = returnSolution(i, tempFolder);
                System.out.println("The solution:");
                System.out.println(sol.getSolutionPath());
            } else
                System.out.println("Did not find maze - search successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Solution returnSolution(int i, String tempFolder) throws IOException {
        i--;
        FileReader fileReader = new FileReader(tempFolder + "/s" + i + ".txt");
        BufferedReader br = new BufferedReader(fileReader);
        MazeState last = null;
        for (String line; (line = br.readLine()) != null; ) {
            int x = Integer.parseInt(line.substring(1,2));
            int y = Integer.parseInt(line.substring(3,4));
            MazeState curr = new MazeState(x,y);
            curr.setParent(last);
            last = curr;
        }
        fileReader.close();
        br.close();
        return new Solution(last);
    }

    private static int saveMazes(ArrayList<Maze> mazes, String tempFolder) throws IOException {
        int count = 0;
        for (int j = 0; j < 3; j++)
        {
            //save the maze
            Maze mazeFromClient = createAndSaveMaze(tempFolder, count);
            mazes.add(mazeFromClient);
            //solve
            SearchableMaze searchableMaze = new SearchableMaze(mazeFromClient);
            BestFirstSearch searcher = new BestFirstSearch();
            Solution solution = searcher.solve(searchableMaze);
            //save solution
            writeSolution(solution, tempFolder,count);
            count++;//increase counter
        }
        return count;
    }

    private static Maze createAndSaveMaze(String tempFolder, int count) throws IOException {
        MyMazeGenerator generator = new MyMazeGenerator();
        Maze mazeFromClient =  generator.generate(10,10);
        Path file = Paths.get(tempFolder+ "/m" + count+".txt");
        Files.write(file, mazeFromClient.toByteArray());

*//*        FileOutputStream mazeFile = new FileOutputStream((new File(tempFolder + "/m" + count)));
        mazeFile.write(mazeFromClient.toByteArray());
        mazeFile.close();*//*
        return mazeFromClient;
    }

    private static void writeSolution(Solution solution,String tempFolder, int count) throws IOException {
        Path file = Paths.get(tempFolder+"/s"+count+".txt");
        ArrayList<String > lines = new ArrayList<>();
        ArrayList<AState> ListOfSolution = solution.getSolutionPath();
        for (int i = 0; i < ListOfSolution.size(); i++) {
            lines.add((ListOfSolution.get(i)).toString());
        }
        Files.write(file, lines, Charset.forName("UTF-8"));


       *//* BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(tempFolder+"/s"+count)));

        outputWriter.flush();
        outputWriter.close();*//*
    }
*/


    private static void simpleTest(String tempFolder) throws IOException {
        int count = 0;
        byte [] mazeFromClient1 = {'s',30,1,40,6,0};
        count = writeToFile(tempFolder, count,mazeFromClient1);
        System.out.println("test1, should be true: "+findByteArray(tempFolder,count, mazeFromClient1));

        byte [] mazeFromClient11 = {115,30,1,40,6,0};
        count = writeToFile(tempFolder, count,mazeFromClient1);
        System.out.println("test1, should be false: "+findByteArray(tempFolder,count, mazeFromClient11));

        byte [] mazeFromClient2 = {30,1,127,6,0};
        count = writeToFile(tempFolder, count,mazeFromClient1);
        System.out.println("test1, should be false: "+findByteArray(tempFolder,count, mazeFromClient2));
    }

    private static int writeToFile(String tempFolder, int count,byte[] mazeFromClient) throws IOException {
        Path file = Paths.get(tempFolder+ "/m" + count+".txt");
        Files.write(file, mazeFromClient);
        /*FileOutputStream mazeFile = new FileOutputStream((new File(tempFolder + "/m" + count)));
        mazeFile.write(mazeFromClient);
        mazeFile.close();*/
        return count;
    }

    private static boolean findByteArray(String tempFolder,int count,byte[] byteMazeFromClient) throws IOException {
        Path fileLocation = Paths.get(tempFolder+"/m"+count+".txt"); //.txt???
        byte[] currByteArray = Files.readAllBytes(fileLocation);
        return Arrays.equals(currByteArray,byteMazeFromClient);
    }

}
