package org.vaadin.example.views.navigation;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("no-items")
public class NoItemsView extends Div {
    public NoItemsView() {
        setText("No items found.");
    }
}
