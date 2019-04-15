package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {

    /**
     *
     * @param inputStream
     * @param outputStream
     */
    void serverStrategy(InputStream inputStream, OutputStream outputStream);

}
