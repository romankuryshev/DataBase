package Main;

public class ResultTask1 {
    private int vaultId;
    private String buyerName;
    private String address;
    private int prodId;

    public ResultTask1(int vaultId, String buyerName, String address, int prodId) {
        this.vaultId = vaultId;
        this.buyerName = buyerName;
        this.address = address;
        this.prodId = prodId;
    }

    public int getVaultId() {
        return vaultId;
    }

    public void setVaultId(int vaultId) {
        this.vaultId = vaultId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }
}
