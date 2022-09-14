// Servidor

package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import models.Manipulation;
import models.Vehicle;

public class VehiclePersistence implements CRUD {
    private String filePath = null;
    
    public VehiclePersistence(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public void include(Manipulation object) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(object.dismount());
            bw.newLine();
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Manipulation> read() throws Exception {
        File file = new File(filePath);
        file.createNewFile();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<Manipulation> vehicleList = new ArrayList<>();
            String line = "";
            
            while((line = br.readLine()) != null) {
                Vehicle vehicle = new Vehicle();
                vehicle.simpleMount(line);
                vehicleList.add(vehicle);
            }
            return vehicleList;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(Manipulation object) throws Exception {
        Vehicle vehicleObject = (Vehicle) object;
        List<Vehicle> vehicleList = new ArrayList<>();
        for (Manipulation vehicle : read()) {
            Vehicle vehicleItem = (Vehicle) vehicle;
            if (vehicleItem.getId() == vehicleObject.getId()) {
                vehicleItem = new Vehicle(vehicleObject.getId(), vehicleObject.getVehiclePlate(), vehicleObject.getLastMileage(),
                                          vehicleObject.getModel(), vehicleObject.getCategory(), vehicleObject.getVehicleStatus());
            }
            vehicleList.add(vehicleItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (Vehicle vehicle : vehicleList) {
                include(vehicle);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }    
}
