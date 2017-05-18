package sample;

import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.DatabaseConnection.getConnection;

public class MechanicController {

    public static ObservableList<Reservation> dataReservation;

    @FXML
    private TableView<Reservation> tableReservation;

    @FXML
    private TableColumn<Reservation, String> start_reservation;

    @FXML
    private TextArea comments;

    @FXML
    private TableColumn<Reservation, Boolean> paid_reservation;

    @FXML
    private TableColumn<Reservation, String> pickup_reservation;

    @FXML
    private TableColumn<Reservation, Integer> price_reservation;

    @FXML
    private TableColumn<Reservation, String> payment_type_reservation;

    @FXML
    private TableColumn<Reservation, Integer> id_reservation;

    @FXML
    private TableColumn<Reservation, String> mechanic_comments;

    @FXML
    private TableColumn<Reservation, Integer> vehicle_reservation;

    @FXML
    private Button approveReservationButton;

    @FXML
    private TableColumn<Reservation, String> finish_reservation;

    @FXML
    private TableColumn<Reservation, Boolean> bike_reservation;

    @FXML
    private TableColumn<Reservation, Boolean> fuel_level_over_half_reservation;

    @FXML
    private Button writeCommentButton;

    @FXML
    private TableColumn<Reservation, String> dropoff_reservation;

    @FXML
    private TableColumn<Reservation, Boolean> mechanic_approval_reservation;

    @FXML
    private TableColumn<Reservation, Integer> id_customer_reservation;

    @FXML
    private TableColumn<Reservation, Boolean> child_reservation;

    @FXML
    private TableColumn<Reservation, Boolean> picnic_reservation;

    @FXML
    public void initialize(){
        try {
            loadReservationsMechanic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadReservationsMechanic() throws Exception{
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
    private void writeComment(ActionEvent event) throws Exception {
        Reservation reservation = tableReservation.getSelectionModel().getSelectedItem();
        String comment = comments.getText();
        try{
            Connection con = getConnection();
            con.createStatement().executeUpdate("UPDATE reservation SET mechanic_comments = '"+comment+"' WHERE id_reservation = '"+reservation.getId_reservation()+"'");
        }catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
            comments.setText("");
            loadReservationsMechanic();
    }

    @FXML
    void approveReservation(ActionEvent event) throws Exception {
        Reservation reservation = tableReservation.getSelectionModel().getSelectedItem();
        try{
            Connection con = getConnection();
            con.createStatement().executeUpdate("UPDATE reservation SET mechanic_approval_reservation = 1 WHERE id_reservation = '"+reservation.getId_reservation()+"'");
        }catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        loadReservationsMechanic();

    }

}
