package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "service_activation_schedule", schema = "telco")
public class ServiceActivationSchedule implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    private int id;

    private Date activationDate;
    private Date deactivationDate;

    @ManyToOne(fetch= FetchType.EAGER)
    private Customer customer;
}
