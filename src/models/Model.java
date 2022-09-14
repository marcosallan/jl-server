// Servidor

package models;

import java.io.IOException;

public class Model implements Manipulation, SimpleManipulation {
    private int id;
    private String description = " ";
    private int year;
    private String color = " ";
    private String picturePath = " ";
    
    private Brand brand;

    public Model() throws IOException {
        this.brand = new Brand();
    }

    public Model(String description, int year, String color, String picturePath, Brand brand) {
        this.description = description;
        this.year = year;
        this.color = color;
        this.picturePath = picturePath;
        this.brand = brand;
    }

    public Model(int id, String description, int year, String color, String picturePath, Brand brand) {
        this.id = id;
        this.description = description;
        this.year = year;
        this.color = color;
        this.picturePath = picturePath;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public void mount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = Integer.parseInt(vectString[0]);
            this.description = vectString[1];
            this.year = Integer.parseInt(vectString[2]);
            this.color = vectString[3];
            this.picturePath = vectString[4];
            this.brand.mount(vectString[5] + ";" + vectString[6] + ";" + vectString[7]);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto Modelo");
        }
    }

    @Override
    public void mount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = id;
            this.description = vectString[1];
            this.year = Integer.parseInt(vectString[2]);
            this.color = vectString[3];
            this.picturePath = vectString[4];
            this.brand.mount(vectString[5] + ";" + vectString[6] + ";" + vectString[7]);
        }
        catch(Exception e) {
            throw new Exception("Erro ao montar objeto Modelo");
        }
    }

    @Override
    public String dismount() {
        return id + ";" + description + ";" +
               year + ";" + color + ";" + picturePath + ";" + brand.getId();
    }
    
    @Override
    public String toString() {
        return id + ". " + description + " - " + year + " - " + color;
    }

    @Override
    public void simpleMount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = Integer.parseInt(vectString[0]);
            this.description = vectString[1];
            this.year = Integer.parseInt(vectString[2]);
            this.color = vectString[3];
            this.picturePath = vectString[4];
            this.brand.mount(vectString[5] + ";" + brand.getName() + ";" + brand.getIconPath());
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto Modelo");
        }
    }

    @Override
    public void simpleMount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = id;
            this.description = vectString[1];
            this.year = Integer.parseInt(vectString[2]);
            this.color = vectString[3];
            this.picturePath = vectString[4];
            this.brand.mount(vectString[5] + ";" + brand.getName() + ";" + brand.getIconPath());
        }
        catch(Exception e) {
            throw new Exception("Erro ao montar objeto Modelo");
        }
    }
}
