// Servidor

package application;

import connection.ServerTCP;
import control.PersistenceControl;
import java.io.IOException;
import java.net.SocketException;

public class Program {
    public static void main(String[] args) throws IOException, Exception {
        ServerTCP serverTCP = new ServerTCP(5000);
        
        while(true) {
            try {
                String datas = serverTCP.receiveData();
                PersistenceControl pc = new PersistenceControl(serverTCP);
                pc.operation(datas);
            }
            catch (SocketException se) {
            }            
        }
    }
}
