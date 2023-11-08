package mds.framework.example.views.navigation;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("api/:path*")
//@Route("api/:path*(com|vaadin|flow)")
public class ApiViewer extends Div implements BeforeEnterObserver {

    private String path;
    private List<String> pathSegments;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        path = event.getRouteParameters().get("path").orElse("");
        setText("ApiViewer-path:"+path);
        System.out.println("ApiViewer-path:"+path);

        pathSegments = event.getRouteParameters().getWildcard("path");
        System.out.println("ApiViewer-pathSegments-count:"+pathSegments.stream().count());
        setText("ApiViewer-pathSegments-count:"+pathSegments.stream().count());

        for (String item:pathSegments) {
            System.out.println("ApiViewer-pathSegments:"+item);
            setText("ApiViewer-pathSegments:"+item);
        }
    }
}