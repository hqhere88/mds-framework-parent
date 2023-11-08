package mds.framework.example.views.navigation;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;

@Route("greet")
public class OptionalGreeting extends Div
        implements HasUrlParameter<String> {

    //greet and greet/<anything>
    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
           if (parameter == null) {
            setText("Welcome anonymous.");
        } else {
            setText(String.format("Welcome %s.",
                    parameter));
        }
    }

  /**
   * //greet/one/five/three
    @Override
    public void setParameter(BeforeEvent event,
                             @WildcardParameter String parameter) {
        if (parameter.isEmpty()) {
            setText("Welcome anonymous.");
        } else {
            setText(String.format(
                    "Handling parameter %s.",
                    parameter));
        }
    }
 */

    /**
     * book/search?keyword=Vaadin
     * https://example.com/?genre=fiction&restrictions=16+&genre=classic
     * {"genre" : ["fiction", "classic"], "restrictions": ["16+"]}}
    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {

        Location location = event.getLocation();
        QueryParameters queryParameters = location
                .getQueryParameters();

        Map<String, List<String>> parametersMap =
                queryParameters.getParameters();
    }
      */
}
