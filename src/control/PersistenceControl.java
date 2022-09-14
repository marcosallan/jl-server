// Servidor

package control;

import connection.ServerTCP;
import java.io.IOException;
import models.Brand;
import models.Category;
import persistence.BrandPersistence;
import idgenerator.IdGenerator;
import java.text.SimpleDateFormat;
import models.Driver;
import models.IndividualClient;
import models.LegalClient;
import models.Manipulation;
import models.Model;
import models.RentToIndividual;
import models.RentToLegal;
import models.Vehicle;
import persistence.CategoryPersistence;
import persistence.DriverPersistence;
import persistence.IndividualClientPersistence;
import persistence.LegalClientPersistence;
import persistence.ModelPersistence;
import persistence.RentToIndividualPersistence;
import persistence.RentToLegalPersistence;
import persistence.VehiclePersistence;

public class PersistenceControl {
    SimpleDateFormat sdf;
    ServerTCP serverTCP;
    IdGenerator idGenerator;
    BrandPersistence brandPersistence;
    ModelPersistence modelPersistence;
    CategoryPersistence categoryPersistence;
    DriverPersistence driverPersistence;
    VehiclePersistence vehiclePersistence;
    IndividualClientPersistence individualClientPersistence;
    LegalClientPersistence legalClientPersistence;
    RentToIndividualPersistence rentToIndividualPersistence;
    RentToLegalPersistence rentToLegalPersistence;
    SystemPath systemPath;
    
    
    public PersistenceControl(ServerTCP serverTCP) throws IOException {
        systemPath = new SystemPath();
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.serverTCP = serverTCP;
        idGenerator = new IdGenerator(systemPath.getPath() + "idControl.txt");
        brandPersistence = new BrandPersistence(systemPath.getPath() + "brand_datas.txt");
        modelPersistence = new ModelPersistence(systemPath.getPath() + "model_datas.txt");
        categoryPersistence = new CategoryPersistence(systemPath.getPath() + "category_datas.txt");
        driverPersistence = new DriverPersistence(systemPath.getPath() + "driver_datas.txt");
        vehiclePersistence = new VehiclePersistence(systemPath.getPath() + "vehicle_datas.txt");
        individualClientPersistence = new IndividualClientPersistence(systemPath.getPath() + "individual_datas.txt");
        legalClientPersistence = new LegalClientPersistence(systemPath.getPath() + "legal_datas.txt");
        rentToIndividualPersistence = new RentToIndividualPersistence(systemPath.getPath() + "rentIndividual_datas.txt");
        rentToLegalPersistence = new RentToLegalPersistence(systemPath.getPath() + "rentLegal_datas.txt");
    }
    
    public void brandPersistenceControl(Operation option, String datas) throws Exception {
        switch(option) {
            case INCLUDE:
                Brand brand = new Brand();
                brand.mount(idGenerator.getBrandId(), datas);
                idGenerator.writeId();
                brandPersistence.include(brand);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation object : brandPersistence.read()) {
                    datasToClient += object.dismount() + "\n";
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                Brand brandUpdated = new Brand();
                brandUpdated.mount(datas);
                brandPersistence.update(brandUpdated);
                break;
        }
    }
    
    public void categoryPersistenceControl(Operation option, String datas) throws Exception {
        switch(option) {
            case INCLUDE:
                Category category = new Category();
                category.mount(idGenerator.getCategoryId(), datas);
                idGenerator.writeId();
                categoryPersistence.include(category);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation object : categoryPersistence.read()) {
                    datasToClient += object.dismount() + "\n";
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                Category categoryUpdated = new Category();
                categoryUpdated.mount(datas);
                categoryPersistence.update(categoryUpdated);
                break;
        }
    }
    
    public void modelPersistenceControl(Operation option, String datas) throws Exception {
        switch(option) {
            case INCLUDE:
                Model model = new Model();
                model.simpleMount(idGenerator.getModelId(), datas);
                idGenerator.writeId();
                modelPersistence.include(model);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation objectModel : modelPersistence.read()) {
                    Model object = (Model) objectModel;
                    datasToClient += objectModel.dismount() + ";";
                    for (Manipulation objectBrand : brandPersistence.read()) {
                        Brand brandModel = (Brand) objectBrand;
                        if (object.getBrand().getId() == brandModel.getId()) {
                            datasToClient += brandModel.getName() + ";";
                            datasToClient += brandModel.getIconPath() + "\n";
                        }
                    }
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                Model modelUpdated = new Model();
                modelUpdated.simpleMount(datas);
                modelPersistence.update(modelUpdated);
                break;
        }
    }
    
    public void driverPersistenceControl(Operation option, String datas) throws Exception {
        switch(option) {
            case INCLUDE:
                Driver driver = new Driver();
                driver.mount(idGenerator.getDriverId(), datas);
                idGenerator.writeId();
                driverPersistence.include(driver);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation object : driverPersistence.read()) {
                    datasToClient += object.dismount() + "\n";
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                Driver driverUpdated = new Driver();
                driverUpdated.mount(datas);
                driverPersistence.update(driverUpdated);
                break;
        }
    }
    
    public void vehiclePersistenceControl(Operation option, String datas) throws Exception {
        switch(option) {
            case INCLUDE:
                Vehicle vehicle = new Vehicle();
                vehicle.simpleMount(idGenerator.getVehicleId(), datas);
                idGenerator.writeId();
                vehiclePersistence.include(vehicle);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation objectVehicle : vehiclePersistence.read()) {
                    Vehicle objVehicle = (Vehicle) objectVehicle;
                    datasToClient += objVehicle.getId() + ";";
                    datasToClient += objVehicle.getVehiclePlate() + ";";
                    datasToClient += objVehicle.getLastMileage() + ";";
                    for (Manipulation objectModel : modelPersistence.read()) {
                        Model objModel = (Model) objectModel;
                        if (objVehicle.getModel().getId() == objModel.getId()) {
                            datasToClient += objModel.dismount() + ";";
                            for (Manipulation objectBrand : brandPersistence.read()) {
                                Brand objBrand = (Brand) objectBrand;
                                if (objModel.getBrand().getId() == objBrand.getId()) {
                                    datasToClient += objBrand.getName() + ";";
                                    datasToClient += objBrand.getIconPath() + ";";
                                }
                            }
                            break;
                        }
                    }
                    for (Manipulation objectCategory : categoryPersistence.read()) {
                        Category objCategory = (Category) objectCategory;
                        if (objVehicle.getCategory().getId() == objCategory.getId()) {
                            datasToClient += objCategory.dismount() + ";";
                        }
                    }
                    datasToClient += objVehicle.getVehicleStatus() + "\n";
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                Vehicle vehicleUpdated = new Vehicle();
                vehicleUpdated.simpleMount(datas);
                vehiclePersistence.update(vehicleUpdated);
                break;
        }
    }
    
    public void individualClientPersistenceControl(Operation option, String datas) throws Exception {
        switch(option) {
            case INCLUDE:
                IndividualClient individualClient = new IndividualClient();
                individualClient.mount(idGenerator.getClientId(), datas);
                idGenerator.writeId();
                individualClientPersistence.include(individualClient);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation object : individualClientPersistence.read()) {
                    datasToClient += object.dismount() + "\n";
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                IndividualClient individualClientUpdated = new IndividualClient();
                individualClientUpdated.mount(datas);
                individualClientPersistence.update(individualClientUpdated);
                break;
        }
    }
    
    public void legalClientPersistenceControl(Operation option, String datas) throws Exception {
        switch(option) {
            case INCLUDE:
                LegalClient legalClient = new LegalClient();
                legalClient.mount(idGenerator.getClientId(), datas);
                idGenerator.writeId();
                legalClientPersistence.include(legalClient);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation object : legalClientPersistence.read()) {
                    datasToClient += object.dismount() + "\n";
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                LegalClient legalClientUpdated = new LegalClient();
                legalClientUpdated.mount(datas);
                legalClientPersistence.update(legalClientUpdated);
                break;
        }
    }
    
    public void rentToIndividualPersistenceControl(Operation option, String datas) throws Exception {
        switch(option) {
            case INCLUDE:
                RentToIndividual rent = new RentToIndividual();
                rent.simpleMount(idGenerator.getRentId(), datas);
                idGenerator.writeId();
                rentToIndividualPersistence.include(rent);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation objectRent : rentToIndividualPersistence.read()) {
                    RentToIndividual objRent = (RentToIndividual) objectRent;
                    
                    datasToClient += objRent.getId() + ";";
                    datasToClient += sdf.format(objRent.getStartDate()) + ";";
                    datasToClient += sdf.format(objRent.getExpectedFinishDate()) + ";";
                    datasToClient += sdf.format(objRent.getFinishDate()) + ";";
                    datasToClient += objRent.getPayment().getPaymentWay() + ";";
                    for (Manipulation objectVehicle : vehiclePersistence.read()) {
                        Vehicle objVehicle = (Vehicle) objectVehicle;
                        if (objRent.getVehicle().getId() == objVehicle.getId()) {
                            datasToClient += objVehicle.getId() + ";";
                            datasToClient += objVehicle.getVehiclePlate() + ";";
                            datasToClient += objVehicle.getLastMileage() + ";";
                            for (Manipulation objectModel : modelPersistence.read()) {
                                Model objModel = (Model) objectModel;
                                if (objVehicle.getModel().getId() == objModel.getId()) {
                                    datasToClient += objModel.dismount() + ";";
                                    for (Manipulation objectBrand : brandPersistence.read()) {
                                        Brand objBrand = (Brand) objectBrand;
                                        if (objModel.getBrand().getId() == objBrand.getId()) {
                                            datasToClient += objBrand.getName() + ";";
                                            datasToClient += objBrand.getIconPath() + ";";
                                        }
                                    }
                                    break;
                                }
                            }
                            for (Manipulation objectCategory : categoryPersistence.read()) {
                                Category objCategory = (Category) objectCategory;
                                if (objVehicle.getCategory().getId() == objCategory.getId()) {
                                    datasToClient += objCategory.dismount() + ";";
                                    break;
                                }
                            }
                            datasToClient += objVehicle.getVehicleStatus() + ";";
                        }
                    }
                    
                    for (Manipulation objectDriver : driverPersistence.read()) {
                        Driver objDriver = (Driver) objectDriver;
                        if (objRent.getDriver().getId() == objDriver.getId()) {
                            datasToClient += objDriver.dismount() + ";";
                            break;
                        }
                    }
                    
                    for (Manipulation objecClient : individualClientPersistence.read()) {
                        IndividualClient objClient = (IndividualClient) objecClient;
                        if (objRent.getClient().getId() == objClient.getId()) {
                            datasToClient += objecClient.dismount() + "\n";
                            break;
                        }
                    }
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                RentToIndividual rentUpdated = new RentToIndividual();
                rentUpdated.simpleMount(datas);
                rentToIndividualPersistence.update(rentUpdated);
                break;
        }
    }
    
    public void rentToLegalPersistenceControl(Operation option, String datas) throws IOException, Exception {
        switch(option) {
            case INCLUDE:
                RentToLegal rent = new RentToLegal();
                rent.simpleMount(idGenerator.getRentId(), datas);
                idGenerator.writeId();
                rentToLegalPersistence.include(rent);
                break;
            case READ:
                String datasToClient = "";
                for (Manipulation objectRent : rentToLegalPersistence.read()) {
                    RentToLegal objRent = (RentToLegal) objectRent;
                    
                    datasToClient += objRent.getId() + ";";
                    datasToClient += sdf.format(objRent.getStartDate()) + ";";
                    datasToClient += sdf.format(objRent.getExpectedFinishDate()) + ";";
                    datasToClient += sdf.format(objRent.getFinishDate()) + ";";
                    datasToClient += objRent.getPayment().getPaymentWay() + ";";
                    for (Manipulation objectVehicle : vehiclePersistence.read()) {
                        Vehicle objVehicle = (Vehicle) objectVehicle;
                        if (objRent.getVehicle().getId() == objVehicle.getId()) {
                            datasToClient += objVehicle.getId() + ";";
                            datasToClient += objVehicle.getVehiclePlate() + ";";
                            datasToClient += objVehicle.getLastMileage() + ";";
                            for (Manipulation objectModel : modelPersistence.read()) {
                                Model objModel = (Model) objectModel;
                                if (objVehicle.getModel().getId() == objModel.getId()) {
                                    datasToClient += objModel.dismount() + ";";
                                    for (Manipulation objectBrand : brandPersistence.read()) {
                                        Brand objBrand = (Brand) objectBrand;
                                        if (objModel.getBrand().getId() == objBrand.getId()) {
                                            datasToClient += objBrand.getName() + ";";
                                            datasToClient += objBrand.getIconPath() + ";";
                                        }
                                    }
                                    break;
                                }
                            }
                            for (Manipulation objectCategory : categoryPersistence.read()) {
                                Category objCategory = (Category) objectCategory;
                                if (objVehicle.getCategory().getId() == objCategory.getId()) {
                                    datasToClient += objCategory.dismount() + ";";
                                    break;
                                }
                            }
                            datasToClient += objVehicle.getVehicleStatus() + ";";
                        }
                    }
                    
                    for (Manipulation objectDriver : driverPersistence.read()) {
                        Driver objDriver = (Driver) objectDriver;
                        if (objRent.getDriver().getId() == objDriver.getId()) {
                            datasToClient += objDriver.dismount() + ";";
                            break;
                        }
                    }
                    
                    for (Manipulation objecClient : legalClientPersistence.read()) {
                        LegalClient objClient = (LegalClient) objecClient;
                        if (objRent.getClient().getId() == objClient.getId()) {
                            datasToClient += objecClient.dismount() + "\n";
                            break;
                        }
                    }
                }
                serverTCP.sendData(datasToClient);
                break;
            case UPDATE:
                RentToLegal rentUpdated = new RentToLegal();
                rentUpdated.simpleMount(datas);
                rentToLegalPersistence.update(rentUpdated);
                break;
        }
    }
    
    public void operation(String datas) throws Exception {
        String[] strVect = datas.split("#");
        Operation operation = Operation.valueOf(strVect[1]);
        switch(strVect[0]) {
            case "Brand":
                brandPersistenceControl(operation, strVect[2]);
                break;
            case "Category":
                categoryPersistenceControl(operation, strVect[2]);
                break;
            case "Model":
                modelPersistenceControl(operation, strVect[2]);
                break;
            case "Driver":
                driverPersistenceControl(operation, strVect[2]);
                break;
            case "Vehicle":
                vehiclePersistenceControl(operation, strVect[2]);
                break;
            case "IndividualClient":
                individualClientPersistenceControl(operation, strVect[2]);
                break;
            case "LegalClient":
                legalClientPersistenceControl(operation, strVect[2]);
                break;
            case "RentToIndividual":
                rentToIndividualPersistenceControl(operation, strVect[2]);
                break;
            case "RentToLegal":
                rentToLegalPersistenceControl(operation, strVect[2]);
                break;
        }
    }
}
