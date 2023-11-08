package mds.framework.example.views.navigation;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("blog")
public class BlogList extends Div
        implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // implementation omitted
        Object record = getItem();

        if (record == null) {
            event.rerouteTo(mds.framework.example.views.navigation.NoItemsView.class);
        }
    }

    private Object getItem() {
        // no-op implementation
        return null;
    }
}