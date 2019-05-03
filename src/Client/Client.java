package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    protected InetAddress m_InetAddress;
    protected int m_port;
    protected IClientStrategy m_IClientStrategy;


    public Client(InetAddress InetAddress, int Port, IClientStrategy IClientStrategy){
        m_port = Port;
        m_InetAddress =InetAddress;
        m_IClientStrategy =IClientStrategy;


    }


    public void communicateWithServer() {

        try {
            Socket theServer = new Socket(m_InetAddress, m_port);
            System.out.println("Client is connected to server!");
            m_IClientStrategy.clientStrategy(theServer.getInputStream(),theServer.getOutputStream());
            theServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
