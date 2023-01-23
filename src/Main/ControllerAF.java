package Main;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
public class ControllerAF implements Initializable {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/Zad1";
    public static final String USER = "postgres";
    public static final String PASSWORD = "1653";


    @FXML
    private TableView<AllTables> allTables;
    @FXML
    private TableColumn<AllTables, Integer> acc_id;
    @FXML
    private TableColumn<AllTables, Date> acc_date;
    @FXML
    private TableColumn<AllTables, Integer> prod_count;
    @FXML
    private TableColumn<AllTables, String> unit;
    @FXML
    private TableColumn<AllTables, Integer> prod_id;
    @FXML
    private TableColumn<AllTables, Integer> vault_id;
    @FXML
    private TableColumn<AllTables, String> responsible;
    @FXML
    private TableColumn<AllTables, Integer> buyer_id;
    @FXML
    private TableColumn<AllTables, String> buyer_name;
    @FXML
    private TableColumn<AllTables, String> buyer_address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allTables.setItems(FXCollections.observableList(getData()));
        acc_id.setCellValueFactory(new PropertyValueFactory<>("acc_id"));
        acc_date.setCellValueFactory(new PropertyValueFactory<>("acc_date"));
        prod_count.setCellValueFactory(new PropertyValueFactory<>("prod_count"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        prod_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
        vault_id.setCellValueFactory(new PropertyValueFactory<>("vault_id"));
        responsible.setCellValueFactory(new PropertyValueFactory<>("responsible"));
        buyer_id.setCellValueFactory(new PropertyValueFactory<>("buyer_id"));
        buyer_name.setCellValueFactory(new PropertyValueFactory<>("buyer_name"));
        buyer_address.setCellValueFactory(new PropertyValueFactory<>("buyer_address"));
    }
    private Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
            throw  new RuntimeException("Error connection Alternative Form");
        }
        return connection;
    }

    public List<AllTables> getData(){
        List<AllTables> data = new ArrayList<>();
        String querySelectAll = "SELECT * FROM all_tables";
        Statement st;
        ResultSet res;
        try{
            Connection conn = getConnection();
            st = conn.createStatement();
            res = st.executeQuery(querySelectAll);
            AllTables temp;
            while (res.next()){
                temp = new AllTables(res.getInt("acc_id"),
                        res.getString("acc_date"),
                        res.getInt("prod_count"),
                        res.getString("unit"),
                        res.getInt("prod_id"),
                        res.getInt("vault_id"),
                        res.getString("responsible"),
                        res.getInt("buyer_id"),
                        res.getString("buyer_name"),
                        res.getString("address")
                        );
                data.add(temp);
            }
        }catch (SQLException e ){
            e.printStackTrace();
            throw new RuntimeException("SQL Error view");
        }
        return data;
    }
}