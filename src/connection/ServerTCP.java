// Servidor

package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerTCP {
    private ServerSocket serverSocket = null;
    private Socket connection = null;
    private DataOutputStream sendToClient = null;
    private DataInputStream receiveFromClient = null;
    
    public ServerTCP(int serverDoor) throws IOException {
        serverSocket = new ServerSocket(serverDoor);
        connection = serverSocket.accept();
        sendToClient = new DataOutputStream(connection.getOutputStream());
        receiveFromClient = new DataInputStream(connection.getInputStream());
    }
    
    public void sendData(String data) throws IOException {
        sendToClient.writeUTF(data);
        sendToClient.flush();
    }
    
    public String receiveData() throws IOException, SocketException {
        return receiveFromClient.readUTF();
    }
}
