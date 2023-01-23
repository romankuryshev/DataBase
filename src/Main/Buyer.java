package Main;

public class Buyer {
    private int buyer_id;
    private String buyer_name;
    private String address;
    public Buyer(int buyer_id, String buyer_name, String address) {
        this.buyer_id = buyer_id;
        this.buyer_name = buyer_name;
        this.address = address;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public String getAddress() {
        return address;
    }
}
