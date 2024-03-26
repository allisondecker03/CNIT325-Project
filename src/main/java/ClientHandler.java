import java.io.InputStream;
import java.io.OutputStream;

public class ClientHandler implements Runnable {
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientHandler(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        System.out.println("Running in ClientHandler");
    }
}
