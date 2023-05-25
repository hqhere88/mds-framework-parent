package org.vaadin.example.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import mds.framework.entity.Person;
import mds.framework.service.DataService;
import org.vaadin.example.views.MainLayout;
import java.util.Collections;
import java.util.List;



@PageTitle("Contacts | Vaadin CRM")
@Route(value = "List", layout = MainLayout.class)
public class ListView extends VerticalLayout {
    Grid<Person> grid = new Grid<>(Person.class);
    TextField filterText = new TextField();
    ContactForm form;

    public ListView() {
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());
        //add(getToolbar(), grid);
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new ContactForm(Collections.emptyList(), Collections.emptyList());
        form.setWidth("25em");
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(contact -> contact.getStatus()).setHeader("Status");
        grid.addColumn(contact -> contact.getAddress().getCountry()).setHeader("Country");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        List<Person> people = DataService.getPeople();
        grid.setItems(people);
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Add contact");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");


        addContactButton.addClickListener(clickEvent ->
                Notification.show("Hello " + filterText.getValue()));

        return toolbar;
    }


}