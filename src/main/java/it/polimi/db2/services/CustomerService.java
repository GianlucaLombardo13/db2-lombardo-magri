package it.polimi.db2.services;

import it.polimi.db2.entities.Customer;
import it.polimi.db2.exception.CredentialsException;
import it.polimi.db2.exception.NotUniqueResultException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class CustomerService {
    @PersistenceContext(unitName = "TelcoEJB")

    private EntityManager entityManager;

    public Customer checkCredentials(String username,String password) throws CredentialsException, NotUniqueResultException {
        List customerList;

        try{
            customerList = this.entityManager.createNamedQuery("Customer.checkCredentials",Customer.class).setParameter(1,username).setParameter(2,password).getResultList();
        }catch (PersistenceException e){
            throw new CredentialsException("Could not verify credentials!");
        }

        if(customerList.isEmpty()){
            return null;
        }else if(customerList.size()==1){
            return (Customer) customerList.get(0);
        }else{
            throw new NotUniqueResultException("More than one user found!");
        }
    }
}
