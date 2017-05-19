package sample;

import javafx.beans.property.*;

/**
 * Created by George Stratulat on 17/05/2017.
 */
public class Customer {
    private final IntegerProperty id_customer;
    private final StringProperty name_customer;
    private final StringProperty email_customer;
    private final StringProperty password_customer;
    private final LongProperty cpr_customer;

    public Customer(int id_customer, String name_customer, String email_customer, String password_customer, long cpr_customer){
        this.id_customer = new SimpleIntegerProperty(id_customer);
        this.name_customer = new SimpleStringProperty(name_customer);
        this.cpr_customer = new SimpleLongProperty(cpr_customer);
        this.email_customer = new SimpleStringProperty(email_customer);
        this.password_customer = new SimpleStringProperty(password_customer);
    }

    public int getId_customer() {
        return id_customer.get();
    }

    public IntegerProperty id_customerProperty() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer.set(id_customer);
    }

    public String getName_customer() {
        return name_customer.get();
    }

    public StringProperty name_customerProperty() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer.set(name_customer);
    }

    public String getEmail_customer() {
        return email_customer.get();
    }

    public StringProperty email_customerProperty() {
        return email_customer;
    }

    public void setEmail_customer(String email_customer) {
        this.email_customer.set(email_customer);
    }

    public String getPassword_customer() {
        return password_customer.get();
    }

    public StringProperty password_customerProperty() {
        return password_customer;
    }

    public void setPassword_customer(String password_customer) {
        this.password_customer.set(password_customer);
    }

    public long getCpr_customer() {
        return cpr_customer.get();
    }

    public LongProperty cpr_customerProperty() {
        return cpr_customer;
    }

    public void setCpr_customer(long cpr_customer) {
        this.cpr_customer.set(cpr_customer);
    }
}
