package com.homework.webshopregapp.jpa.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;

@Stateless
public class LocationService {
    
    public List<String> getCountries() {
        
        List<String> countryList = new ArrayList<String>();        
        String[] countries = Locale.getISOCountries();     
        
        for(int i = 0; i < countries.length; i++) {              
            String country = countries[i];
            Locale locale = new Locale("hu", country);          
            countryList.add(locale.getDisplayCountry());
        }  
        
        return countryList;
    }     
}
