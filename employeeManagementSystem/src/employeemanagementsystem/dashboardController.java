/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author admin
 */
public class dashboardController implements Initializable {

    @FXML
    private Button addEmployee_addBtn;

    @FXML
    private Button addEmployee_btn;

    @FXML
    private Button addEmployee_clearBtn;

    @FXML
    private TableColumn<employeeData, java.sql.Date> addEmployee_col_date;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_firstName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_gender;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_lastName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_phoneNum;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_position;

    @FXML
    private Button addEmployee_deleteBtn;

    @FXML
    private TextField addEmployee_employeeID;

    @FXML
    private TextField addEmployee_firstName;

    @FXML
    private AnchorPane addEmployee_form;

    @FXML
    private ComboBox<String> addEmployee_gender;

    @FXML
    private ImageView addEmployee_image;

    @FXML
    private Button addEmployee_importBtn;

    @FXML
    private TextField addEmployee_lastName;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private ComboBox<String> addEmployee_position;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TableView<employeeData> addEmployee_tableView;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private Button addSite_btn1;

    @FXML
    private Button addSite_btn2;

    @FXML
    private ComboBox<String> assignsite;

    @FXML
    private Button close;

    @FXML
    private DatePicker enddate;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private Label home_totalInactiveEm;

    @FXML
    private Label home_totalPresents;

    @FXML
    private Label home_totalincompletesite;

    @FXML
    private Label home_totalsitecompleted;

    @FXML
    private Label home_totalsites;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button site_clearBtn;

    @FXML
    private TableColumn<SiteData, java.sql.Date> site_col_Enddate;

    @FXML
    private TableColumn<SiteData, String> site_col_SiteLocation;

    @FXML
    private TableColumn<SiteData, java.sql.Date> site_col_Startdate;

    @FXML
    private TableColumn<SiteData, String> site_col_siteAssignedTo;

    @FXML
    private TableColumn<SiteData, String> site_col_sitename;

    @FXML
    private AnchorPane site_form;

    @FXML
    private TableView<SiteData> site_tableView;

    @FXML
    private TextField siteaddress;

    @FXML
    private TextField sitename;

    @FXML
    private DatePicker startdate;

    @FXML
    private Label username;

    @FXML
    private DatePicker addEmployee_joiningDate;
   

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private Image image;

    public void addEmployeeAdd() {

        String sql = "INSERT INTO employee "
                            + "(employee_id,firstName,lastName,gender,phoneNum,position,image,date)"
                            + "VALUES(?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            if (addEmployee_employeeID.getText().isEmpty()
                                || addEmployee_firstName.getText().isEmpty()
                                || addEmployee_lastName.getText().isEmpty()
                                || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                                || addEmployee_phoneNum.getText().isEmpty()
                                || addEmployee_position.getSelectionModel().getSelectedItem() == null
                                || getData.path == null || getData.path == "" || addEmployee_joiningDate.getValue() == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();

            } else {
                String check = "SELECT employee_id FROM employee WHERE employee_id ='"
                                    + addEmployee_employeeID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID:" + addEmployee_employeeID.getText() + " is already exist!");
                    alert.showAndWait();
                } else {
                  
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addEmployee_phoneNum.getText());
                    prepare.setString(6, (String) addEmployee_position.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);
                    java.time.LocalDate localDate = addEmployee_joiningDate.getValue();
                    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                    prepare.setDate(8, sqlDate);
                    prepare.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void generateNextEmployeeID() {
    String str = "SELECT MAX(employee_id) FROM employee";

    connect = database.connectDb();

    try {
        statement = connect.createStatement();
        result = statement.executeQuery(str);
        int nextId = 1;
        if (result.next()) {
            nextId = result.getInt(1) + 1;
        }

        addEmployee_employeeID.setText(String.valueOf(nextId));
                addEmployee_employeeID.setEditable(false); 


    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void addEmployeeDelete() {

        String sql = "DELETE FROM employee WHERE employee_id= '"
                            + addEmployee_employeeID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (addEmployee_employeeID.getText().isEmpty()
                                || addEmployee_firstName.getText().isEmpty()
                                || addEmployee_lastName.getText().isEmpty()
                                || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                                || addEmployee_phoneNum.getText().isEmpty()
                                || addEmployee_position.getSelectionModel().getSelectedItem() == null
                                || getData.path == null || getData.path == "" || addEmployee_joiningDate.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID:" + addEmployee_employeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee Deleted Successfully!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                    addEmployee_employeeID.setEditable(false); 

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEmployeeUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        java.time.LocalDate localDate = addEmployee_joiningDate.getValue();
        java.sql.Date sqlDate = (localDate != null) ? java.sql.Date.valueOf(localDate) : null;

        String sql = "UPDATE employee SET firstName='"
                            + addEmployee_firstName.getText() + "',lastName= '"
                            + addEmployee_lastName.getText() + "',gender='"
                            + addEmployee_gender.getSelectionModel().getSelectedItem() + "',position='"
                            + addEmployee_position.getSelectionModel().getSelectedItem() + "',phoneNum='"
                            + addEmployee_phoneNum.getText() + "',image='"
                            + uri + "',date= '" + sqlDate + "' WHERE employee_Id='"
                            + addEmployee_employeeID.getText() + "'";

        connect = database.connectDb();

        try {

            if (addEmployee_employeeID.getText().isEmpty()
                                || addEmployee_firstName.getText().isEmpty()
                                || addEmployee_lastName.getText().isEmpty()
                                || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                                || addEmployee_phoneNum.getText().isEmpty()
                                || addEmployee_position.getSelectionModel().getSelectedItem() == null
                                || getData.path == null || getData.path == "" || addEmployee_joiningDate.getValue() == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID:" + addEmployee_employeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEmployeeReset() {
        addEmployee_employeeID.setText("");
        addEmployee_firstName.setText("");
        addEmployee_lastName.setText("");
        addEmployee_gender.getSelectionModel().clearSelection();
        addEmployee_phoneNum.setText("");
        addEmployee_position.getSelectionModel().clearSelection();
        addEmployee_image.setImage(null);
        addEmployee_joiningDate.setValue(null);
        getData.path = "";
        generateNextEmployeeID();

    }

    public void addEmployeeInsertImage() {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 101, 127, false, true);
            addEmployee_image.setImage(image);
        }
    }

    public void addEmployeePositionList() {
        ObservableList<String> listData = FXCollections.observableArrayList("Senior Engineer", "Junior Engineer", "worker");
        addEmployee_position.setItems(listData);
    }

    public void addEmployeeGenderList() {
        ObservableList<String> listData = FXCollections.observableArrayList("Male", "Female", "Others");
        addEmployee_gender.setItems(listData);
    }

    public void addEmployeeSearch() {
        FilteredList<employeeData> filter = new FilteredList<>(addEmployeeList, e -> true);

        addEmployee_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if (predicateEmployeeData.getEmployeeId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getFirstName().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getLastName().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getGender().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPhoneNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPosition().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDate().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<employeeData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addEmployee_tableView.comparatorProperty());
        addEmployee_tableView.setItems(sortList);
    }

    public ObservableList<employeeData> addEmployeeListData() {

        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";

        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            employeeData employeeD;

            while (result.next()) {

                employeeD = new employeeData(result.getInt("employee_id"),
                                    result.getString("firstName"),
                                    result.getString("lastName"),
                                    result.getString("gender"),
                                    result.getString("phoneNum"),
                                    result.getString("position"),
                                    result.getString("image"),
                                    result.getDate("date"));

                listData.add(employeeD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<employeeData> addEmployeeList;

    public void addEmployeeShowListData() {

        addEmployeeList = addEmployeeListData();

        addEmployee_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        addEmployee_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addEmployee_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addEmployee_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        addEmployee_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addEmployee_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        addEmployee_col_date.setCellFactory(column -> new TableCell<employeeData, java.sql.Date>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            protected void updateItem(java.sql.Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    LocalDate localDate = item.toLocalDate();
                    setText(localDate.format(formatter));
                }
            }
        });

        addEmployee_tableView.setItems(addEmployeeList);
    }

    public void addEmployeeSelect() {
        employeeData employeeD = addEmployee_tableView.getSelectionModel().getSelectedItem();
        int num = addEmployee_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addEmployee_employeeID.setText(String.valueOf(employeeD.getEmployeeId()));
        addEmployee_firstName.setText(String.valueOf(employeeD.getFirstName()));
        addEmployee_lastName.setText(String.valueOf(employeeD.getLastName()));
        addEmployee_gender.getSelectionModel().select(employeeD.getGender());
        addEmployee_phoneNum.setText(String.valueOf(employeeD.getPhoneNum()));
        addEmployee_position.getSelectionModel().select(employeeD.getPosition());

        java.sql.Date sqlDate = employeeD.getDate();
        if (sqlDate != null) {
            java.time.LocalDate localDate = sqlDate.toLocalDate();
            addEmployee_joiningDate.setValue(localDate);
        } else {
            addEmployee_joiningDate.setValue(null); // Clear DatePicker if no date
        }
        getData.path = employeeD.getImage();
        String uri = "file:" + employeeD.getImage();

        image = new Image(uri, 101, 127, false, true);
        addEmployee_image.setImage(image);

    }

    public void displayUsername() {
        username.setText(getData.username);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addEmployee_form.setVisible(false);
            site_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3a4368,#28966c);");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            addSite_btn1.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == addEmployee_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(true);
            site_form.setVisible(false);

            addEmployee_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#3a4368,#28966c);");
            home_btn.setStyle("-fx-background-color:transparent");
            addSite_btn1.setStyle("-fx-background-color:transparent");

            addEmployeeGenderList();
            addEmployeePositionList();
            addEmployeeSearch();

        } else if (event.getSource() == addSite_btn1) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            site_form.setVisible(true);

            addSite_btn1.setStyle("-fx-background-color:linear-gradient(to bottom right,#3a4368,#28966c);");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
        }

    }

    public void populateAssignToDropdown() {
        try {
            String sql = "SELECT firstName, lastName FROM employee";
            connect = database.connectDb();
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            ObservableList<String> employeeNames = FXCollections.observableArrayList();
            while (result.next()) {
                String fullName = result.getString("firstName") + " " + result.getString("lastName");
                employeeNames.add(fullName);
            }

            assignsite.setItems(employeeNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSite() {

        populateAssignToDropdown();

        String sql = "INSERT INTO site "
                            + "(siteName,siteAddress,assignedTo,startDate,endDate)"
                            + "VALUES(?,?,?,?,?)";

        connect = database.connectDb();

        try {
            if (sitename.getText().isEmpty()
                                || siteaddress.getText().isEmpty()
                                || assignsite.getSelectionModel().getSelectedItem() == null
                                || enddate.getValue() == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank fields");
                alert.showAndWait();

            } else {
                String check = "SELECT siteName FROM site WHERE siteName ='"
                                    + sitename.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(sitename.getText() + " is already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, sitename.getText());
                    prepare.setString(2, siteaddress.getText());
                    prepare.setString(3, (String) assignsite.getSelectionModel().getSelectedItem());

                    java.time.LocalDate localDate = startdate.getValue();
                    java.sql.Date startDate = java.sql.Date.valueOf(localDate);
                    prepare.setDate(4, startDate);

                    java.time.LocalDate localDate1 = enddate.getValue();
                    java.sql.Date endDate = java.sql.Date.valueOf(localDate1);
                    prepare.setDate(5, endDate);
                    prepare.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();
                    addSiteShowListData();
                    addSiteReset();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSiteReset() {
        sitename.setText("");
        siteaddress.setText("");
        assignsite.getSelectionModel().clearSelection();
        startdate.setValue(null);
        enddate.setValue(null);

    }

    public ObservableList<SiteData> addSiteListData() {
        ObservableList<SiteData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM site";

        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            SiteData siteD;

            while (result.next()) {
                siteD = new SiteData(result.getString("siteName"),
                                    result.getString("siteAddress"),
                                    result.getString("assignedTo"),
                                    result.getDate("startDate"),
                                    result.getDate("endDate"));

                listData.add(siteD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<SiteData> addSiteList;

    public void addSiteShowListData() {
        addSiteList = addSiteListData();

        site_col_sitename.setCellValueFactory(new PropertyValueFactory<>("siteName"));
        site_col_SiteLocation.setCellValueFactory(new PropertyValueFactory<>("siteAddress"));
        site_col_siteAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        site_col_Startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        site_col_Enddate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        site_col_Startdate.setCellFactory(column -> new TableCell<SiteData, java.sql.Date>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            protected void updateItem(java.sql.Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    LocalDate localDate = item.toLocalDate();
                    setText(localDate.format(formatter));
                }
            }
        });

        site_col_Enddate.setCellFactory(column -> new TableCell<SiteData, java.sql.Date>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            protected void updateItem(java.sql.Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    LocalDate localDate = item.toLocalDate();
                    setText(localDate.format(formatter));
                }
            }
        });

        site_tableView.setItems(addSiteList);
    }

    public void addSiteSelect() {
        SiteData siteD = site_tableView.getSelectionModel().getSelectedItem();
        int num = site_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        sitename.setText(String.valueOf(siteD.getSiteName()));
        siteaddress.setText(String.valueOf(siteD.getSiteAddress()));
        assignsite.getSelectionModel().select(siteD.getAssignedTo());

        java.sql.Date sqlDate = siteD.getStartDate();
        if (sqlDate != null) {
            java.time.LocalDate localDate = sqlDate.toLocalDate();
            startdate.setValue(localDate);
        } else {
            startdate.setValue(null); // Clear DatePicker if no date
        }
        java.sql.Date sqlDate1 = siteD.getEndDate();
        if (sqlDate1 != null) {
            java.time.LocalDate localDate = sqlDate.toLocalDate();
            enddate.setValue(localDate);
        } else {
            enddate.setValue(null); // Clear DatePicker if no date
        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are You sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

  

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addEmployeeShowListData();
        addEmployeeGenderList();
        addEmployeePositionList();
        populateAssignToDropdown();
        addSiteShowListData();
        generateNextEmployeeID();
}

    }
   
   
