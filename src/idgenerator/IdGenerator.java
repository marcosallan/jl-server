// Servidor

package idgenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class IdGenerator {
    private int brandId = 0;
    private int categoryId = 0;
    private int modelId = 0;
    private int driverId = 0;
    private int vehicleId = 0;
    private int clientId = 0;
    private int rentId = 0;
    
    File file;
    String pathFile;
    public IdGenerator(String pathFile) throws FileNotFoundException, IOException {
        this.pathFile = pathFile;
        
        file = new File(this.pathFile);
        if (!file.exists()) {
            writeId();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String line = br.readLine();
            String stringVect[] = line.split(",");
            brandId = Integer.parseInt(stringVect[0]);
            categoryId = Integer.parseInt(stringVect[1]);
            modelId = Integer.parseInt(stringVect[2]);
            driverId = Integer.parseInt(stringVect[3]);
            vehicleId = Integer.parseInt(stringVect[4]);
            clientId = Integer.parseInt(stringVect[5]);
            rentId = Integer.parseInt(stringVect[6]);
        }
    }
    
    public int getBrandId() {
        return ++brandId;
    }
    
    public int getCategoryId() {
        return ++categoryId;
    }
    
    public int getModelId() {
        return ++modelId;
    }
    
    public int getDriverId() {
        return ++driverId;
    }
    
    public int getVehicleId() {
        return ++vehicleId;
    }
    
    public int getClientId() {
        return ++clientId;
    }
    
    public int getRentId() {
        return ++rentId;
    }
    
    public void writeId() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, false))) {
            String outStr = brandId + ",";
            outStr += categoryId + ",";
            outStr += modelId + ",";
            outStr += driverId + ",";
            outStr += vehicleId + ",";
            outStr += clientId + ",";
            outStr += rentId;
            bw.write(outStr);
        }		
    }
}
