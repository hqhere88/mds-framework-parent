package mds.framework.example.views.navigation;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;

@Route(value = "")
@RoutePrefix("forum/category/:categoryID")
public class ForumView extends Div implements RouterLayout,
        BeforeEnterObserver {

    private String categoryID;

    private String threadID;

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        final RouteParameters urlParameters = beforeEnterEvent.getRouteParameters();

        threadID = null;

        categoryID = urlParameters.get("categoryID").get();
        setText("ForumView-categoryID:"+categoryID);
        urlParameters.get("threadID").ifPresent(value -> threadID = value);
    }
}

