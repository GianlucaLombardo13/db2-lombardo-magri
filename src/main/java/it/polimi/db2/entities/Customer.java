package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer",schema = "telco")
@NamedQuery(name = "Customer.checkCredentials", query = "SELECT c FROM Customer AS c WHERE c.username=? AND c.password=?")
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
}
