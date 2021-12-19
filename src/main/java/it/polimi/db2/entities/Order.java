package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order", schema = "telco")
public class Order implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    private int id;

    private String creationTimestamp;
    private Date subscriptionStartDate;
    private float value;
    private boolean valid;

    @ManyToOne(fetch= FetchType.EAGER)
    private Customer customer;
    @ManyToOne(fetch=FetchType.EAGER)
    private ServicePackage servicePackage;
    @ManyToOne(fetch=FetchType.EAGER)
    private ValidityPeriod validityPeriodId;
}
