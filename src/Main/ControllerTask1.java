package Main;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControllerTask1 implements Initializable {

    @FXML
    private TableView<ResultTask1> tableAnswer;
    @FXML
    private TableColumn<ResultTask1, Integer> vaultIdColumn;
    @FXML
    private TableColumn<ResultTask1, String> buyerNameColumn;
    @FXML
    private TableColumn<ResultTask1, String> addressColumn;
    @FXML
    private TableColumn<ResultTask1, Integer> prodIdColumn;


    @FXML
    private Button btnExecuteSQL;
    @FXML
    private Button btnExecuteORM;
    @FXML
    private TextField tfInputDate;
    @FXML
    private Label labelResult;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaultIdColumn.setCellValueFactory(new PropertyValueFactory<>("vaultId"));
        buyerNameColumn.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        prodIdColumn.setCellValueFactory(new PropertyValueFactory<>("prodId"));

        btnExecuteSQL.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> tableAnswer.setItems(FXCollections.observableList(getAnswer())));
        btnExecuteORM.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> tableAnswer.setItems(FXCollections.observableList(getAnswerORM())));
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

    private List<ResultTask1> getAnswer(){
        Connection connection = getConnection();
        List<ResultTask1> ans = new ArrayList<>();
        String query = String.format("SELECT vault_id, buyer_name, address, prod_id FROM all_tables WHERE acc_date > '%s'", tfInputDate.getText());
        Statement statement;
        ResultSet result;
        try{
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            ResultTask1 temp;
            while (result.next()){
                temp = new ResultTask1(result.getInt("vault_id"),
                        result.getString("buyer_name"),
                        result.getString("address"),
                        result.getInt("prod_id"));
                ans.add(temp);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("getAnswer error");
        }
        labelResult.setText("Выполнено SQL!");
        return ans;
    }

    private List<ResultTask1> getAnswerORM(){
        String currentDate = tfInputDate.getText();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            date = format.parse(currentDate);
        }catch (ParseException e){
            System.out.println("Data parse error");
        }
        ControllerAF secondController = new ControllerAF();
        List<AllTables> temp = secondController.getData().stream().filter(item -> item.getAcc_date().compareTo(currentDate) > 0).toList();
        List<ResultTask1> ans = new ArrayList<>();
        ResultTask1 tempAns;
        for(int i = 0; i < temp.size(); i++){
            tempAns = new ResultTask1(temp.get(i).getVault_id(),
                    temp.get(i).getBuyer_name(),
                    temp.get(i).getBuyer_address(),
                    temp.get(i).getProd_id());
            ans.add(tempAns);
        }
        labelResult.setText("Выполнено ORM!");
        return ans;
    }

}
