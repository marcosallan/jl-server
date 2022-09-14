package control;

import java.io.File;

public class SystemPath {
    String path = "";
    
    public SystemPath() {
        this.path = System.getProperty("user.home");
    }
    
    public String getPath() {
        String osStr = System.getProperty("os.name");
        String[] osVect = osStr.split(" ");
        
        if ("Windows".equals(osVect[0])) {
            boolean mkDir = new File(this.path + "\\JLRentACarDatas").mkdir();
            return path + "\\JLRentACarDatas\\";
        }
        else {
            boolean mkDir = new File(this.path + "/JLRentACarDatas").mkdir();
            return path + "/JLRentACarDatas/";
        }
    }
}
