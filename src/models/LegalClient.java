// Servidor

package models;

public class LegalClient extends Client implements Manipulation {

    public LegalClient() {
        super();
    }

    public LegalClient(String name, String socialName, Adress adress, Contact contact, String socialId) {
        super(name, socialName, adress, contact, socialId);
    }

    public LegalClient(int id, String name, String socialName, Adress adress, Contact contact, String socialId) {
        super(id, name, socialName, adress, contact, socialId);
    }

    @Override
    public void mount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = Integer.parseInt(vectString[0]);
            this.name = vectString[1];
            this.socialName = vectString[2];
            this.adress.setPlace(vectString[3]);
            this.adress.setNumber(vectString[4]);
            this.adress.setNeighborhood(vectString[5]);
            this.contact.setTelephone(vectString[6]);
            this.contact.setCellPhone(vectString[7]);
            this.contact.setEmail(vectString[8]);
            this.socialId = vectString[9];
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto Cliente Pessoa Jurídica");
        }
    }

    @Override
    public void mount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = id;
            this.name = vectString[1];
            this.socialName = vectString[2];
            this.adress.setPlace(vectString[3]);
            this.adress.setNumber(vectString[4]);
            this.adress.setNeighborhood(vectString[5]);
            this.contact.setTelephone(vectString[6]);
            this.contact.setCellPhone(vectString[7]);
            this.contact.setEmail(vectString[8]);
            this.socialId = vectString[9];
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto Cliente Pessoa Jurídica");
        }
    }

    @Override
    public String dismount() {
        return id + ";" + name + ";" + socialName + ";" + adress.getPlace() + ";" + adress.getNumber() + ";" +
               adress.getNeighborhood() + ";" + contact.getTelephone() + ";" + contact.getCellPhone() + ";" +
               contact.getEmail() + ";" + socialId;
    }
}
