package chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author simon
 */
public class SocketMock extends Socket{

    public boolean closeCalled = false;
    @Override
    public InputStream getInputStream() {

        return new InputStream() {

            @Override
            public int read() throws IOException {
                return 1;
            }
        };
    }

    
    @Override
    public OutputStream getOutputStream() {

        return new OutputStream() {

            @Override
            public void write(int b) throws IOException {

            }
        };
    }
    @Override
    public void close() throws IOException {
        closeCalled = true;
    }

}
