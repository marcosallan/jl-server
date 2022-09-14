// Servidor

package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import models.IndividualClient;
import models.Manipulation;

public class IndividualClientPersistence implements CRUD {
    private String filePath = null;
    
    public IndividualClientPersistence(String filePath) {
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
            
            List<Manipulation> individualClientList = new ArrayList<>();
            String line = "";
            
            while((line = br.readLine()) != null) {
                IndividualClient individual = new IndividualClient();
                individual.mount(line);
                individualClientList.add(individual);
            }
            return individualClientList;
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void update(Manipulation object) throws Exception {
        IndividualClient individualObject = (IndividualClient) object;
        List<IndividualClient> individualList = new ArrayList<>();
        for (Manipulation individual : read()) {
            IndividualClient individualItem = (IndividualClient) individual;
            if (individualItem.getId() == individualObject.getId()) {
                individualItem = new IndividualClient(individualObject.getBirhDate(), individualObject.getId(), individualObject.getName(),
                individualObject.getSocialName(), individualObject.getAdress(), individualObject.getContact(), individualObject.getSocialId(),
                individualObject.getGender());
            }
            individualList.add(individualItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (IndividualClient individual : individualList) {
                include(individual);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}
