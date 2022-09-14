// Servidor

package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import models.LegalClient;
import models.Manipulation;

public class LegalClientPersistence implements CRUD {
    private String filePath = null;
    
    public LegalClientPersistence(String filePath) {
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
            
            List<Manipulation> legalClientList = new ArrayList<>();
            String line = "";
            
            while((line = br.readLine()) != null) {
                LegalClient legalClient = new LegalClient();
                legalClient.mount(line);
                legalClientList.add(legalClient);
            }
            return legalClientList;
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void update(Manipulation object) throws Exception {
        LegalClient legalClientObject = (LegalClient) object;
        List<LegalClient> legalClientList = new ArrayList<>();
        for (Manipulation legalClient : read()) {
            LegalClient legalClientItem = (LegalClient) legalClient;
            if (legalClientItem.getId() == legalClientObject.getId()) {
                legalClientItem = new LegalClient(legalClientObject.getId(), legalClientObject.getName(),
                legalClientObject.getSocialName(), legalClientObject.getAdress(), legalClientObject.getContact(), legalClientObject.getSocialId());
            }
            legalClientList.add(legalClientItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (LegalClient legalClient : legalClientList) {
                include(legalClient);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}
