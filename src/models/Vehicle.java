// Servidor

package models;

import java.io.IOException;

public class Vehicle implements Manipulation, SimpleManipulation {
    private int id;
    private String vehiclePlate = " ";
    private double lastMileage;
    
    private Model model;
    private Category category;
    private VehicleStatusEnum vehicleStatus;

    public Vehicle() throws IOException {
        this.model = new Model();
        this.category = new Category();
        vehicleStatus = VehicleStatusEnum.INDISPONIVEL;
    }

    public Vehicle(int id, String vehiclePlate, double lastMileage, Model model, Category category, VehicleStatusEnum vehicleStatus) {
        this.id = id;
        this.vehiclePlate = vehiclePlate;
        this.lastMileage = lastMileage;
        this.model = model;
        this.category = category;
        this.vehicleStatus = vehicleStatus;
    }

    public Vehicle(String vehiclePlate, double lastMileage, Model model, Category category, VehicleStatusEnum vehicleStatus) {
        this.vehiclePlate = vehiclePlate;
        this.lastMileage = lastMileage;
        this.model = model;
        this.category = category;
        this.vehicleStatus = vehicleStatus;
    }

    public int getId() {
        return id;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public double getLastMileage() {
        return lastMileage;
    }

    public void setLastMileage(double lastMileage) {
        this.lastMileage = lastMileage;
    }
    
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public VehicleStatusEnum getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatusEnum vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    @Override
    public void mount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            String modelStrMount = "";
            String categoryStrMount = "";
            
            for (int i = 0; i < vectString.length; i++) {
                if (i < 3) {
                    this.id = i == 0 ? Integer.parseInt(vectString[i]) : id;
                    this.vehiclePlate = i == 1 ? vectString[i] : vehiclePlate;
                    this.lastMileage = i == 2 ? Double.parseDouble(vectString[i]) : lastMileage;
                    continue;
                }
                if (i < 11) {
                    modelStrMount += i == 3 ? vectString[i] + ";" : "";
                    modelStrMount += i == 4 ? vectString[i] + ";" : "";
                    modelStrMount += i == 5 ? vectString[i] + ";" : "";
                    modelStrMount += i == 6 ? vectString[i] + ";" : "";
                    modelStrMount += i == 7 ? vectString[i] + ";" : "";
                    modelStrMount += i == 8 ? vectString[i] + ";" : "";
                    modelStrMount += i == 9 ? vectString[i] + ";" : "";
                    modelStrMount += i == 10 ? vectString[i] : "";
                    continue;
                }
                if (i < 14) {
                    categoryStrMount += i == 11 ? vectString[i] + ";" : "";
                    categoryStrMount += i == 12 ? vectString[i] + ";" : "";
                    categoryStrMount += i == 13 ? vectString[i] : "";
                    continue;
                }
                this.vehicleStatus = VehicleStatusEnum.valueOf(vectString[14].toUpperCase());
            }
            model.mount(modelStrMount);
            category.mount(categoryStrMount);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto veículo");
        }
    }

    @Override
    public void mount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            String modelStrMount = "";
            String categoryStrMount = "";
            this.id = id;
            
            for (int i = 0; i < vectString.length; i++) {
                if (i < 3) {
                    this.vehiclePlate = i == 1 ? vectString[i] : vehiclePlate;
                    this.lastMileage = i == 2 ? Double.parseDouble(vectString[i]) : lastMileage;
                    continue;
                }
                if (i < 11) {
                    modelStrMount += i == 3 ? vectString[i] + ";" : "";
                    modelStrMount += i == 4 ? vectString[i] + ";" : "";
                    modelStrMount += i == 5 ? vectString[i] + ";" : "";
                    modelStrMount += i == 6 ? vectString[i] + ";" : "";
                    modelStrMount += i == 7 ? vectString[i] + ";" : "";
                    modelStrMount += i == 8 ? vectString[i] + ";" : "";
                    modelStrMount += i == 9 ? vectString[i] + ";" : "";
                    modelStrMount += i == 10 ? vectString[i] : "";
                    continue;
                }
                if (i < 14) {
                    categoryStrMount += i == 11 ? vectString[i] + ";" : "";
                    categoryStrMount += i == 12 ? vectString[i] + ";" : "";
                    categoryStrMount += i == 13 ? vectString[i] : "";
                    continue;
                }
                this.vehicleStatus = VehicleStatusEnum.valueOf(vectString[14].toUpperCase());
            }
            model.mount(modelStrMount);
            category.mount(categoryStrMount);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto veículo");
        }
    }

    @Override
    public String dismount() {
        return this.id + ";" + this.vehiclePlate + ";" + this.lastMileage + ";" + this.model.getId() + ";" +
               this.category.getId() + ";" + this.vehicleStatus.getVehicleStatus();
    }

    @Override
    public void simpleMount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            String modelStrMount = "";
            String categoryStrMount = "";
            
            for (int i = 0; i < vectString.length; i++) {
                if (i < 3) {
                    this.id = i == 0 ? Integer.parseInt(vectString[i]) : id;
                    this.vehiclePlate = i == 1 ? vectString[i] : vehiclePlate;
                    this.lastMileage = i == 2 ? Double.parseDouble(vectString[i]) : lastMileage;
                    continue;
                }
                if (i == 3) {
                    modelStrMount += vectString[i] + ";";
                    modelStrMount += model.getDescription() + ";";
                    modelStrMount += model.getYear() + ";";
                    modelStrMount += model.getColor() + ";";
                    modelStrMount += model.getPicturePath() + ";";
                    modelStrMount += model.getBrand().getId();
                    continue;
                }
                if (i == 4) {
                    categoryStrMount += vectString[4] + ";";
                    categoryStrMount += category.getDescription() + ";";
                    categoryStrMount += category.getRentValue();
                    continue;
                }
                this.vehicleStatus = VehicleStatusEnum.valueOf(vectString[5].toUpperCase());
            }
            model.simpleMount(modelStrMount);
            category.mount(categoryStrMount);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto veículo");
        }
    }

    @Override
    public void simpleMount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            String modelStrMount = "";
            String categoryStrMount = "";
            this.id = id;
            
            for (int i = 0; i < vectString.length; i++) {
                if (i < 3) {
                    this.vehiclePlate = i == 1 ? vectString[i] : vehiclePlate;
                    this.lastMileage = i == 2 ? Double.parseDouble(vectString[i]) : lastMileage;
                    continue;
                }
                if (i == 3) {
                    modelStrMount += vectString[i] + ";";
                    modelStrMount += model.getDescription() + ";";
                    modelStrMount += model.getYear() + ";";
                    modelStrMount += model.getColor() + ";";
                    modelStrMount += model.getPicturePath() + ";";
                    modelStrMount += model.getBrand().getId();
                    continue;
                }
                if (i == 4) {
                    categoryStrMount += vectString[4] + ";";
                    categoryStrMount += category.getDescription() + ";";
                    categoryStrMount += category.getRentValue();
                    continue;
                }
                this.vehicleStatus = VehicleStatusEnum.valueOf(vectString[5].toUpperCase());
            }
            model.simpleMount(modelStrMount);
            category.mount(categoryStrMount);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto veículo");
        }
    }
    
    @Override
    public String toString() {
        return String.format("%d. %s - %s", id, model.getDescription(), category.getDescription());
    }
}
