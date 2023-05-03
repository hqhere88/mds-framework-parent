package org.vaadin.example.views.navigation;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "threadID/:threadID", layout = ForumView.class)
@RouteAlias(value = "threadID/:threadID/comment", layout = ForumView.class)
public class ForumThreadView extends Div implements BeforeEnterObserver {

    private String threadID;

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        threadID = beforeEnterEvent.getRouteParameters().get("threadID").get();

        setText("ForumThreadView-threadID:"+threadID);
        /**
         if ("comment".equals(getLastSegment(beforeEnterEvent))) {
         new CommentDialog().open();
         }
         */
    }
}