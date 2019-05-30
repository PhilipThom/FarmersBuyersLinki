package fyp.example.farmersbuyerslink.Model;

public class FarmersUploads {

    private String quantity,type;

    public FarmersUploads(){

    }

    public FarmersUploads(String quantity, String type) {
        this.quantity = quantity;
        this.type = type;

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
