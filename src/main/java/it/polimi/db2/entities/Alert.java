package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "alert", schema = "telco")
public class Alert implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    private String id;

    private String lastRejectionTimeStamp;
    private float amount;

    @OneToOne(fetch = FetchType.EAGER)
    private Customer customer;
}
