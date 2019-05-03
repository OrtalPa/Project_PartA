package Client;

import java.io.InputStream;
import java.io.OutputStream;

//The interface justifies the customer's strategy
public interface IClientStrategy {

    void clientStrategy(InputStream inFromServer,OutputStream outToServer);
}
