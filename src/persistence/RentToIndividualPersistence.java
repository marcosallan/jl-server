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
import models.RentToIndividual;

public class RentToIndividualPersistence implements CRUD {
    private String filePath = null;
    
    public RentToIndividualPersistence(String filePath) {
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
                RentToIndividual rent = new RentToIndividual();
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
        RentToIndividual rentObject = (RentToIndividual) object;
        List<RentToIndividual> rentList = new ArrayList<>();
        for (Manipulation rent : read()) {
            RentToIndividual rentItem = (RentToIndividual) rent;
            if (rentItem.getId() == rentObject.getId()) {
                rentItem = new RentToIndividual(rentObject.getId(), rentObject.getStartDate(), rentObject.getExpectedFinishDate(),
                                    rentObject.getPayment(), rentObject.getVehicle(), rentObject.getClient(), rentObject.getDriver());
            }
            rentList.add(rentItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (RentToIndividual rent : rentList) {
                include(rent);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }    
}
