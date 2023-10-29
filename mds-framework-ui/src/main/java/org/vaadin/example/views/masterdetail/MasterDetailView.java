package org.vaadin.example.views.masterdetail;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import mds.framework.entity.Person;
import mds.framework.entity.SamplePerson;
import mds.framework.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.views.MainLayout;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PageTitle("Master Detail")
@Route(value = "master-detail/:samplePersonID?/:action?(edit)", layout = MainLayout.class)
@Uses(Icon.class)
public class MasterDetailView extends Div implements BeforeEnterObserver {

    private final String SAMPLEPERSON_ID = "personID";
    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "master-detail/%s/edit";

    private Grid<Person> grid = new Grid<>(Person.class, false);

    private TextField firstName;
    private TextField lastName;
    private TextField email;
  //  private TextField phone;
   // private DatePicker dateOfBirth;
  //  private TextField occupation;
  //  private Checkbox important;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Person> binder;

    private Person person;

   // private SamplePersonService samplePersonService;
    @Resource(name="dataService")
    // @Resource(type = DataServiceImpl.class)
    private final IDataService dataService;


    public MasterDetailView(@Autowired IDataService dataService) {
        this.dataService = dataService;
        addClassNames("master-detail-view", "flex", "flex-col", "h-full");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("firstName").setAutoWidth(true);
        grid.addColumn("lastName").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
       // grid.addColumn("phone").setAutoWidth(true);
       // grid.addColumn("managerId").setAutoWidth(true);
       // grid.addColumn("occupation").setAutoWidth(true);
      //  TemplateRenderer<Person> importantRenderer = TemplateRenderer.<Person>of(
      //          "<iron-icon hidden='[[!item.important]]' icon='vaadin:check' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: var(--lumo-primary-text-color);'></iron-icon><iron-icon hidden='[[item.important]]' icon='vaadin:minus' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: var(--lumo-disabled-text-color);'></iron-icon>")
      //          .withProperty("important", Person::isImportant);
      //  grid.addColumn(String.valueOf(importantRenderer)).setHeader("Important").setAutoWidth(true);

       // grid.setDataProvider(new GenericCrudServiceDataProvider<>(samplePersonService));

        List<Person> people =  this.dataService.getPeople();
        grid.setItems(people);

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(MasterDetailView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Person.class);

        // Bind fields. This is where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.person == null) {
                    this.person = new Person();
                }
                binder.writeBean(this.person);

              //  samplePersonService.update(this.samplePerson);
                clearForm();
                refreshGrid();
                Notification.show("Person details stored.");
                UI.getCurrent().navigate(MasterDetailView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the samplePerson details.");
            }
        });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
       // Optional<UUID> personId = event.getRouteParameters().get(SAMPLEPERSON_ID).map(UUID::fromString);
        Optional<String> personId = event.getRouteParameters().get(SAMPLEPERSON_ID);

        if (personId.isPresent()) {
            Optional<Person> personFromBackend = dataService.getPeopleById(Integer.parseInt(personId.get())).stream().findFirst();
            if (personFromBackend.isPresent()) {
                populateForm(personFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested person was not found, ID = %s", personId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(MasterDetailView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        firstName = new TextField("First Name");
        lastName = new TextField("Last Name");
        email = new TextField("Email");
      //  phone = new TextField("Phone");
      //  birthday = new DatePicker("birthday");
      //  membership = new TextField("membership");
      //  important = new Checkbox("Important");
     //   important.getStyle().set("padding-top", "var(--lumo-space-m)");
        Component[] fields = new Component[]{firstName, lastName, email};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Person value) {
        this.person = value;
        binder.readBean(this.person);

    }
}
