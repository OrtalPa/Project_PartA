package test;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_Part_B {

    public static void main(String[] args) {
        String tempFolder = System.getProperty("java.io.tmpdir");
        try {
         //   testMazes();
            simpleTest(tempFolder);


        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

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

    private static void testMazes() throws IOException {
    /*ObjectInputStream fromClient = new ObjectInputStream(inputStream);
    Maze mazeFromClient = (Maze)fromClient.readObject();*/

        int count = saveMazes();
        //search if the maze exists

      //  byte [] byteMazeFromClient = mazeFromClient.toByteArray();
        boolean stop = false;
        int i = 0;
        for (; i < count && !stop; i++) {

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
    }

    private static int saveMazes() throws IOException {
        String tempFolder = System.getProperty("java.io.tmpdir");
        int count = 0;
        for (int j = 0; j < 3; j++)
        {
            //save the maze
            Maze mazeFromClient = createAndSaveMaze(tempFolder, count);
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
/*        FileOutputStream mazeFile = new FileOutputStream((new File(tempFolder + "/m" + count)));
        mazeFile.write(mazeFromClient.toByteArray());
        mazeFile.close();*/
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


       /* BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(tempFolder+"/s"+count)));

        outputWriter.flush();
        outputWriter.close();*/
    }
}
