package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.DatabaseConnection.getConnection;

/**
 * d by George Stratulat on 17/05/2017.
 */
public class AdminController {
    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TableColumn<Customer, Integer> id_customer;
    @FXML
    private TableColumn<Customer, String> name_customer;
    @FXML
    private TableColumn<Customer, Long> cpr_customer;
    @FXML
    private TableColumn<Customer, String> email_customer;
    @FXML
    private TableColumn<Customer, String> password_customer;

    @FXML
    private TableView<TypeOfVehicle> tableTypeOfVehicle;
    @FXML
    private TableColumn<TypeOfVehicle, Integer> id_typeOfVehicle;
    @FXML
    private TableColumn<TypeOfVehicle, String> brand_typeOfVehicle;
    @FXML
    private TableColumn<TypeOfVehicle, String> capacity_typeOfVehicle;


    public static ObservableList<Customer> dataCustomer;
    public static ObservableList<TypeOfVehicle> dataTypeOfVehicle;
    public static ObservableList<Vehicle> dataVehicle;
    public static ObservableList<Reservation> dataReservation;

    @FXML
    public void initialize(){
        try {
            loadCustomers();
            loadTypeOfVehicles();
            loadVehicle();
            loadReservations();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomers() throws Exception{
        try {
            Connection con = getConnection();
            dataCustomer = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),  rs.getLong(5));
                dataCustomer.add(customer);
            }
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        id_customer.setCellValueFactory(new PropertyValueFactory<>("id_customer"));
        name_customer.setCellValueFactory(new PropertyValueFactory<>("name_customer"));
        cpr_customer.setCellValueFactory(new PropertyValueFactory<>("cpr_customer"));
        email_customer.setCellValueFactory(new PropertyValueFactory<>("email_customer"));
        password_customer.setCellValueFactory(new PropertyValueFactory<>("password_customer"));
        tableCustomers.setItems(null);
        tableCustomers.setItems(dataCustomer);
    }

    private void loadTypeOfVehicles() throws Exception{
        try {
            Connection con = getConnection();
            dataTypeOfVehicle = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM typeofvehicle");
            while (rs.next()) {
                TypeOfVehicle typeOfVehicle = new TypeOfVehicle(rs.getInt(1), rs.getString(2), rs.getString(3));
                dataTypeOfVehicle.add(typeOfVehicle);
            }
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        id_typeOfVehicle.setCellValueFactory(new PropertyValueFactory<>("id_typeOfVehicle"));
        brand_typeOfVehicle.setCellValueFactory(new PropertyValueFactory<>("brand_typeOfVehicle"));
        capacity_typeOfVehicle.setCellValueFactory(new PropertyValueFactory<>("capacity_typeOfVehicle"));
        tableTypeOfVehicle.setItems(null);
        tableTypeOfVehicle.setItems(dataTypeOfVehicle);

    }

    @FXML
    private Button deleteTypeOfVehicleButton;
    @FXML
    private void deleteTypeOfVehicle(ActionEvent event) throws Exception{
        TypeOfVehicle typeOfVehicle = tableTypeOfVehicle.getSelectionModel().getSelectedItem();
        try{
            Connection con = getConnection();
            con.createStatement().executeUpdate("DELETE FROM typeofvehicle WHERE id_typeOfVehicle =" + typeOfVehicle.getId_typeOfVehicle());
        }catch(SQLException ex){
            System.err.println("Error" + ex);
        }
        loadTypeOfVehicles();
    }

    @FXML
    private TableView<Vehicle> tableVehicle;
    @FXML
    private TableColumn<Vehicle, Integer> id_vehicle;
    @FXML
    private TableColumn<Vehicle, Integer> type_vehicle;
    @FXML
    private TableColumn<Vehicle, String> license_vehicle;
    @FXML
    private TableColumn<Vehicle, Boolean> booked_vehicle;
    @FXML
    private TableColumn<Vehicle, Integer> price_vehicle;

    private void loadVehicle() throws Exception{
        try {
            Connection con = getConnection();
            dataVehicle = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM vehicle");
            while (rs.next()) {
                Vehicle vehicle = new Vehicle(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                dataVehicle.add(vehicle);
            }
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        id_vehicle.setCellValueFactory(new PropertyValueFactory<>("id_vehicle"));
        type_vehicle.setCellValueFactory(new PropertyValueFactory<>("type_vehicle"));
        license_vehicle.setCellValueFactory(new PropertyValueFactory<>("license_vehicle"));
        price_vehicle.setCellValueFactory(new PropertyValueFactory<>("price_vehicle"));
        tableVehicle.setItems(null);
        tableVehicle.setItems(dataVehicle);

    }

    @FXML
    private Button deleteVehicleButton;
    @FXML
    private void deleteVehicle(ActionEvent event) throws Exception{
        Vehicle vehicle = tableVehicle.getSelectionModel().getSelectedItem();
        try{
            Connection con = getConnection();
            con.createStatement().executeUpdate("DELETE FROM vehicle where id_vehicle ="+vehicle.getId_vehicle());
        }catch(SQLException ex){
            System.err.println("Error" + ex);
        }
        loadVehicle();
    }

    @FXML
    private Button addVehicleButton;
    @FXML
    private TextField vehicleType;
    @FXML
    private TextField vehicleLicense;
    @FXML
    private TextField vehicleBooked;
    @FXML
    private TextField vehiclePrice;

    @FXML
    private void addVehicle(ActionEvent event) throws Exception{
        String vehicle_Type = vehicleType.getText();
        String vehicle_License = vehicleLicense.getText();
        String vehicle_Booked = vehicleBooked.getText();
        String vehicle_Price = vehiclePrice.getText();

        try{
            Connection connection = getConnection();
            String sql = "INSERT INTO vehicle(type_vehicle, license_vehicle, price_vehicle) VALUES (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vehicle_Type);
            statement.setString(2, vehicle_License);
            statement.setString(3,vehicle_Price);
            statement.execute();
            vehicleType.setText("");
            vehicleLicense.setText("");
            vehiclePrice.setText("");

        }catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        loadVehicle();
    }

    @FXML
    private Button editVehicleButton;

    @FXML
    private void editVehicle(ActionEvent event) throws Exception{
        Vehicle vehicle = tableVehicle.getSelectionModel().getSelectedItem();
        vehicleType.setText(Integer.toString(vehicle.getType_vehicle()));
        vehicleLicense.setText(vehicle.getLicense_vehicle());
        vehiclePrice.setText(Integer.toString(vehicle.getPrice_vehicle()));

    }

    @FXML
    private Button saveVehicleButton;

    @FXML
    private void saveVehicle(ActionEvent event) throws Exception {
        Vehicle vehicle = tableVehicle.getSelectionModel().getSelectedItem();
        try{
            Connection con = getConnection();
            String sql = "UPDATE vehicle SET type_vehicle= ?, license_vehicle= ?, price_vehicle= ? WHERE id_vehicle= ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, vehicleType.getText());
            statement.setString(2, vehicleLicense.getText());
            statement.setString(3, vehiclePrice.getText());
            statement.setString(4, Integer.toString(vehicle.getId_vehicle()));
            statement.executeUpdate();

        }catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        vehicleType.setText("");
        vehicleLicense.setText("");
        vehicleBooked.setText("");
        vehiclePrice.setText("");
        loadVehicle();

    }



    @FXML
    private Button addTypeOfVehicleButton;
    @FXML
    private TextField brand_tov;
    @FXML
    private TextField capacity_tov;
    @FXML
    private void addTypeOfVehicle(ActionEvent event) throws Exception{
        String brand_typeOfVehicle = brand_tov.getText();
        String capacity_typeOfVehicle = capacity_tov.getText();
        try{
            Connection con = getConnection();
            String sql = "INSERT INTO typeofvehicle(brand_typeOfVehicle, capacity_typeOfVehicle) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, brand_typeOfVehicle);
            statement.setString(2, capacity_typeOfVehicle);
            statement.execute();
            brand_tov.setText("");
            capacity_tov.setText("");

        }catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        loadTypeOfVehicles();

    }

    @FXML
    private TableView<Reservation> tableReservation;
    @FXML
    private TableColumn<Reservation, String> start_reservation;
    @FXML
    private TableColumn<Reservation, Boolean> paid_reservation;
    @FXML
    private TableColumn<Reservation, String> pickup_reservation;
    @FXML
    private TableColumn<Reservation, Integer> id_reservation;
    @FXML
    private TableColumn<Reservation, Boolean> bike_reservation;
    @FXML
    private TableColumn<Reservation, Boolean> dropoff_reservation;
    @FXML
    private TableColumn<Reservation, Boolean> mechanic_approval_reservation;
    @FXML
    private TableColumn<Reservation, Integer> id_customer_reservation;
    @FXML
    private TableColumn<Reservation, Boolean> child_reservation;
    @FXML
    private TableColumn<Reservation, Boolean> picnic_reservation;
    @FXML
    private TableColumn<Reservation, Integer> price_reservation;
    @FXML
    private TableColumn<Reservation, String> payment_type_reservation;
    @FXML
    private Button deleteReservationButton;
    @FXML
    private TableColumn<Reservation, String> mechanic_comments;
    @FXML
    private TableColumn<Reservation, String> finish_reservation;
    @FXML
    private TableColumn<Reservation, Boolean> fuel_level_over_half_reservation;
    @FXML
    private TableColumn<Reservation, Integer> vehicle_reservation;
    @FXML
    private Button addReservationButton;

    private void loadReservations() throws Exception{
        try {
            Connection con = getConnection();
            dataReservation = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation");
            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getBoolean(10),
                        rs.getBoolean(11), rs.getString(12), rs.getBoolean(13), rs.getBoolean(14), rs.getBoolean(15), rs.getString(16));
                dataReservation.add(reservation);

            }
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        id_reservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        start_reservation.setCellValueFactory(new PropertyValueFactory<>("start_reservation"));
        finish_reservation.setCellValueFactory(new PropertyValueFactory<>("finish_reservation"));
        id_customer_reservation.setCellValueFactory(new PropertyValueFactory<>("reservation_customer_id"));
        vehicle_reservation.setCellValueFactory(new PropertyValueFactory<>("vehicle_reservation"));
        pickup_reservation.setCellValueFactory(new PropertyValueFactory<>("pickup_reservation"));
        dropoff_reservation.setCellValueFactory(new PropertyValueFactory<>("dropoff_reservation"));
        price_reservation.setCellValueFactory(new PropertyValueFactory<>("price_reservation"));
        bike_reservation.setCellValueFactory(new PropertyValueFactory<>("bike_reservation"));
        child_reservation.setCellValueFactory(new PropertyValueFactory<>("child_reservation"));
        picnic_reservation.setCellValueFactory(new PropertyValueFactory<>("picnic_reservation"));
        payment_type_reservation.setCellValueFactory(new PropertyValueFactory<>("payment_type_reservation"));
        paid_reservation.setCellValueFactory(new PropertyValueFactory<>("paid_reservation"));
        fuel_level_over_half_reservation.setCellValueFactory(new PropertyValueFactory<>("fuel_level_over_half_reservation"));
        mechanic_approval_reservation.setCellValueFactory(new PropertyValueFactory<>("mechanic_approval_reservation"));
        mechanic_comments.setCellValueFactory(new PropertyValueFactory<>("mechanic_comments"));
        tableReservation.setItems(null);
        tableReservation.setItems(dataReservation);

    }


    @FXML
    private void deleteReservation(ActionEvent event){
        Reservation reservation = tableReservation.getSelectionModel().getSelectedItem();
        try{
            Connection con = getConnection();
            con.createStatement().executeUpdate("DELETE FROM reservation WHERE id_reservation =" + reservation.getId_reservation());
            loadReservations();
        }catch(Exception ex){
            System.err.println("Error" + ex);
        }

    }


    @FXML
    private TextField reservation_drop;

    @FXML
    private TextField reservation_vehicle;

    @FXML
    private TextField reservation_payment;

    @FXML
    private TextField reservation_child;

    @FXML
    private TextField reservation_mechanic;

    @FXML
    private TextField reservation_fuel;

    @FXML
    private TextField reservation_comments;

    @FXML
    private Button reservationCreateButton;

    @FXML
    private TextField reservation_picnic;

    @FXML
    private TextField reservation_id;

    @FXML
    private TextField reservation_finish;

    @FXML
    private TextField reservation_bike;

    @FXML
    private TextField reservation_customer_id;

    @FXML
    private TextField reservation_paid;

    @FXML
    private TextField reservation_start;

    @FXML
    private Button reservationSaveButton;

    @FXML
    private TextField reservation_pick;

    @FXML
    private TextField reservation_price;

    @FXML
    private void createReservation(ActionEvent event) throws Exception {
        String id_reservation = reservation_id.getText();
        String customer_reservation = reservation_customer_id.getText();
        String vehicle_reservation = reservation_vehicle.getText();
        String start_reservation = reservation_start.getText();
        String finish_reservation = reservation_finish.getText();
        String pick_reservation = reservation_pick.getText();
        String drop_reservation = reservation_drop.getText();
        String price_reservation = reservation_price.getText();
        String bike_reservation = reservation_bike.getText();
        String picnic_reservation = reservation_picnic.getText();
        String child_reservation = reservation_child.getText();
        String payment_reservation = reservation_payment.getText();
        String paid_reservation = reservation_paid.getText();
        String fuel_reservation = reservation_fuel.getText();
        String mechanic_reservation = reservation_mechanic.getText();
        String comments_reservation = reservation_comments.getText();
        try{
            Connection con = getConnection();
            String sql = "INSERT INTO reservation VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, start_reservation);
            statement.setString(2, finish_reservation);
            statement.setString(3, customer_reservation);
            statement.setString(4, vehicle_reservation);
            statement.setString(5, pick_reservation);
            statement.setString(6, drop_reservation);
            statement.setString(7, price_reservation);
            statement.setString(8, bike_reservation);
            statement.setString(9, child_reservation);
            statement.setString(10, picnic_reservation);
            statement.setString(11, payment_reservation);
            statement.setString(12,paid_reservation);
            statement.setString(13, fuel_reservation);
            statement.setString(14, mechanic_reservation);
            statement.setString(15, comments_reservation);

            statement.executeUpdate();

        }catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        loadReservations();

    }

    @FXML
    private Button editReservationButton;

    @FXML
    public void editReservation() throws Exception {
        Reservation reservation = tableReservation.getSelectionModel().getSelectedItem();
        reservation_id.setText(Integer.toString(reservation.getId_reservation()));
        reservation_customer_id.setText(Integer.toString(reservation.getReservation_customer_id()));
        reservation_vehicle.setText(Integer.toString(reservation.getVehicle_reservation()));
        reservation_start.setText(reservation.getStart_reservation());
        reservation_finish.setText(reservation.getFinish_reservation());
        reservation_pick.setText(reservation.getPickup_reservation());
        reservation_drop.setText(reservation.getDropoff_reservation());
        reservation_price.setText(Integer.toString(reservation.getPrice_reservation()));
        reservation_bike.setText(Boolean.toString(reservation.isBike_reservation()));
        reservation_picnic.setText(Boolean.toString(reservation.isPicnic_reservation()));
        reservation_child.setText(Boolean.toString(reservation.isChild_reservation()));
        reservation_payment.setText(reservation.getPayment_type_reservation());
        reservation_paid.setText(Boolean.toString(reservation.isPaid_reservation()));
        reservation_fuel.setText(Boolean.toString(reservation.isFuel_level_over_half_reservation()));
        reservation_mechanic.setText(Boolean.toString(reservation.isMechanic_approval_reservation()));
        reservation_comments.setText(reservation.getMechanic_comments());

    }

    @FXML
    private void saveReservation(ActionEvent event) throws Exception {
            String id_reservation = reservation_id.getText();
            String customer_reservation = reservation_customer_id.getText();
            String vehicle_reservation = reservation_vehicle.getText();
            String start_reservation = reservation_start.getText();
            String finish_reservation = reservation_finish.getText();
            String pick_reservation = reservation_pick.getText();
            String drop_reservation = reservation_drop.getText();
            String price_reservation = reservation_price.getText();
            String bike_reservation = reservation_bike.getText();
            String picnic_reservation = reservation_picnic.getText();
            String child_reservation = reservation_child.getText();
            String payment_reservation = reservation_payment.getText();
            String paid_reservation = reservation_paid.getText();
            String fuel_reservation = reservation_fuel.getText();
            String mechanic_reservation = reservation_mechanic.getText();
            String comments_reservation = reservation_comments.getText();
            try{
                Connection con = getConnection();
                String sql = ("UPDATE reservation SET start_reservation = ?, finish_reservation = ?, reservation_customer_id = ?, vehicle_reservation = ?," +
                        "pickup_reservation = ?, dropoff_reservation = ?, price_reservation = ?, bike_reservation = ?, child_reservation = ?, picnic_reservation = ?, payment_type_reservation = ?," +
                        "paid_reservation = ?, fuel_level_over_half_reservation = ?, mechanic_approval_reservation = ?, mechanic_comments = ? WHERE id_reservation = ?");

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, start_reservation);
                ps.setString(2, finish_reservation);
                ps.setString(3, customer_reservation);
                ps.setString(4, vehicle_reservation);
                ps.setString(5, pick_reservation);
                ps.setString(6, drop_reservation);
                ps.setString(7, price_reservation);
                ps.setString(8, bike_reservation);
                ps.setString(9, child_reservation);
                ps.setString(10, picnic_reservation);
                ps.setString(11, payment_reservation);
                ps.setString(12, paid_reservation);
                ps.setString(13, fuel_reservation);
                ps.setString(14, mechanic_reservation);
                ps.setString(15, comments_reservation);
                ps.setString(16, id_reservation);
                ps.executeUpdate();

                loadReservations();

            }catch (SQLException ex) {
                System.err.println("Error" + ex);
            }

        }

}
