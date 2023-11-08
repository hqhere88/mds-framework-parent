package mds.framework.example.views.navigation;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;

@Route("side-menu")
public class SideMenu extends Div
        implements AfterNavigationObserver {
    Anchor blog = new Anchor("blog", "Blog");

    @Override
    public void afterNavigation(
            AfterNavigationEvent event) {
        boolean active = event.getLocation()
                .getFirstSegment()
                .equals(blog.getHref());
        blog.getElement()
                .getClassList()
                .set("active", active);
    }
}