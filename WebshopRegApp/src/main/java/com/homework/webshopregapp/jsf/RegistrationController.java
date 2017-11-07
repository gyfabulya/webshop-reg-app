package com.homework.webshopregapp.jsf;

import com.homework.webshopregapp.jpa.entities.Address;
import com.homework.webshopregapp.jpa.entities.AddressType;
import com.homework.webshopregapp.jpa.entities.Customer;
import com.homework.webshopregapp.jpa.session.AddressFacade;
import com.homework.webshopregapp.jpa.session.CustomerFacade;
import com.homework.webshopregapp.jsf.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;

@Named(value = "registrationController")
@SessionScoped
public class RegistrationController implements Serializable {
    
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    
    @EJB
    private com.homework.webshopregapp.jpa.session.CustomerFacade ejbCustomerFacade; 

    @EJB
    private com.homework.webshopregapp.jpa.session.AddressFacade ejbAddressFacade;    

    
    public RegistrationController() {
    }

    public Customer getCustomer() {
        if (customer == null) {
            customer = new Customer();
        }
        return customer;
    }

    public Address getShippingAddress() {
        if (shippingAddress == null) {
            shippingAddress = new Address();
            shippingAddress.setType(AddressType.SHIPPING);
        }        
        return shippingAddress;
    }

    public Address getBillingAddress() {
        if (billingAddress == null) {
            billingAddress = new Address();
            billingAddress.setType(AddressType.BILLING);
        }               
        return billingAddress;
    }

    private CustomerFacade getCustomerFacade() {
        return ejbCustomerFacade;
    }

    private AddressFacade getAddressFacade() {
        return ejbAddressFacade;
    }

    public String prepareCreate() {
        customer = new Customer();
        return "Create";
    }    
    
    public String registration() {
        try {            
            /*
            List<Address> addressList = new ArrayList<Address>();
            addressList.add(billingAddress);
            
            customer.setAddressCollection(addressList);
            
            getCustomerFacade().create(customer);
            */
            
            getCustomerFacade().create(customer);
            if (customer != null) {
                billingAddress.setCustomerId(customer);
                shippingAddress.setCustomerId(customer);
                getAddressFacade().create(billingAddress);
                getAddressFacade().create(shippingAddress);
            } 
                        
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources//Bundle").getString("CustomerCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources//Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }    
    
}
