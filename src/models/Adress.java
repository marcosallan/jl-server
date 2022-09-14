// Servidor

package models;

public class Adress {
    
    private String place = " ";
    private String number = " ";
    private String neighborhood = " ";

    public Adress() {
    }

    public Adress(String place, String number, String neighborhood) {
        this.place = place;
        this.number = number;
        this.neighborhood = neighborhood;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
