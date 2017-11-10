package com.homework.webshopregapp.jpa.session;

import com.homework.webshopregapp.jpa.entities.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AddressFacade extends AbstractFacade<Address> {
    @PersistenceContext(unitName = "WebshopRegApp_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }
    
}
