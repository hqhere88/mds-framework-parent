package mds.framework.example.views.navigation;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("user/:userID/edit")
//@Route("user/:userID?([0-9]{1,9})/edit")
//@Route(user/to/:param1?([0-9]*)/:param2?([a-z]*)")
public class UserProfileEdit extends Div implements BeforeEnterObserver {

    private String userID;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        userID = event.getRouteParameters().get("userID").get();
        setText("UserProfileEdit-userID:"+userID);
    }
}