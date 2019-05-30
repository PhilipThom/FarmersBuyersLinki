package fyp.example.farmersbuyerslink.Model;

public class Users {
    private String phone,name,password,cty,quantity,type;

    public Users(){

    }

    public Users(String phone, String name, String password, String cty,String quantity,String type) {
        this.phone = phone;
        this.name = name;
        this.password = password;
        this.cty = cty;
        this.quantity=quantity;
        this.type=type;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
