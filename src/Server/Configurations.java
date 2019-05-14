package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.Properties;
 public  class Configurations {


     public static String getPropValues(String property) throws FileNotFoundException {
         InputStream inputStream = null;
         String ans = "";
         try {
             Properties prop = new Properties();
             inputStream = new FileInputStream("resources/config.properties");


             if (inputStream != null) {
                 prop.load(inputStream);
             } else {
                 //An error is thrown if the file does not exist
                 throw new FileNotFoundException("property file config.properties not found in the classpath");
             }

             // get the property value and print it out
             ans = prop.getProperty(property);


         } catch (Exception e) {
             System.out.println("Exception: " + e);
         } finally {
             try {
                 inputStream.close();
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (NullPointerException e) {
                 e.printStackTrace();
             }
         }
         return ans;
     }

     public static int getNumberOfClients() {
         //default value

         int result = 4;
         try {
             String ans = getPropValues("NumberOfClients");
             result = Integer.parseInt(ans);

         } catch (FileNotFoundException e) {
             //do nothing, return default value
         } catch (NumberFormatException e) {
             //do nothing, return default value
         }
         return result;
     }


     public static AMazeGenerator getMazeGenerator() {
         //default value

         AMazeGenerator result = new MyMazeGenerator();
         try {
             String ans = getPropValues("MazeGenerators");
             if (ans.equals("MyMazeGenerator")) {
                 result = new MyMazeGenerator();
             }
             if (ans.equals("SimpleMazeGenerator")) {
                 result = new SimpleMazeGenerator();
             }
             if (ans.equals("EmptyMazeGenerator")) {
                 result = new EmptyMazeGenerator();
             }

         } catch (FileNotFoundException e) {
             //do nothing, return default value
         }

         return result;
     }

     public static ASearchingAlgorithm getAlgorithm() {
         //default value
         ASearchingAlgorithm result = new BestFirstSearch();
         try {
             String ans = getPropValues("Algorithm");
             if (ans.equals("BestFirstSearch")) {
                 result = new BestFirstSearch();
             }
             if (ans.equals("BreadthFirstSearch")) {
                 result = new BreadthFirstSearch();
             }
             if (ans.equals("DepthFirstSearch")) {
                 result = new DepthFirstSearch();
             }

         } catch (FileNotFoundException e) {
             //do nothing, return default value
         }

         return result;
     }


 }



