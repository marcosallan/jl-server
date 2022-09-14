// Servidor

package models;

public enum VehicleStatusEnum {
    DISPONIVEL("Disponivel"),
    INDISPONIVEL("Indisponivel");
    
    private String vehicleStatus;
    
    VehicleStatusEnum(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
    
    public String getVehicleStatus() {
        return this.vehicleStatus;
    }
}
