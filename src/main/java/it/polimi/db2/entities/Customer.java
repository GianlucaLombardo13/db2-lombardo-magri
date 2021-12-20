package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(
        name = "customer",
        schema = "telco"
)
@NamedQuery(
        name = "Customer.checkCredentials",
        query = "SELECT c FROM Customer AS c WHERE c.username=?1 AND c.password=?2")
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    private String email;
    private String password;
    private boolean insolvent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Order> orders;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<ServiceActivationSchedule> serviceActivationSchedules;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "customer")
    private Alert alert;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInsolvent() {
        return insolvent;
    }

    public void setInsolvent(boolean insolvent) {
        this.insolvent = insolvent;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<ServiceActivationSchedule> getServiceActivationSchedules() {
        return serviceActivationSchedules;
    }

    public void setServiceActivationSchedules(List<ServiceActivationSchedule> serviceActivationSchedules) {
        this.serviceActivationSchedules = serviceActivationSchedules;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }
}
