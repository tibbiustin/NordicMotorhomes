package sample;

import javafx.beans.property.*;

/**
 * Created by George Stratulat on 17/05/2017.
 */
public class Vehicle {
    private final IntegerProperty id_vehicle;
    private final IntegerProperty type_vehicle;
    private final StringProperty license_vehicle;
    private final BooleanProperty booked_vehicle;
    private final IntegerProperty price_vehicle;

    public Vehicle(int id_vehicle, int type_vehicle, String license_vehicle, boolean booked_vehicle, int price_vehicle){
        this.id_vehicle = new SimpleIntegerProperty(id_vehicle);
        this.type_vehicle = new SimpleIntegerProperty(type_vehicle);
        this.license_vehicle = new SimpleStringProperty(license_vehicle);
        this.booked_vehicle = new SimpleBooleanProperty(booked_vehicle);
        this.price_vehicle = new SimpleIntegerProperty(price_vehicle);
    }

    public int getId_vehicle() {
        return id_vehicle.get();
    }

    public IntegerProperty id_vehicleProperty() {
        return id_vehicle;
    }

    public void setId_vehicle(int id_vehicle) {
        this.id_vehicle.set(id_vehicle);
    }

    public int getType_vehicle() {
        return type_vehicle.get();
    }

    public IntegerProperty type_vehicleProperty() {
        return type_vehicle;
    }

    public void setType_vehicle(int type_vehicle) {
        this.type_vehicle.set(type_vehicle);
    }

    public String getLicense_vehicle() {
        return license_vehicle.get();
    }

    public StringProperty license_vehicleProperty() {
        return license_vehicle;
    }

    public void setLicense_vehicle(String license_vehicle) {
        this.license_vehicle.set(license_vehicle);
    }

    public boolean isBooked_vehicle() {
        return booked_vehicle.get();
    }

    public BooleanProperty booked_vehicleProperty() {
        return booked_vehicle;
    }

    public void setBooked_vehicle(boolean booked_vehicle) {
        this.booked_vehicle.set(booked_vehicle);
    }

    public int getPrice_vehicle() {
        return price_vehicle.get();
    }

    public IntegerProperty price_vehicleProperty() {
        return price_vehicle;
    }

    public void setPrice_vehicle(int price_vehicle) {
        this.price_vehicle.set(price_vehicle);
    }
}
