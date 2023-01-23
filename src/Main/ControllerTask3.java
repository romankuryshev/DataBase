package Main;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.*;

public class ControllerTask3 implements Initializable {

    @FXML
    private TableView<ResultTask3> tableResult3;
    @FXML
    private TableColumn<ResultTask3, Integer> columnVaultId;
    @FXML
    private Button btnFindSQL;
    @FXML
    private Button btnFindORM;
    @FXML
    private TextField textFieldCity;
    @FXML
    private TextField textFieldCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnVaultId.setCellValueFactory(new PropertyValueFactory<>("vault_id"));
        btnFindSQL.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> tableResult3.setItems(FXCollections.observableList(getResultTask3())));
        btnFindORM.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> tableResult3.setItems(FXCollections.observableList(getResultTask3ORM())));
    }
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/Zad1";
    public static final String USER = "postgres";
    public static final String PASSWORD = "1653";

    public static Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Connection error");
        }
        return conn;
    }

    private List<ResultTask3> getResultTask3(){
        Connection conn;
        Statement st;
        ResultSet result;
        List<ResultTask3> ans = new ArrayList<>();
        String query = String.format("SELECT v.vault_id FROM vaults v " +
                "WHERE NOT EXISTS (SELECT * FROM buyers b " +
                        "WHERE b.address = '%s' AND NOT EXISTS (SELECT * FROM accounting a " +
                                "WHERE a.buyer_id = b.buyer_id AND " +
                                "a.prod_count > %s AND " +
                                "v.vault_id = a.vault_id));", textFieldCity.getText(), textFieldCount.getText());
        try{
            conn = getConnection();
            st = conn.createStatement();
            result = st.executeQuery(query);
            ResultTask3 temp;
            while(result.next()){
                temp = new ResultTask3(result.getInt("vault_id"));
                ans.add(temp);
            }
        }catch (SQLException e ){
            e.printStackTrace();
            throw new RuntimeException("ResultTask3 Error");
        }
        return ans;
    }
    private List<ResultTask3> getResultTask3ORM(){
        List<ResultTask3> ans = new ArrayList<>();
        List<Vault> vaultsList = new Controller().getVaults();
        List<Buyer> buyersList = new Controller().getBuyers();
        List<Account> accountList = new Controller().getAccounting();
        String inputCity = textFieldCity.getText();
        Integer inputCount = Integer.parseInt(textFieldCount.getText());
        for (Vault v: vaultsList) {
            boolean t = true;
            for (Buyer b: buyersList) {
                if(b.getAddress().equals(inputCity)){
                    boolean f = false;
                    for (Account a: accountList){
                        if(a.getBuyer_id() == b.getBuyer_id() && a.getProd_count() > inputCount && v.getVault_id() == a.getVault_id())
                            f = true;
                    }
                    if(!f) t = false;
                }
            }
            if(t) ans.add(new ResultTask3(v.getVault_id()));
        }
        return ans;
    }

}
