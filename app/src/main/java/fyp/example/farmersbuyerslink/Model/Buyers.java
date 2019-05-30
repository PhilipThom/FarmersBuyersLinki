package fyp.example.farmersbuyerslink.Model;

public class Buyers {
    private String phone,name,password,cty;

    public Buyers(){

    }

    public Buyers(String phone, String name, String password, String cty) {
        this.phone = phone;
        this.name = name;
        this.password = password;
        this.cty = cty;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCty() {
        return cty;
    }

    public void setCty(String cty) {
        this.cty = cty;
    }




}
