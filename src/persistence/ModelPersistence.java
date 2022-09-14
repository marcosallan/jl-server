// Servidor

package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import models.Model;
import models.Manipulation;

public class ModelPersistence implements CRUD {
    private String filePath = null;
    
    public ModelPersistence(String filePath) {
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
            List<Manipulation> modelList = new ArrayList<>();
            String line = "";
            
            while((line = br.readLine()) != null) {
                Model model = new Model();
                model.simpleMount(line);
                modelList.add(model);
            }
            return modelList;
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void update(Manipulation object) throws Exception {
        Model modelObject = (Model) object;
        List<Model> modelList = new ArrayList<>();
        for (Manipulation model : read()) {
            Model modelItem = (Model) model;
            if (modelItem.getId() == modelObject.getId()) {
                modelItem.setDescription(modelObject.getDescription());
                modelItem.setYear(modelObject.getYear());
                modelItem.setColor(modelObject.getColor());
                modelItem.setPicturePath(modelObject.getPicturePath());
                modelItem.setBrand(modelObject.getBrand());
            }
            modelList.add(modelItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (Model model : modelList) {
                include(model);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}
