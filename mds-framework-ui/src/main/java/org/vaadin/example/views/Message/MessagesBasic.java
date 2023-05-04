package org.vaadin.example.views.Message;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.example.views.MainLayout;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Messages-basic")
@Route(value = "messages-basic", layout = MainLayout.class)
public class MessagesBasic extends VerticalLayout {

    public MessagesBasic() {
        // tag::snippet[]
        MessageList list = new MessageList();
        MessageInput input = new MessageInput();
        input.addSubmitListener(submitEvent -> {
            MessageListItem newMessage = new MessageListItem(
                    submitEvent.getValue(), Instant.now(), "Milla Sting");
            newMessage.setUserColorIndex(3);
            List<MessageListItem> items = new ArrayList<>(list.getItems());
            items.add(newMessage);
            list.setItems(items);
        });

        MessageListItem message1 = new MessageListItem(
                "Nature does not hurry, yet everything gets accomplished.",
                LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC),
                "Matt Mambo");
        message1.setUserColorIndex(1);
        MessageListItem message2 = new MessageListItem(
                "Using your talent, hobby or profession in a way that makes you contribute with something good to this world is truly the way to go.",
                LocalDateTime.now().minusMinutes(55).toInstant(ZoneOffset.UTC),
                "Linsey Listy", "images/logo.png");
        message2.setUserColorIndex(2);
        list.setItems(message1, message2);

        VerticalLayout chatLayout = new VerticalLayout(list, input);
        chatLayout.setHeight("400px");
        chatLayout.setWidth("700px");
        chatLayout.expand(list);
        add(chatLayout);
        // end::snippet[]
    }

}

