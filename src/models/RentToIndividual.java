// Servidor

package models;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentToIndividual implements Manipulation, SimpleManipulation {
    private int id;
    
    SimpleDateFormat sdf, sdf2;
    
    private Date startDate;
    private Date expectedFinishDate;
    private Date finishDate;
    
    private PaymentWayEnum payment;
    
    private Vehicle vehicle;
    private Driver driver;
    private IndividualClient client;

    public RentToIndividual() throws IOException, ParseException {
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        startDate = sdf.parse(sdf.format(new Date()));
        expectedFinishDate = sdf.parse(sdf.format(new Date()));
        finishDate = sdf.parse(sdf.format(new Date()));
        vehicle = new Vehicle();
        client = new IndividualClient();
        driver = new Driver();
    }

    public RentToIndividual(Date startDate, Date expectedFinishDate, PaymentWayEnum payment, Vehicle vehicle, IndividualClient client, Driver driver) {
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        this.startDate = startDate;
        this.expectedFinishDate = expectedFinishDate;
        this.finishDate = new Date();
        this.payment = payment;
        this.vehicle = vehicle;
        this.client = client;
        this.driver = driver;
    }

    public RentToIndividual(int id, Date startDate, Date expectedFinishDate, PaymentWayEnum payment, Vehicle vehicle, IndividualClient client, Driver driver) {
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        this.id = id;
        this.startDate = startDate;
        this.expectedFinishDate = expectedFinishDate;
        this.finishDate = new Date();
        this.payment = payment;
        this.vehicle = vehicle;
        this.client = client;
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public double getExpectedRentValue() {
        long daysMS = expectedFinishDate.getTime() - startDate.getTime();
        int days = (int) Math.ceil(daysMS / 1000 / 60 / 60 / 24);
        return getVehicle().getCategory().getRentValue() * days;
    }

    public double getRentValue() throws ParseException {
        if (finishDate.getTime() > expectedFinishDate.getTime()) {
            long overDaysMS = finishDate.getTime() - expectedFinishDate.getTime();
            int overDays = (int) Math.ceil(overDaysMS / 1000 / 60 / 60 / 24);
            double overDaysValue = getVehicle().getCategory().getRentValue() * overDays;
            return getExpectedRentValue() + overDaysValue + overDaysValue * 0.10;
        }
        else {
            if (finishDate.getTime() < expectedFinishDate.getTime()){
                long earlyDaysMS = expectedFinishDate.getTime() - finishDate.getTime();
                int earlyDays = (int) Math.ceil(earlyDaysMS / 1000 / 60 / 60 / 24);
                double earlyDaysValue = getVehicle().getCategory().getRentValue() * earlyDays;
                return getExpectedRentValue() - earlyDaysValue * 0.90;
            }
            else {
                return getExpectedRentValue();
            }
        }
    }

    public double getSecurityValue() {
        return getExpectedRentValue();
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpectedFinishDate() {
        return expectedFinishDate;
    }

    public void setExpectedFinishDate(Date expectedFinishDate) {
        this.expectedFinishDate = expectedFinishDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public PaymentWayEnum getPayment() {
        return payment;
    }

    public void setPayment(PaymentWayEnum payment) {
        this.payment = payment;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public IndividualClient getClient() {
        return client;
    }

    public void setClient(IndividualClient client) {
        this.client = client;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void mount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            String vehicleStrMount = "";
            String driverStrMount = "";
            String clientStrMount = "";
            for (int i = 0; i < vectString.length; i++) {
                if (i < 5) {
                    id = i == 0 ? Integer.parseInt(vectString[i]) : id;
                    startDate = i == 1 ? sdf.parse(vectString[i]) : startDate;
                    expectedFinishDate = i == 2 ? sdf.parse(vectString[i]) : expectedFinishDate;
                    finishDate = i == 3 ? sdf.parse(vectString[i]) : finishDate;
                    payment = i == 4 ? PaymentWayEnum.valueOf(vectString[i].toUpperCase()) : payment;
                    continue;
                }
                if (i < 20) {
                    vehicleStrMount += i != 19 ? vectString[i] + ";" : vectString[i];
                    continue;
                }
                if (i < 26) {
                    driverStrMount += i != 25 ? vectString[i] + ";" : vectString[i];
                    continue;
                }
                clientStrMount += i != 37 ? vectString[i] + ";" : vectString[i];
            }
            vehicle.mount(vehicleStrMount);
            driver.mount(driverStrMount);
            client.mount(clientStrMount);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar locação !");
        }
    }

    @Override
    public void mount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = id;
            String vehicleStrMount = "";
            String driverStrMount = "";
            String clientStrMount = "";
            for (int i = 0; i < vectString.length; i++) {
                if (i < 5) {
                    startDate = i == 1 ? sdf.parse(vectString[i]) : startDate;
                    expectedFinishDate = i == 2 ? sdf.parse(vectString[i]) : expectedFinishDate;
                    finishDate = i == 3 ? sdf.parse(vectString[i]) : finishDate;
                    payment = i == 4 ? PaymentWayEnum.valueOf(vectString[i].toUpperCase()) : payment;
                    continue;
                }
                if (i < 20) {
                    vehicleStrMount += i != 19 ? vectString[i] + ";" : vectString[i];
                    continue;
                }
                if (i < 29) {
                    driverStrMount += i != 25 ? vectString[i] + ";" : vectString[i];
                    continue;
                }
                clientStrMount += i != 37 ? vectString[i] + ";" : vectString[i];
            }
            vehicle.mount(vehicleStrMount);
            driver.mount(driverStrMount);
            client.mount(clientStrMount);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar Locação !");
        }
    }

    @Override
    public String dismount() {
        String rentDismount = "";
        rentDismount += id + ";";
        rentDismount += sdf.format(startDate) + ";";
        rentDismount += sdf.format(expectedFinishDate) + ";";
        rentDismount += sdf.format(finishDate) + ";";
        rentDismount += payment.getPaymentWay() + ";";
        rentDismount += getVehicle().getId() + ";";
        rentDismount += getDriver().getId() + ";";
        rentDismount += getClient().getId();
        return rentDismount;
    }

    @Override
    public void simpleMount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            String vehicleStrMount = "";
            String driverStrMount = "";
            String clientStrMount = "";

            for (int i = 0; i < vectString.length; i++) {
                if (i < 5) {
                    id = i == 0 ? Integer.parseInt(vectString[i]) : id;
                    startDate = i == 1 ? sdf.parse(vectString[i]) : startDate;
                    expectedFinishDate = i == 2 ? sdf.parse(vectString[i]) : expectedFinishDate;
                    finishDate = i == 3 ? sdf.parse(vectString[i]) : finishDate;
                    payment = i == 4 ? PaymentWayEnum.valueOf(vectString[i].toUpperCase()) : payment;
                    continue;
                }
                if (i == 5) {
                    vehicleStrMount += vectString[i] + ";";
                    vehicleStrMount += vehicle.getVehiclePlate() + ";";
                    vehicleStrMount += vehicle.getLastMileage() + ";";
                    vehicleStrMount += vehicle.getModel().getId() + ";";
                    vehicleStrMount += vehicle.getCategory().getId() + ";";
                    vehicleStrMount += vehicle.getVehicleStatus();
                    continue;
                }
                if (i == 6) {
                    driverStrMount += vectString[i] + ";";
                    driverStrMount += driver.getName() + ";";
                    driverStrMount += driver.getCpf() + ";";
                    driverStrMount += driver.getCnh() + ";";
                    driverStrMount += driver.getCnhPath() + ";";
                    driverStrMount += driver.getGender().getGender();
                    continue;
                }
                clientStrMount += vectString[i] + ";";
            }
            clientStrMount += client.getName() + ";";
            clientStrMount += client.getSocialName() + ";";
            clientStrMount += client.getAdress().getPlace() + ";";
            clientStrMount += client.getAdress().getNumber() + ";";
            clientStrMount += client.getAdress().getNeighborhood() + ";";
            clientStrMount += client.getContact().getTelephone() + ";";
            clientStrMount += client.getContact().getCellPhone() + ";";
            clientStrMount += client.getContact().getEmail() + ";";
            clientStrMount += client.getSocialId() + ";";
            clientStrMount += sdf2.format(client.getBirhDate()) + ";";
            clientStrMount += client.getGender().getGender();
            client.mount(clientStrMount);
            vehicle.simpleMount(vehicleStrMount);
            driver.mount(driverStrMount);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar locação !");
        }
    }

    @Override
    public void simpleMount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = id;
            String vehicleStrMount = "";
            String driverStrMount = "";
            String clientStrMount = "";

            for (int i = 0; i < vectString.length; i++) {
                if (i < 5) {
                    startDate = i == 1 ? sdf.parse(vectString[i]) : startDate;
                    expectedFinishDate = i == 2 ? sdf.parse(vectString[i]) : expectedFinishDate;
                    finishDate = i == 3 ? sdf.parse(vectString[i]) : finishDate;
                    payment = i == 4 ? PaymentWayEnum.valueOf(vectString[i].toUpperCase()) : payment;
                    continue;
                }
                if (i == 5) {
                    vehicleStrMount += vectString[i] + ";";
                    vehicleStrMount += vehicle.getVehiclePlate() + ";";
                    vehicleStrMount += vehicle.getLastMileage() + ";";
                    vehicleStrMount += vehicle.getModel().getId() + ";";
                    vehicleStrMount += vehicle.getCategory().getId() + ";";
                    vehicleStrMount += vehicle.getVehicleStatus();
                    continue;
                }
                if (i == 6) {
                    driverStrMount += vectString[i] + ";";
                    driverStrMount += driver.getName() + ";";
                    driverStrMount += driver.getCpf() + ";";
                    driverStrMount += driver.getCnh() + ";";
                    driverStrMount += driver.getCnhPath() + ";";
                    driverStrMount += driver.getGender().getGender();
                    continue;
                }
                clientStrMount += vectString[i] + ";";
            }
            clientStrMount += client.getName() + ";";
            clientStrMount += client.getSocialName() + ";";
            clientStrMount += client.getAdress().getPlace() + ";";
            clientStrMount += client.getAdress().getNumber() + ";";
            clientStrMount += client.getAdress().getNeighborhood() + ";";
            clientStrMount += client.getContact().getTelephone() + ";";
            clientStrMount += client.getContact().getCellPhone() + ";";
            clientStrMount += client.getContact().getEmail() + ";";
            clientStrMount += client.getSocialId() + ";";
            clientStrMount += sdf2.format(client.getBirhDate()) + ";";
            clientStrMount += client.getGender().getGender();
            client.mount(clientStrMount);
            vehicle.simpleMount(vehicleStrMount);
            driver.mount(driverStrMount);
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar locação !");
        }
    }
}
