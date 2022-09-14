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
import models.RentToLegal;

public class RentToLegalPersistence implements CRUD {
    private String filePath = null;
    
    public RentToLegalPersistence(String filePath) {
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
            List<Manipulation> rentList = new ArrayList<>();
            String line = "";
            
            while((line = br.readLine()) != null) {
                RentToLegal rent = new RentToLegal();
                rent.simpleMount(line);
                rentList.add(rent);
            }
            return rentList;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(Manipulation object) throws Exception {
        RentToLegal rentObject = (RentToLegal) object;
        List<RentToLegal> rentList = new ArrayList<>();
        for (Manipulation rent : read()) {
            RentToLegal rentItem = (RentToLegal) rent;
            if (rentItem.getId() == rentObject.getId()) {
                rentItem = new RentToLegal(rentObject.getId(), rentObject.getStartDate(), rentObject.getExpectedFinishDate(),
                                    rentObject.getPayment(), rentObject.getVehicle(), rentObject.getClient(), rentObject.getDriver());
            }
            rentList.add(rentItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (RentToLegal rent : rentList) {
                include(rent);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }    
}
