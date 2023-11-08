package mds.framework.example.views.navigation;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "threadID/:threadID", layout = mds.framework.example.views.navigation.ForumView.class)
@RouteAlias(value = "threadID/:threadID/comment", layout = mds.framework.example.views.navigation.ForumView.class)
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