// Servidor

package models;

public enum GenderEnum {
    MASCULINO("Masculino"),
    FEMININO("Feminino");
    
    private String gender;
    
    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
