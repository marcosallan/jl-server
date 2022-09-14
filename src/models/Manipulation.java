// Servidor

package models;

public interface Manipulation {
    void mount(String datas) throws Exception;
    void mount(int id, String datas) throws Exception;
    String dismount();
}
