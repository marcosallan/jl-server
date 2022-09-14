// Servidor

package control;

public enum Operation {
    INCLUDE("Include"),
    READ("Read"),
    UPDATE("Update");
    
    private String operation;

    Operation(String operation) {
        this.operation = operation;
    }
    
    public String getOperation() {
        return operation;
    }
}
