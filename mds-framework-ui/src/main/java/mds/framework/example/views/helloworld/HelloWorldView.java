package mds.framework.example.views.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import mds.framework.example.views.MainLayout;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@PageTitle("Hello World")
@Route(value = "hello-world", layout = MainLayout.class)
public class HelloWorldView extends VerticalLayout {

    private final Tab details;
    private final Tab payment;
    private final Tab shipping;
    private final VerticalLayout content;

    public HelloWorldView() {
        this.details = new Tab("Details");
        this.payment = new Tab("Payment");
        this.shipping = new Tab("Shipping");

        Tabs tabs = new Tabs(this.details, this.payment, this.shipping);
        tabs.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab())
        );

        content = new VerticalLayout();
        content.setSpacing(false);
        setContent(tabs.getSelectedTab());
        //setVerticalComponentAlignment(Alignment.END, name, sayHello);
        add(tabs, content);

        /**
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(this.details);
         */
    }

    private void setContent(Tab tab) {
        content.removeAll();

        if (tab.equals(details)) {

            TextField name = new TextField("Your name");
            Button sayHello = new Button("Say hello");
            sayHello.addClickListener(e -> {
                Notification.show("Hello " + name.getValue());
            });

            HorizontalLayout tabDetails = new HorizontalLayout();
            tabDetails.setMargin(true);
            tabDetails.setVerticalComponentAlignment(Alignment.END, name, sayHello);
            tabDetails.add(name, sayHello);
            content.add(tabDetails);
            //content.add(new Paragraph("This is the Details tab"));
        } else if (tab.equals(payment)) {
            content.add(new Paragraph("This is the Payment tab"));
            MessageList list = new MessageList();
            Instant yesterday = LocalDateTime.now().minusDays(1)
                    .toInstant(ZoneOffset.UTC);
            Instant fiftyMinsAgo = LocalDateTime.now().minusMinutes(50)
                    .toInstant(ZoneOffset.UTC);
            MessageListItem message1 = new MessageListItem(
                    "Linsey, could you check if the details with the order are okay?",
                    yesterday, "Matt Mambo");
            message1.setUserColorIndex(1);
            MessageListItem message2 = new MessageListItem("All good. Ship it.",
                    fiftyMinsAgo, "Linsey Listy", "images/logo.png");
            message2.setUserColorIndex(2);
            list.setItems(Arrays.asList(message1, message2));
            content.add(list);
        } else {
            content.add(new Paragraph("This is the Shipping tab"));
        }
    }

}
