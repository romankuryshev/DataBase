package Main;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerTask2 implements Initializable {

    @FXML
    private TableView<ResultTask2> tableTask2;
    @FXML
    private TableColumn<ResultTask2, Integer> columnVaultId;
    @FXML
    private TableColumn<ResultTask2, String> columnBuyerName;
    @FXML
    private TableColumn<ResultTask2, String> columnResponsible;
    @FXML
    private TableColumn<ResultTask2, Integer> columnNumberAcc;
    @FXML
    private TableColumn<ResultTask2, Integer> totalNumber;

//    @FXML
//    private TextField inputName;
//
//    @FXML
//    private Button btnExecute;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableTask2.setItems(FXCollections.observableList(getResultTask2()));
        columnVaultId.setCellValueFactory(new PropertyValueFactory<>("vaultId"));
        columnResponsible.setCellValueFactory(new PropertyValueFactory<>("responsible"));
        columnBuyerName.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
        columnNumberAcc.setCellValueFactory(new PropertyValueFactory<>("numberAccounting"));
        totalNumber.setCellValueFactory(new PropertyValueFactory<>("totalNumber"));


//        btnExecute.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> tableTask2.setItems(FXCollections.observableList(getResultTask2())));
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

    private List<ResultTask2> getResultTask2(){
        List<ResultTask2> ans = new ArrayList<>();
        String query = "SELECT a.vault_id," +
                "buyer_name, " +
                "responsible, " +
                "row_number() over (partition by b.buyer_name order by a.acc_date) as number_accounting_for_this_buyer, " +
                "count(*) over (partition by b.buyer_name) as total_accounting_for_this_buyer " +
                "FROM accounting a left JOIN buyers b on b.buyer_id = a.buyer_id left join vaults v on v.vault_id = a.vault_id;";

//        String query = String.format("SELECT a.vault_id," +
//                "buyer_name, " +
//                "responsible, " +
//                "row_number() over (partition by b.buyer_name order by a.acc_date) as number_accounting_for_this_buyer, " +
//                "count(*) over (partition by b.buyer_name) as total_accounting_for_this_buyer " +
//                "FROM accounting a left JOIN buyers b on b.buyer_id = a.buyer_id left join vaults v on v.vault_id = a.vault_id " +
//                "WHERE b.buyer_name = '%s';", inputName.getText());
        Connection conn;
        Statement st;
        ResultSet result;
        try{
            conn = getConnection();
            st = conn.createStatement();
            result = st.executeQuery(query);
            ResultTask2 temp;
            while (result.next()){
                temp = new ResultTask2(result.getInt("vault_id"),
                        result.getString("responsible"),
                        result.getString("buyer_name"),
                        result.getInt("number_accounting_for_this_buyer"),
                        result.getInt("total_accounting_for_this_buyer"));
                ans.add(temp);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Connection error");
        }
        return ans;
    }

}
