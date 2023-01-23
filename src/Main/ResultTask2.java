package Main;

public class ResultTask2 {
    int vaultId;
    String responsible;
    String buyerName;
    int numberAccounting;
    int totalNumber;

    public ResultTask2(int vaultId, String responsible, String buyerName, int numberAccounting, int totalNumber) {
        this.vaultId = vaultId;
        this.responsible = responsible;
        this.buyerName = buyerName;
        this.numberAccounting = numberAccounting;
        this.totalNumber = totalNumber;
    }

    public int getVaultId() {
        return vaultId;
    }

    public void setVaultId(int vaultId) {
        this.vaultId = vaultId;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public int getNumberAccounting() {
        return numberAccounting;
    }

    public void setNumberAccounting(int numberAccounting) {
        this.numberAccounting = numberAccounting;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
}
