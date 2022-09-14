// Servidor

package models;

public class Client {
    protected int id;
    protected String name = " ";
    protected String socialName = " ";
    protected Adress adress;
    protected Contact contact;
    protected String socialId = " ";

    public Client() {
        this.adress = new Adress();
        this.contact = new Contact();
    }

    public Client(String name, String socialName, Adress adress, Contact contact, String socialId) {
        this.name = name;
        this.socialName = socialName;
        this.adress = adress;
        this.contact = contact;
        this.socialId = socialId;
    }

    public Client(int id, String name, String socialName, Adress adress, Contact contact, String socialId) {
        this.id = id;
        this.name = name;
        this.socialName = socialName;
        this.adress = adress;
        this.contact = contact;
        this.socialId = socialId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }
    
    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }
}
