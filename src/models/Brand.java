// Servidor

package models;

import java.io.IOException;

public class Brand implements Manipulation {
    private int id;
    private String name = " ";
    private String iconPath = " ";
    
    public Brand() throws IOException {
    }

    public Brand(String name, String iconPath) {
        this.name = name;
        this.iconPath = iconPath;
    }
    
    public Brand(int id, String name, String iconPath) {
        this.id = id;
        this.name = name;
        this.iconPath = iconPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public void mount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = Integer.parseInt(vectString[0]);
            this.name = vectString[1];
            this.iconPath = vectString[2];
        }
        catch (NumberFormatException e) {
            throw new Exception("Erro ao montar objeto Marca");
        }
    }
    
    @Override
    public void mount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = id;
            this.name = vectString[1];
            this.iconPath = vectString[2];
        }
        catch(Exception e) {
            throw new Exception("Erro ao montar objeto Marca");
        }
    }

    @Override
    public String dismount() {
        return id + ";" + name + ";" + iconPath;
    }
    
    @Override
    public String toString() {
        return id + ". " + name;
    }
}
