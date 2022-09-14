// Servidor

package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import models.Driver;
import models.Manipulation;

public class DriverPersistence implements CRUD {
    private String filePath = null;
    
    public DriverPersistence(String filePath) {
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
            List<Manipulation> driverList = new ArrayList<>();
            String line = "";
            
            while((line = br.readLine()) != null) {
                Driver driver = new Driver();
                driver.mount(line);
                driverList.add(driver);
            }
            return driverList;
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void update(Manipulation object) throws Exception {
        Driver driverObject = (Driver) object;
        List<Driver> driverList = new ArrayList<>();
        for (Manipulation driver : read()) {
            Driver driverItem = (Driver) driver;
            if (driverItem.getId() == driverObject.getId()) {
                driverItem = new Driver(driverObject.getId(), driverObject.getName(), driverObject.getCpf(),
                                        driverObject.getCnh(), driverObject.getCnhPath(), driverObject.getGender());
            }
            driverList.add(driverItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (Driver driver : driverList) {
                include(driver);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}
