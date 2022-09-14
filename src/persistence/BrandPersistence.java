// Servidor

package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import models.Brand;
import models.Manipulation;

public class BrandPersistence implements CRUD {
    private String filePath = null;
    
    public BrandPersistence(String filePath) {
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
            List<Manipulation> brandList = new ArrayList<>();
            String line = "";
            
            while((line = br.readLine()) != null) {
                Brand brand = new Brand();
                brand.mount(line);
                brandList.add(brand);
            }
            return brandList;
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void update(Manipulation object) throws Exception {
        Brand brandObject = (Brand) object;
        List<Brand> brandList = new ArrayList<>();
        for (Manipulation brand : read()) {
            Brand brandItem = (Brand) brand;
            if (brandItem.getId() == brandObject.getId()) {
                brandItem.setName(brandObject.getName());
                brandItem.setIconPath(brandObject.getIconPath());
            }
            brandList.add(brandItem);
        }
        try {
            File file = new File(filePath);
            file.delete();
            for (Brand brand : brandList) {
                include(brand);
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}
