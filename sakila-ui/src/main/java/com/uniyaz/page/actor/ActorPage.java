package com.uniyaz.page.actor;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.queryfilterdto.ActorCriteriaFilterDto;
import com.uniyaz.actor.service.ActorService;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.Date;
import java.util.List;

public class ActorPage extends VerticalLayout{

    private HorizontalLayout buttonLayout;
    private FormLayout filterFormLayout;
    private VerticalLayout tableLayout;
    private FormLayout formLayout;

    private TextField idFiltre;
    private TextField firstNameFilter;

    private TextField id;
    private TextField firstName;
    private TextField lastName;

    private Table table;

    private Button saveButton;
    private Button deleteButton;
    private Button searchButton;

    public ActorPage() {

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

        ActorService actorService = new ActorService();
        List<Actor> actorList = actorService.findAll();
        fillTable(actorList);
    }

    private void buildFilterFormLayout() {
        filterFormLayout = new FormLayout();

        idFiltre = new TextField();
        idFiltre.setCaption("Id");
        filterFormLayout.addComponent(idFiltre);

        firstNameFilter = new TextField();
        firstNameFilter.setCaption("First Name");
        filterFormLayout.addComponent(firstNameFilter);

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

                ActorCriteriaFilterDto actorCriteriaFilterDto = new ActorCriteriaFilterDto();
                if (!idFiltre.getValue().equals("")) actorCriteriaFilterDto.setId(new Long(idFiltre.getValue()));
                if (!firstNameFilter.getValue().equals("")) actorCriteriaFilterDto.setFirstName(firstNameFilter.getValue());

                ActorService actorService = new ActorService();
                List<Actor> actorList = actorService.findAllByQueryFilterDto(actorCriteriaFilterDto);
                fillTable(actorList);
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

        table.addContainerProperty("firstName", String.class, null);
        table.addContainerProperty("lastName", String.class, null);
        table.addContainerProperty("lastUpdate", Date.class, null);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Actor actor = (Actor) itemClickEvent.getItemId();
                fillFormLayoutByActor(actor);
            }
        });
    }

    private void fillFormLayoutByActor(Actor actor) {

        id.setValue(actor.getId().toString());
        firstName.setValue(actor.getFirstName());
        lastName.setValue(actor.getLastName());
    }

    private void buildFormLayout() {

        formLayout = new FormLayout();

        id = new TextField();
        id.setCaption("Id");
        formLayout.addComponent(id);

        firstName = new TextField();
        firstName.setCaption("Fİrst Name");
        formLayout.addComponent(firstName);

        lastName = new TextField();
        lastName.setCaption("Last Name");
        formLayout.addComponent(lastName);
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

                Actor actor = new Actor();

                if (!id.getValue().equals("")) {
                    actor.setId(new Long(id.getValue()));
                }
                actor.setFirstName(firstName.getValue());
                actor.setLastName(lastName.getValue());
                actor.setLastUpdate(new Date());

                ActorService actorService = new ActorService();
                actorService.save(actor);

                List<Actor> actorList = actorService.findAll();
                fillTable(actorList);
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
                Actor actor = new Actor();
                actor.setId(new Long(id.getValue()));

                ActorService actorService = new ActorService();
                actorService.delete(actor);

                List<Actor> actorList = actorService.findAll();
                fillTable(actorList);
            }
        });
    }

    private void fillTable(List<Actor> actorList) {

        table.removeAllItems();
        for (Actor actor : actorList) {
            Item item = table.addItem(actor);
            item.getItemProperty("firstName").setValue(actor.getFirstName());
            item.getItemProperty("lastName").setValue(actor.getLastName());
            item.getItemProperty("lastUpdate").setValue(actor.getLastUpdate());
        }
    }
}