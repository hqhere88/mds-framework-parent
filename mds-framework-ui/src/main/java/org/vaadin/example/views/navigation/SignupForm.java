package org.vaadin.example.views.navigation;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Route;

@Route("signup-form")
public class SignupForm extends Div
        implements BeforeLeaveObserver {
    @Override
    public void beforeLeave(BeforeLeaveEvent event) {
        if (this.hasChanges()) {
            BeforeLeaveEvent.ContinueNavigationAction action =
                    event.postpone();
           /** ConfirmDialog.build("Are you sure you want"+
                          " to leave this page?")
                   .ifAccept(action::proceed)
                   .show();
            */
        }
    }

    private boolean hasChanges() {
        // no-op implementation
        return true;
    }
}
