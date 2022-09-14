// Servidor

package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Manipulation;

public class CategoryPersistence implements CRUD {
    private String filePath = null;
    
    public CategoryPersistence(String filePath) {
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
            List<Manipulation> categoryList = new ArrayList<>();
            String line = "";
            
            while((line = br.readLine()) != null) {
                Category category = new Category();
                category.mount(line);
                categoryList.add(category);
            }
            return categoryList;
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void update(Manipulation object) throws Exception {
        Category categoryObject = (Category) object;
        List<Category> categoryList = new ArrayList<>();
        for (Manipulation category : read()) {
            Category categoryItem = (Category) category;
            if (categoryItem.getId() == categoryObject.getId()) {
                categoryItem.setDescription(categoryObject.getDescription());
                categoryItem.setRentValue(categoryObject.getRentValue());
            }
            categoryList.add(categoryItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (Category category : categoryList) {
                include(category);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}
