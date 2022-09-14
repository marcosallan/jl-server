// Servidor

package models;

public class Category implements Manipulation {
    private int id;
    private String description = " ";
    private double rentValue;

    public Category() {
    }
    
    public Category(String description, double rentValue) {
        this.description = description;
        this.rentValue = rentValue;
    }

    public Category(int id, String description, double rentValue) {
        this.id = id;
        this.description = description;
        this.rentValue = rentValue;
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

    public double getRentValue() {
        return rentValue;
    }

    public void setRentValue(double rentValue) {
        this.rentValue = rentValue;
    }

    @Override
    public void mount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = Integer.parseInt(vectString[0]);
            this.description = vectString[1];
            this.rentValue = Double.parseDouble(vectString[2].replaceAll(",", "."));
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto Categoria");
        }
    }

    @Override
    public void mount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = id;
            this.description = vectString[1];
            this.rentValue = Double.parseDouble(vectString[2].replaceAll(",", "."));
        }
        catch(Exception e) {
            throw new Exception("Erro ao montar objeto Categoria");
        }
    }

    @Override
    public String dismount() {
        return id + ";" + description + ";" + String.format("%.2f", rentValue);
    }
    
    @Override
    public String toString() {
        return id + ". " + description;
    }
}
