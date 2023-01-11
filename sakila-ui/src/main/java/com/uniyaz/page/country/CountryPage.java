package com.uniyaz.page.country;

import com.uniyaz.country.domain.Country;
import com.uniyaz.country.queryfilterdto.CountryQueryFilterDto;
import com.uniyaz.country.service.CountryService;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.Date;
import java.util.List;

public class CountryPage extends VerticalLayout{

    private HorizontalLayout buttonLayout;
    private FormLayout filterFormLayout;
    private VerticalLayout tableLayout;
    private FormLayout formLayout;

    private TextField idFiltre;
    private TextField countryFilter;

    private TextField id;
    private TextField countryField;

    private Table table;

    private Button saveButton;
    private Button deleteButton;
    private Button searchButton;

    public CountryPage() {

        setMargin(true);
        setSpacing(true);

        buildFilterFormLayout();
        addComponent(filterFormLayout);

        buildTableLayout();
        addComponent(tableLayout);

        buildFormLayout();
        addComponent(formLayout);

        buildButtonLayout();
        addComponent(buttonLayout);

        CountryService countryService = new CountryService();
        List<Country> countryList = countryService.findAll();
        fillTable(countryList);
    }

    private void buildFilterFormLayout() {
        filterFormLayout = new FormLayout();

        idFiltre = new TextField();
        idFiltre.setCaption("Id");
        filterFormLayout.addComponent(idFiltre);

        countryFilter = new TextField();
        countryFilter.setCaption("City");
        filterFormLayout.addComponent(countryFilter);

        buildSearchButton();
        filterFormLayout.addComponent(searchButton);
    }

    private void buildSearchButton() {

        searchButton = new Button();
        searchButton.setCaption("Search");
        searchButton.setIcon(FontAwesome.SEARCH);
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                CountryQueryFilterDto countryQueryFilterDto = new CountryQueryFilterDto();
                if (!idFiltre.getValue().equals("")) countryQueryFilterDto.setId(new Long(idFiltre.getValue()));
                if (!countryFilter.getValue().equals("")) countryQueryFilterDto.setCountry(countryFilter.getValue());

                CountryService countryService = new CountryService();
                List<Country> countryList = countryService.findAllByQueryFilterDto(countryQueryFilterDto);
                fillTable(countryList);
            }
        });
    }

    private void buildTableLayout() {
        tableLayout = new VerticalLayout();

        buildTable();
        tableLayout.addComponent(table);
    }

    private void buildTable() {
        table = new Table();
        table.setSelectable(true);

        table.addContainerProperty("id", Long.class, null);
        table.addContainerProperty("country", String.class, null);
        table.addContainerProperty("lastUpdate", Date.class, null);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Country country = (Country) itemClickEvent.getItemId();
                fillFormLayoutByCountry(country);
            }
        });
    }

    private void fillFormLayoutByCountry(Country country) {

        id.setValue(country.getId().toString());
        countryField.setValue(country.getCountry());
    }

    private void buildFormLayout() {

        formLayout = new FormLayout();

        id = new TextField();
        id.setCaption("Id");
        formLayout.addComponent(id);

        countryField = new TextField();
        countryField.setCaption("City");
        formLayout.addComponent(countryField);
    }

    private void buildButtonLayout() {

        buttonLayout = new HorizontalLayout();

        buildSaveButton();
        buttonLayout.addComponent(saveButton);

        buildDeleteButton();
        buttonLayout.addComponent(deleteButton);
    }

    private void buildSaveButton() {
        saveButton = new Button();
        saveButton.setCaption("Save");
        saveButton.setIcon(FontAwesome.SAVE);
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                Country country = new Country();

                if (!id.getValue().equals("")) {
                    country.setId(new Long(id.getValue()));
                }
                country.setCountry(countryField.getValue());
                country.setLastUpdate(new Date());

                CountryService countryService = new CountryService();
                countryService.save(country);

                List<Country> countryList = countryService.findAll();
                fillTable(countryList);
            }
        });
    }

    private void buildDeleteButton() {

        deleteButton = new Button();
        deleteButton.setCaption("Delete");
        deleteButton.setIcon(FontAwesome.TRASH);
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Country country = new Country();
                country.setId(new Long(id.getValue()));

                CountryService countryService = new CountryService();
                countryService.delete(country);

                List<Country> countryList = countryService.findAll();
                fillTable(countryList);
            }
        });
    }

    private void fillTable(List<Country> countryList) {

        table.removeAllItems();
        for (Country country : countryList) {
            Item item = table.addItem(country);
            item.getItemProperty("id").setValue(country.getId());
            item.getItemProperty("country").setValue(country.getCountry());
            item.getItemProperty("lastUpdate").setValue(country.getLastUpdate());
        }
    }
}