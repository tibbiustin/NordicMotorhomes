package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by George Stratulat on 17/05/2017.
 */
public class Manager {
    private final IntegerProperty id_admin;
    private final StringProperty username_admin;
    private final StringProperty password_admin;

    public Manager(int id_admin, String username_admin, String password_admin){
        this.id_admin = new SimpleIntegerProperty(id_admin);
        this.username_admin = new SimpleStringProperty(username_admin);
        this.password_admin = new SimpleStringProperty(password_admin);
    }

    public int getId_admin() {
        return id_admin.get();
    }

    public IntegerProperty id_adminProperty() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin.set(id_admin);
    }

    public String getUsername_admin() {
        return username_admin.get();
    }

    public StringProperty username_adminProperty() {
        return username_admin;
    }

    public void setUsername_admin(String username_admin) {
        this.username_admin.set(username_admin);
    }

    public String getPassword_admin() {
        return password_admin.get();
    }

    public StringProperty password_adminProperty() {
        return password_admin;
    }

    public void setPassword_admin(String password_admin) {
        this.password_admin.set(password_admin);
    }
}
