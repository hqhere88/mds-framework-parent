package org.vaadin.example.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.*;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import mds.framework.entity.Person;
import mds.framework.service.IDataService;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

//@PWA(name = "Flow CRM Tutorial", shortName = "Flow CRM Tutorial", enableInstallPrompt = false)
@Theme(themeFolder = "flowcrmtutorial")
@PageTitle("MyList")
@Route(value = "MyList")
public class MyListView extends VerticalLayout {

    @Resource(name="dataService")
    private final IDataService dataService;

    public MyListView(IDataService dataService) {
        this.dataService = dataService;
        setSizeFull();
        HorizontalLayout layout_01 = new HorizontalLayout();
        Icon icon = VaadinIcon.VAADIN_H.create();
        Button btn01 = new Button("Vaadin", icon);

        Icon icon01 = new Icon("lumo", "clock");
        Button btn02 = new Button("Clock", icon01);

        MemoryBuffer memoryBuffer = new MemoryBuffer();

        Upload upload = new Upload(memoryBuffer);
        upload.addFinishedListener(e -> {
            InputStream inputStream = memoryBuffer.getInputStream();
            // read the contents of the buffered memory
            // from inputStream
        });

        MultiFileMemoryBuffer multiFileMemoryBuffer = new MultiFileMemoryBuffer();
        Upload upload01 = new Upload(multiFileMemoryBuffer);
        upload01.addFinishedListener(e -> {
            InputStream inputStream = memoryBuffer.getInputStream();
            // read the contents of the buffered memory
            // from inputStream
        });

        layout_01.add(btn01);
        layout_01.add(btn02);
        layout_01.add(upload);
        layout_01.add(upload01);

        HorizontalLayout layout = new HorizontalLayout();
        // Have some data
        /**
        List<Person> people = Arrays.asList(
                new Person("Nicolaus Copernicus", 1543),
                new Person("Galileo Galilei", 1564),
                new Person("Johannes Kepler", 1571));
        */
        List<Person> people = dataService.getPeople();

// Create a grid bound to the list
        Grid<Person> grid = new Grid<>(Person.class, false);
        grid.addClassNames("Person-grid");
        grid.setSizeFull();
        if (people!=null){
            grid.setItems(people);
        }

        grid.addColumn(person -> person.getFirstName())
                .setHeader("First name")
                .setFlexGrow(0)
                .setWidth("150px")
                .setResizable(false);
        grid.addColumn(person -> person.getLastName())
                .setHeader("Last name")
                .setFlexGrow(0)
                .setWidth("150px")
                .setResizable(false);
        /**
         * grid.addColumns("age", "address.postalCode");
         * grid.addColumn(Person::getName).setHeader("Name");

        // grid.addColumn(Person::getYearOfBirth)
        //        .setHeader("Year of birth");
         */

        grid.addColumn(TemplateRenderer.<Person>of(
                        "<button on-click='handleUpdate'>Update</button>" +
                        "<button on-click='handleRemove'>Remove</button>")
                .withEventHandler("handleUpdate", person -> {
                    person.setFirstName(person.getFirstName() + " Updated");
                    grid.getDataProvider().refreshItem(person);
                }).withEventHandler("handleRemove", person -> {
                    ListDataProvider<Person> dataProvider =
                            (ListDataProvider<Person>) grid
                                    .getDataProvider();
                    dataProvider.getItems().remove(person);
                    dataProvider.refreshAll();
                })).setHeader("Actions");

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        SingleSelect<Grid<Person>, Person> personSelect =
                grid.asSingleSelect();
        // personSelect can now be used with Binder or
        // HasValue interface
        personSelect.addValueChangeListener(e -> {
            Person selectedPerson = e.getValue();
        });


        /**
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        MultiSelect<Grid<Person>, Person> multiSelect =
                grid.asMultiSelect();
        multiSelect.addValueChangeListener(e -> {
            Set<Person> selectedPersons = e.getValue();
        });

         grid.addSelectionListener(event -> {
         Set<Person> selected = event.getAllSelectedItems();
         message.setText(selected.size() + " items selected");
         });

         */

        layout.add(grid);
        layout.addClassNames("Person");
        layout.setSizeFull();
        add(layout_01);
        add(layout);
    }
}
