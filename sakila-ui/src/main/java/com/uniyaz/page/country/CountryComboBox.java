package com.uniyaz.page.country;

import com.uniyaz.country.domain.Country;
import com.uniyaz.country.service.CountryService;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;

import java.util.List;

public class CountryComboBox extends ComboBox {

    public CountryComboBox() {
        CountryService countryService = new CountryService();
        List<Country> countryList = countryService.findAll();
        for (Country country : countryList) {
            Item item = addItem(country);
            setItemCaption(item, country.getCountry());
        }
    }
}