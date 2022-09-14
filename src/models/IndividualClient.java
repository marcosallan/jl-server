// Servidor

package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IndividualClient extends Client implements Manipulation {
    SimpleDateFormat sdf;
    private Date birhDate;
    private GenderEnum gender;

    public IndividualClient() throws ParseException {
        super();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        birhDate = sdf.parse(sdf.format(new Date()));
        gender = GenderEnum.MASCULINO;
    }
    
    public IndividualClient(Date birhDate, String name, String socialName, Adress adress, Contact contact, String socialId, GenderEnum gender) {
        super(name, socialName, adress, contact, socialId);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.birhDate = birhDate;
        this.gender = gender;
    }

    public IndividualClient(Date birhDate, int id, String name, String socialName, Adress adress, Contact contact, String socialId, GenderEnum gender) {
        super(id, name, socialName, adress, contact, socialId);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.birhDate = birhDate;
        this.gender = gender;
    }

    public Date getBirhDate() {
        return birhDate;
    }

    public void setBirhDate(Date birhDate) {
        this.birhDate = birhDate;
    }

    public GenderEnum getGender() {
        return gender;
    }
    
    public int getAge() {
        long birthDateMS = getBirhDate().getTime();
        long currentTime = new Date().getTime();
        double years = (currentTime - birthDateMS) / 1000 / 60 / 60 / 24 / 365.25;
        years = Math.floor(years);
        return (int) years;
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
            this.birhDate = sdf.parse(vectString[10]);
            this.gender = GenderEnum.valueOf(vectString[11].toUpperCase());
        }
        catch (NumberFormatException | ParseException e) {
            throw new Exception("Erro ao montar objeto Cliente Pessoa Física");
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
            this.birhDate = sdf.parse(vectString[10]);
            this.gender = GenderEnum.valueOf(vectString[11].toUpperCase());
        }
        catch (ParseException e) {
            throw new Exception("Erro ao montar objeto Cliente Pessoa Física");
        }
    }

    @Override
    public String dismount() {
        String individualDismount = "";
        individualDismount += id + ";";
        individualDismount += name + ";";
        individualDismount += socialName + ";";
        individualDismount += adress.getPlace() + ";";
        individualDismount += adress.getNumber() + ";";
        individualDismount += adress.getNeighborhood() + ";";
        individualDismount += contact.getTelephone() + ";";
        individualDismount += contact.getCellPhone() + ";";
        individualDismount += contact.getEmail() + ";";
        individualDismount += socialId + ";";
        individualDismount += sdf.format(birhDate) + ";";
        individualDismount += gender.getGender();
        return individualDismount;
    }
}
