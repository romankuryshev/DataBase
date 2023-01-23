package Main;

public class Vault{
    private int vault_id;
    private String responsible;

    public int getVault_id() {
        return vault_id;
    }

    public String getResponsible() {
        return responsible;
    }

    public Vault(int vault_id, String responsible) {
        this.vault_id = vault_id;
        this.responsible = responsible;
    }

    public void setVault_id(int vault_id) {
        this.vault_id = vault_id;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
}
