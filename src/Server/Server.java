package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port;
    private int listeningIntervalMS;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService pool;


    public Server(int port, int listeningIntervalMS, IServerStrategy serverStrategy) {
        stop = false;
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.serverStrategy = serverStrategy;
        pool = Executors.newFixedThreadPool(4);
    }

    public void start(){
        new Thread (() -> {
            runServer();
        }).start();
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println(String.format("Server starter at %s!", serverSocket));
            System.out.println(String.format("Server's Strategy: %s", serverStrategy.getClass().getSimpleName()));
            System.out.println("Server is waiting for clients...");
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // Accepts client
                    System.out.println(String.format("Client excepted: %s", clientSocket));
                    pool.execute(()->{ //handles the client in a new thread, according to the thread pool
                        handleClient(clientSocket);
                    });
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket Timeout - No clients are waiting!");
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            System.out.println("Handling client");
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.getInputStream().close();
            clientSocket.getOutputStream().close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("IOException - Error handing client!");
        }
    }

    public void stop (){
        stop = true; //will stop the server from listening to clients
    }


}
