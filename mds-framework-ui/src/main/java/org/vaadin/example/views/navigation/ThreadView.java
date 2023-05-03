package org.vaadin.example.views.navigation;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;

@Route(":something?")
@RouteAlias(":messageID(" + RouteParameterRegex.INTEGER + ")")
@RouteAlias("last")
@RoutePrefix("thread")
public class ThreadView extends Div implements BeforeEnterObserver {

    private Integer messageID;

    private String something;

    private boolean last;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
      //  last = "last".equals(getLastSegment(event));
        last = "last".equals("last");

        messageID = null;
        something = null;

        if (!last) {
            final RouteParameters urlParameters = event.getRouteParameters();

            urlParameters.getInteger("messageID")
                    .ifPresent(value -> messageID = value);
            urlParameters.get("something")
                    .ifPresent(value -> something = value);
        }
    }

}