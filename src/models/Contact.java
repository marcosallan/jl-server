// Servidor

package models;

public class Contact {
    private String telephone = " ";
    private String cellPhone = " ";
    private String email = " ";
    
    public Contact() {
    }

    public Contact(String telephone, String cellPhone, String email) {
        this.telephone = telephone;
        this.cellPhone = cellPhone;
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
