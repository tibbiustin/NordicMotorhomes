package sample;

import javafx.beans.property.*;

/**
 * Created by George Stratulat on 17/05/2017.
 */
public class Reservation {
    private final IntegerProperty id_reservation;
    private final StringProperty start_reservation;
    private final StringProperty finish_reservation;
    private final IntegerProperty reservation_customer_id;
    private final IntegerProperty vehicle_reservation;
    private final StringProperty pickup_reservation;
    private final StringProperty dropoff_reservation;
    private final IntegerProperty price_reservation;
    private final BooleanProperty bike_reservation;
    private final BooleanProperty child_reservation;
    private final BooleanProperty picnic_reservation;
    private final StringProperty payment_type_reservation;
    private final BooleanProperty paid_reservation;
    private final BooleanProperty fuel_level_over_half_reservation;
    private final BooleanProperty mechanic_approval_reservation;
    private final StringProperty mechanic_comments;

    public Reservation(int id_reservation, String start_reservation, String finish_reservation, int reservation_customer_id, int vehicle_reservation, String pickup_reservation, String dropoff_reservation, int price_reservation, boolean bike_reservation, boolean child_reservation, boolean picnic_reservation, String payment_type_reservation, boolean paid_reservation, boolean fuel_level_over_half, boolean mechanic_approval_reservation, String mechanic_comments){
        this.id_reservation = new SimpleIntegerProperty(id_reservation);
        this.start_reservation = new SimpleStringProperty(start_reservation);
        this.finish_reservation = new SimpleStringProperty(finish_reservation);
        this.reservation_customer_id = new SimpleIntegerProperty(reservation_customer_id);
        this.vehicle_reservation = new SimpleIntegerProperty(vehicle_reservation);
        this.pickup_reservation = new SimpleStringProperty(pickup_reservation);
        this.dropoff_reservation = new SimpleStringProperty(dropoff_reservation);
        this.price_reservation = new SimpleIntegerProperty(price_reservation);
        this.bike_reservation = new SimpleBooleanProperty(bike_reservation);
        this.child_reservation = new SimpleBooleanProperty(child_reservation);
        this.picnic_reservation = new SimpleBooleanProperty(picnic_reservation);
        this.payment_type_reservation = new SimpleStringProperty(payment_type_reservation);
        this.paid_reservation = new SimpleBooleanProperty(paid_reservation);
        this.fuel_level_over_half_reservation = new SimpleBooleanProperty(fuel_level_over_half);
        this.mechanic_approval_reservation = new SimpleBooleanProperty(mechanic_approval_reservation);
        this.mechanic_comments = new SimpleStringProperty(mechanic_comments);

    }

    public int getId_reservation() {
        return id_reservation.get();
    }

    public IntegerProperty id_reservationProperty() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation.set(id_reservation);
    }

    public String getStart_reservation() {
        return start_reservation.get();
    }

    public StringProperty start_reservationProperty() {
        return start_reservation;
    }

    public void setStart_reservation(String start_reservation) {
        this.start_reservation.set(start_reservation);
    }

    public String getFinish_reservation() {
        return finish_reservation.get();
    }

    public StringProperty finish_reservationProperty() {
        return finish_reservation;
    }

    public void setFinish_reservation(String finish_reservation) {
        this.finish_reservation.set(finish_reservation);
    }

    public int getReservation_customer_id() {
        return reservation_customer_id.get();
    }

    public IntegerProperty reservation_customer_idProperty() {
        return reservation_customer_id;
    }

    public void setReservation_customer_id(int reservation_customer_id) {
        this.reservation_customer_id.set(reservation_customer_id);
    }

    public int getVehicle_reservation() {
        return vehicle_reservation.get();
    }

    public IntegerProperty vehicle_reservationProperty() {
        return vehicle_reservation;
    }

    public void setVehicle_reservation(int vehicle_reservation) {
        this.vehicle_reservation.set(vehicle_reservation);
    }

    public String getPickup_reservation() {
        return pickup_reservation.get();
    }

    public StringProperty pickup_reservationProperty() {
        return pickup_reservation;
    }

    public void setPickup_reservation(String pickup_reservation) {
        this.pickup_reservation.set(pickup_reservation);
    }

    public String getDropoff_reservation() {
        return dropoff_reservation.get();
    }

    public StringProperty dropoff_reservationProperty() {
        return dropoff_reservation;
    }

    public void setDropoff_reservation(String dropoff_reservation) {
        this.dropoff_reservation.set(dropoff_reservation);
    }

    public int getPrice_reservation() {
        return price_reservation.get();
    }

    public IntegerProperty price_reservationProperty() {
        return price_reservation;
    }

    public void setPrice_reservation(int price_reservation) {
        this.price_reservation.set(price_reservation);
    }

    public boolean isBike_reservation() {
        return bike_reservation.get();
    }

    public BooleanProperty bike_reservationProperty() {
        return bike_reservation;
    }

    public void setBike_reservation(boolean bike_reservation) {
        this.bike_reservation.set(bike_reservation);
    }

    public boolean isChild_reservation() {
        return child_reservation.get();
    }

    public BooleanProperty child_reservationProperty() {
        return child_reservation;
    }

    public void setChild_reservation(boolean child_reservation) {
        this.child_reservation.set(child_reservation);
    }

    public boolean isPicnic_reservation() {
        return picnic_reservation.get();
    }

    public BooleanProperty picnic_reservationProperty() {
        return picnic_reservation;
    }

    public void setPicnic_reservation(boolean picnic_reservation) {
        this.picnic_reservation.set(picnic_reservation);
    }

    public String getPayment_type_reservation() {
        return payment_type_reservation.get();
    }

    public StringProperty payment_type_reservationProperty() {
        return payment_type_reservation;
    }

    public void setPayment_type_reservation(String payment_type_reservation) {
        this.payment_type_reservation.set(payment_type_reservation);
    }

    public boolean isPaid_reservation() {
        return paid_reservation.get();
    }

    public BooleanProperty paid_reservationProperty() {
        return paid_reservation;
    }

    public void setPaid_reservation(boolean paid_reservation) {
        this.paid_reservation.set(paid_reservation);
    }

    public boolean isFuel_level_over_half_reservation() {
        return fuel_level_over_half_reservation.get();
    }

    public BooleanProperty fuel_level_over_half_reservationProperty() {
        return fuel_level_over_half_reservation;
    }

    public void setFuel_level_over_half_reservation(boolean fuel_level_over_half_reservation) {
        this.fuel_level_over_half_reservation.set(fuel_level_over_half_reservation);
    }

    public boolean isMechanic_approval_reservation() {
        return mechanic_approval_reservation.get();
    }

    public BooleanProperty mechanic_approval_reservationProperty() {
        return mechanic_approval_reservation;
    }

    public void setMechanic_approval_reservation(boolean mechanic_approval_reservation) {
        this.mechanic_approval_reservation.set(mechanic_approval_reservation);
    }

    public String getMechanic_comments() {
        return mechanic_comments.get();
    }

    public StringProperty mechanic_commentsProperty() {
        return mechanic_comments;
    }

    public void setMechanic_comments(String mechanic_comments) {
        this.mechanic_comments.set(mechanic_comments);
    }
}
