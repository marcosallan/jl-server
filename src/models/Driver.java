// Servidor

package models;

public class Driver implements Manipulation {
    private int id;
    private String name = " ";
    private String cpf = " ";
    private String cnh = " ";
    private String cnhPath = " ";
    private GenderEnum gender;

    public Driver() {
        this.gender = GenderEnum.MASCULINO;
    }
    
    public Driver(String name, String cpf, String cnh, String cnhPath, GenderEnum gender) {
        this.name = name;
        this.cpf = cpf;
        this.cnh = cnh;
        this.cnhPath = cnhPath;
        this.gender = gender;
    }

    public Driver(int id, String name, String cpf, String cnh, String cnhPath, GenderEnum gender) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.cnh = cnh;
        this.cnhPath = cnhPath;
        this.gender = gender;
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

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCnhPath() {
        return cnhPath;
    }

    public void setCnhPath(String cnhPath) {
        this.cnhPath = cnhPath;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGenre(GenderEnum gender) {
        this.gender = gender;
    }

    @Override
    public void mount(String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = Integer.parseInt(vectString[0]);
            this.name = vectString[1];
            this.cpf = vectString[2];
            this.cnh = vectString[3];
            this.cnhPath = vectString[4];
            this.gender = GenderEnum.valueOf(vectString[5].toUpperCase());
        }
        catch (NumberFormatException e) {
            throw new Exception("Erro ao montar objeto Motorista");
        }
    }

    @Override
    public void mount(int id, String datas) throws Exception {
        try {
            String[] vectString = datas.split(";");
            this.id = id;
            this.name = vectString[1];
            this.cpf = vectString[2];
            this.cnh = vectString[3];
            this.cnhPath = vectString[4];
            this.gender = GenderEnum.valueOf(vectString[5].toUpperCase());
        }
        catch (Exception e) {
            throw new Exception("Erro ao montar objeto Motorista");
        }
    }

    @Override
    public String dismount() {
        return id + ";" + name + ";" +
               cpf + ";" + cnh + ";" +
               cnhPath + ";" + gender.getGender();
    }
}
