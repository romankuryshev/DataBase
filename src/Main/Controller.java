package Main;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/Zad1";
    public static final String USER = "postgres";
    public static final String PASSWORD = "1653";

    @FXML
    private TableView<Vault> vaults;        // таблица хранилища
    @FXML
    private TableColumn<Vault, Integer> vault_id;
    @FXML
    private TableColumn<Vault, String> responsible;

    @FXML
    private TableView<Buyer> buyers;        // таблица покупатели
    @FXML
    private TableColumn<Buyer, Integer> buyer_id;
    @FXML
    private TableColumn<Buyer, String> buyer_name;
    @FXML
    private TableColumn<Buyer, String> address;

    @FXML
    private TableView<Account> accounting;      //  таблица заказов
    @FXML
    private  TableColumn<Account, Integer> acc_id;
    @FXML
    private  TableColumn<Account, String> acc_date;
    @FXML
    private  TableColumn<Account, Integer> prod_count;
    @FXML
    private  TableColumn<Account, String> unit;
    @FXML
    private  TableColumn<Account, Integer> prod_id;
    @FXML
    private  TableColumn<Account, Integer> acc_vault_id;
    @FXML
    private  TableColumn<Account, Integer> acc_buyer_id;

    @FXML       // поля для Хранимой Процедуры
    private TextField FieldVaultId;
    @FXML
    private DatePicker DateStart;
    @FXML
    private DatePicker DateFinish;
    @FXML
    private TextField fieldAnswer;
    @FXML
    private Button ButtonExecute;
    @FXML
    private Button btnInsertVaults; // поля для работы с таблицой хранилища
    @FXML
    private Button btnUpdateVaults;
    @FXML
    private Button btnDeleteVaults;

    @FXML
    private TextField changeVaultId;
    @FXML
    private TextField ChangeResponsible;
                                        // поля для работы с таблицей покупателей
    @FXML
    private TextField changeBuyerId;
    @FXML
    private TextField changeBuyerName;
    @FXML
    private TextField changeBuyerAddress;

    @FXML
    private Button btnInsertBuyers;
    @FXML
    private Button btnUpdateBuyers;
    @FXML
    private Button btnDeleteBuyers;
    @FXML
    private Button btnRefresh;  // обновление всех данных
                                                // кнопки других форм
    @FXML
    private Button alternativeForm;
    @FXML
    private Button btnTask1;
    @FXML
    private Button btnTask2;

    @FXML
    private Button btnTask3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaults.setItems(FXCollections.observableList(getVaults()));
        vault_id.setCellValueFactory(new PropertyValueFactory<>("vault_id"));
        responsible.setCellValueFactory(new PropertyValueFactory<>("responsible"));
        responsible.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vault, String>> () {
            @Override
            public void handle(TableColumn.CellEditEvent<Vault, String> vaultIntegerCellEditEvent) {
                (vaultIntegerCellEditEvent.getTableView().getItems().get(vaultIntegerCellEditEvent.getTablePosition().getRow())).setResponsible(vaultIntegerCellEditEvent.getNewValue());
            }
        });

        vault_id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vault, Integer>> () {
            @Override
            public void handle(TableColumn.CellEditEvent<Vault, Integer> vaultIntegerCellEditEvent) {
                (vaultIntegerCellEditEvent.getTableView().getItems().get(vaultIntegerCellEditEvent.getTablePosition().getRow())).setVault_id(vaultIntegerCellEditEvent.getNewValue());
            }
        });
        vaults.setEditable(true);
        vault_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        responsible.setCellFactory(TextFieldTableCell.forTableColumn());


        buyers.setItems(FXCollections.observableList(getBuyers()));
        buyer_id.setCellValueFactory(new PropertyValueFactory<>("buyer_id"));
        buyer_name.setCellValueFactory(new PropertyValueFactory<>("buyer_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        buyers.setEditable(true);
        buyer_id.setCellFactory(TextFieldTableCell.forTableColumn( new IntegerStringConverter()));
        buyer_name.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setCellFactory(TextFieldTableCell.forTableColumn());
        buyer_id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Buyer, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Buyer, Integer> buyerIntegerCellEditEvent) {
                buyerIntegerCellEditEvent.getTableView().getItems().get(buyerIntegerCellEditEvent.getTablePosition().getRow()).setBuyer_id(buyerIntegerCellEditEvent.getNewValue());
            }
        });
        buyer_name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Buyer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Buyer, String> buyerIntegerCellEditEvent) {
                buyerIntegerCellEditEvent.getTableView().getItems().get(buyerIntegerCellEditEvent.getTablePosition().getRow()).setBuyer_name(buyerIntegerCellEditEvent.getNewValue());
            }
        });
        address.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Buyer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Buyer, String> buyerIntegerCellEditEvent) {
                buyerIntegerCellEditEvent.getTableView().getItems().get(buyerIntegerCellEditEvent.getTablePosition().getRow()).setAddress(buyerIntegerCellEditEvent.getNewValue());
            }
        });

        accounting.setItems(FXCollections.observableList(getAccounting()));
        acc_id.setCellValueFactory(new PropertyValueFactory<>("acc_id"));
        acc_date.setCellValueFactory(new PropertyValueFactory<>("acc_date"));
        prod_count.setCellValueFactory(new PropertyValueFactory<>("prod_count"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        prod_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
        acc_vault_id.setCellValueFactory(new PropertyValueFactory<>("vault_id"));
        acc_buyer_id.setCellValueFactory(new PropertyValueFactory<>("buyer_id"));
        accounting.setEditable(true);
        acc_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        acc_date.setCellFactory(TextFieldTableCell.forTableColumn());
        prod_count.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        unit.setCellFactory(TextFieldTableCell.forTableColumn());
        prod_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        acc_vault_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        acc_buyer_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //кнопки других форм
        alternativeForm.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Main.showAlternativeForm());
        btnTask1.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Main.showTask1Form());
        btnTask2.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Main.showTask2Form());
        btnTask3.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> Main.showTask3Form());

        // кнопки изменения
        ButtonExecute.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> executeProcedure());

        btnInsertVaults.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> insertRecordVaults());
        btnDeleteVaults.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> deleteRecordVaults());
        btnUpdateVaults.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> updateRecordVaults());

        btnInsertBuyers.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> insertRecordBuyers());
        btnUpdateBuyers.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> updateRecordBuyers());
        btnDeleteBuyers.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> deleteRecordBuyers());
        btnRefresh.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> refreshAll());
    }
    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connection");
        }
        return conn;
    }
    public List<Vault> getVaults(){
        List<Vault> vaults_list = new ArrayList<>();
        Connection conn = getConnection();
        String query_select = "SELECT * FROM vaults";
        Statement statem;
        ResultSet result;
        try {
            statem = conn.createStatement();
            result = statem.executeQuery(query_select);
            Vault vault;
            while (result.next()){
                vault = new Vault(result.getInt("vault_id"), result.getString("responsible"));
                vaults_list.add(vault);
            }
        }catch (SQLException e){
            throw new RuntimeException("Error get vault");
        }
        return vaults_list;
    }

    public List<Buyer> getBuyers(){
        List<Buyer> buyers_list = new ArrayList<>();
        Connection conn = getConnection();
        String sql_select = "SELECT * FROM buyers";
        Statement st;
        ResultSet result;
        try{
            st = conn.createStatement();
            result = st.executeQuery(sql_select);
            Buyer temp;
            while(result.next()){
                temp = new Buyer(result.getInt("buyer_id"),
                        result.getString("buyer_name"),
                        result.getString("address"));
                buyers_list.add(temp);
            }
        }catch (SQLException e){
            System.out.println("Error get buyers");
        }
        return buyers_list;
    }

    public List<Account> getAccounting(){
        List<Account> list = new ArrayList<>();
        Connection conn = getConnection();
        String sql_select = "SELECT * FROM accounting";
        Statement st;
        ResultSet result;
        try{
            st = conn.createStatement();
            result = st.executeQuery(sql_select);
            Account temp;
            while (result.next()){
                temp = new Account(result.getInt("acc_id"),
                        result.getString("acc_date"),
                        result.getInt("prod_count"),
                        result.getString("unit"),
                        result.getInt("prod_id"),
                        result.getInt("vault_id"),
                        result.getInt("buyer_id"));
                list.add(temp);
            }
        } catch (SQLException e){
            System.out.println("Error get Accounting");
        }
        return list;
    }

    private void executeProcedure(){
        try {
            Integer vaultId = Integer.parseInt(this.FieldVaultId.getText());
            String date1 = this.DateStart.getValue().toString();
            String date2 = this.DateFinish.getValue().toString();
            String executeProcedure = "SELECT get_count_accountings_from_vault(" + vaultId + ", '" + date1 + "', '" + date2 + "')";

            Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(executeProcedure);
            res.next();
            Integer result = res.getInt("get_count_accountings_from_vault");
            fieldAnswer.setText(result.toString());
        }catch (SQLException e){
            System.out.println("Procedure Error");
            e.printStackTrace();
        }
    }
    public static final String INSERT_VAULTS_QUERY = "INSERT into vaults(vault_id, responsible)" +
                                                    " VALUES " +
                                                    "(%s, '%s')";
    public static final String DELETE_VAULTS_QUERY = "DELETE FROM vaults " +
                                                    "WHERE vault_id = %s";
    public static final String UPDATE_VAULTS_QUERY = "UPDATE vaults " +
                                                    "SET responsible = '%s' " +
                                                    "WHERE vault_id = %s";

    private void executeQuery(String query) throws SQLException{
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        st.execute(query);
    }

    private void insertRecordVaults(){
        String query = String.format(INSERT_VAULTS_QUERY, changeVaultId.getText(), ChangeResponsible.getText());
        try{
            executeQuery(query);
            vaults.getItems().add(new Vault(Integer.parseInt(changeVaultId.getText()), ChangeResponsible.getText()));
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Error insert");
        }
    }

    private void deleteRecordVaults(){
        Vault vault = vaults.getSelectionModel().getSelectedItem();
        if(vaults != null){
            String query = String.format(DELETE_VAULTS_QUERY, vault.getVault_id());
            try{
                executeQuery(query);
                vaults.getItems().removeIf(item -> item.getVault_id() == vault.getVault_id());
            }catch (SQLException e){
                throw new RuntimeException("Error delete");
            }
        }
    }

    private void updateRecordVaults(){
        Vault vault = vaults.getSelectionModel().getSelectedItem();
        if(vault != null){
            String query = String.format(UPDATE_VAULTS_QUERY, vault.getResponsible(), vault.getVault_id());
            try {
                executeQuery(query);
                int index = vaults.getItems().indexOf(vault);
                //vaults.getItems().set(index, vault);
            }catch (SQLException e){
                throw new RuntimeException("Error update");
            }
        }
    }
    private void refreshAll(){
        vaults.setItems(FXCollections.observableList(getVaults()));
        buyers.setItems(FXCollections.observableList(getBuyers()));
        accounting.setItems(FXCollections.observableList(getAccounting()));
    }

    public static final String INSERT_BUYERS_QUERY = "INSERT into buyers(buyer_id, buyer_name, address)" +
            " VALUES " +
            "(%s, '%s', '%s')";
    public static final String DELETE_BUYERS_QUERY = "DELETE FROM buyers " +
            "WHERE buyer_id = %s";
    public static final String UPDATE_BUYERS_QUERY = "UPDATE buyers " +
            "SET buyer_name = '%s', address = '%s' " +
            "WHERE buyer_id = %s";

    private void insertRecordBuyers(){
        String query = String.format(INSERT_BUYERS_QUERY, changeBuyerId.getText(), changeBuyerName.getText(), changeBuyerAddress.getText());
        try{
            executeQuery(query);
            buyers.getItems().add(new Buyer(Integer.parseInt(changeBuyerId.getText()), changeBuyerName.getText(), changeBuyerAddress.getText()));
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Error insert");
        }
    }

    private void deleteRecordBuyers(){
        Buyer buyer = buyers.getSelectionModel().getSelectedItem();
        if(buyers != null){
            String query = String.format(DELETE_BUYERS_QUERY, buyer.getBuyer_id());
            try{
                executeQuery(query);
                buyers.getItems().removeIf(item -> item.getBuyer_id() == buyer.getBuyer_id());
            }catch (SQLException e){
                throw new RuntimeException("Error delete");
            }
        }
    }

    private void updateRecordBuyers(){
        Buyer buyer = buyers.getSelectionModel().getSelectedItem();
        if(buyer != null){
            String query = String.format(UPDATE_BUYERS_QUERY, buyer.getBuyer_name(), buyer.getAddress(), buyer.getBuyer_id());
            try {
                executeQuery(query);
                int index = buyers.getItems().indexOf(buyer);
                //buyers.getItems().set(index, buyer);
            }catch (SQLException e){
                throw new RuntimeException("Error update");
            }
        }
    }
}