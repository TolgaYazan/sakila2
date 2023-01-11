package com.uniyaz;

import com.uniyaz.page.actor.ActorPage;
import com.uniyaz.page.city.CityPage;
import com.uniyaz.page.country.CountryPage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class MyUI extends UI {

    private HorizontalSplitPanel horizontalSplitPanel;
    private VerticalLayout buttonLayout;
    private VerticalLayout contentLayout;
    private Button actorButton;
    private Button countryButton;
    private Button cityButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        buildHorizontalSplitPanel();
        setContent(horizontalSplitPanel);
    }

    private void buildHorizontalSplitPanel() {
        horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setSplitPosition(10, Unit.PERCENTAGE);
        
        buildButtonLayout();
        horizontalSplitPanel.addComponent(buttonLayout);
        
        buildContentLayout();
        horizontalSplitPanel.addComponent(contentLayout);
    }

    private void buildButtonLayout() {
        buttonLayout = new VerticalLayout();
        
        buildActorButton();
        buttonLayout.addComponent(actorButton);

        buildCountryButton();
        buttonLayout.addComponent(countryButton);

        buildCityButton();
        buttonLayout.addComponent(cityButton);
    }

    private void buildActorButton() {
        actorButton = new Button();
        actorButton.setWidth(100, Unit.PIXELS);
        actorButton.setCaption("Aktör");
        actorButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                ActorPage actorPage = new ActorPage();
                contentLayout.removeAllComponents();
                contentLayout.addComponent(actorPage);
            }
        });
    }

    private void buildCountryButton() {
        countryButton = new Button();
        countryButton.setWidth(100, Unit.PIXELS);
        countryButton.setCaption("Country");
        countryButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                CountryPage countryPage = new CountryPage();
                contentLayout.removeAllComponents();
                contentLayout.addComponent(countryPage);
            }
        });
    }

    private void buildCityButton() {
        cityButton = new Button();
        cityButton.setWidth(100, Unit.PIXELS);
        cityButton.setCaption("City");
        cityButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                CityPage cityPage = new CityPage();
                contentLayout.removeAllComponents();
                contentLayout.addComponent(cityPage);
            }
        });
    }

    private void buildContentLayout() {

        contentLayout = new VerticalLayout();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
