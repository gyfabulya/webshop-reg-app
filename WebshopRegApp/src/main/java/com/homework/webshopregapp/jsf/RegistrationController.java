package com.homework.webshopregapp.jsf;

import com.homework.webshopregapp.jpa.entities.Address;
import com.homework.webshopregapp.jpa.entities.AddressType;
import com.homework.webshopregapp.jpa.entities.Customer;
import com.homework.webshopregapp.jpa.session.AddressFacade;
import com.homework.webshopregapp.jpa.session.CustomerFacade;
import com.homework.webshopregapp.jpa.session.LocationService;
import com.homework.webshopregapp.jsf.util.JsfUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@Named(value = "registrationController")
@RequestScoped
public class RegistrationController implements Serializable {
    
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private List<String> countries;
    
    @EJB
    private CustomerFacade ejbCustomerFacade; 

    @EJB
    private AddressFacade ejbAddressFacade;    
    
    @EJB
    private LocationService ejbLocationService;
    
    
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
    
    public LocationService getEjbLocationService() {
        return ejbLocationService;
    }
        
    public List<String> getCountries() {
        return countries;
    }
    
    @PostConstruct
    public void init() {
        countries = ejbLocationService.getCountries();
    }     
    
    public String prepareCreate() {
        customer = new Customer();
        return "Create";
    }    
    
    public String registration() {
        try {      
            
            getCustomerFacade().create(customer);
            if (customer.getCustomerId() != null) {                
                //create Billing address of Customer
                billingAddress.setCustomerId(customer);
                getAddressFacade().create(billingAddress);
                
                //create Shipping address of Customer              
                shippingAddress.setCustomerId(customer);                
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
