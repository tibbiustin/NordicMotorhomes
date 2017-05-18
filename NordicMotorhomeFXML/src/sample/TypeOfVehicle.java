package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by George Stratulat on 17/05/2017.
 */
public class TypeOfVehicle {
    private final IntegerProperty id_typeOfVehicle;
    private final StringProperty capacity_typeOfVehicle;
    private final StringProperty brand_typeOfVehicle;

    public TypeOfVehicle(int id_typeOfVehicle, String capacity_typeOfVehicle, String brand_typeOfVehicle){
        this.id_typeOfVehicle = new SimpleIntegerProperty(id_typeOfVehicle);
        this.capacity_typeOfVehicle = new SimpleStringProperty(capacity_typeOfVehicle);
        this.brand_typeOfVehicle = new SimpleStringProperty(brand_typeOfVehicle);
    }

    public int getId_typeOfVehicle() {
        return id_typeOfVehicle.get();
    }

    public IntegerProperty id_typeOfVehicleProperty() {
        return id_typeOfVehicle;
    }

    public void setId_typeOfVehicle(int id_typeOfVehicle) {
        this.id_typeOfVehicle.set(id_typeOfVehicle);
    }

    public String getCapacity_typeOfVehicle() {
        return capacity_typeOfVehicle.get();
    }

    public StringProperty capacity_typeOfVehicleProperty() {
        return capacity_typeOfVehicle;
    }

    public void setCapacity_typeOfVehicle(String capacity_typeOfVehicle) {
        this.capacity_typeOfVehicle.set(capacity_typeOfVehicle);
    }

    public String getBrand_typeOfVehicle() {
        return brand_typeOfVehicle.get();
    }

    public StringProperty brand_typeOfVehicleProperty() {
        return brand_typeOfVehicle;
    }

    public void setBrand_typeOfVehicle(String brand_typeOfVehicle) {
        this.brand_typeOfVehicle.set(brand_typeOfVehicle);
    }
}
