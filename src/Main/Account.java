package Main;

public class Account {
    private int acc_id;
    private String acc_date;
    private int prod_count;
    private String unit;
    private int prod_id;
    private int vault_id;
    private int buyer_id;

    public Account(int acc_id, String acc_date, int prod_count, String unit, int prod_id, int vault_id, int buyer_id) {
        this.acc_id = acc_id;
        this.acc_date = acc_date;
        this.prod_count = prod_count;
        this.unit = unit;
        this.prod_id = prod_id;
        this.vault_id = vault_id;
        this.buyer_id = buyer_id;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getAcc_date() {
        return acc_date;
    }

    public void setAcc_date(String acc_date) {
        this.acc_date = acc_date;
    }

    public int getProd_count() {
        return prod_count;
    }

    public void setProd_count(int prod_count) {
        this.prod_count = prod_count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getVault_id() {
        return vault_id;
    }

    public void setVault_id(int vault_id) {
        this.vault_id = vault_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }
}
