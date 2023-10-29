package org.vaadin.example.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.example.SpringUtils;
import org.vaadin.example.views.LoginView;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@SpringComponent
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

	//@Autowired
	//private I18NProvider i18nProvider;

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.getSource().addUIInitListener(uiEvent -> { 
			final UI ui = uiEvent.getUI();
			//ui.addBeforeEnterListener(this::authenticateNavigation);
			System.out.println("ConfigureUIServiceInitListener...");
			// Whenever a new user arrives, determine locale
			initLanguage(uiEvent.getUI());
		});
	}

	private void authenticateNavigation(BeforeEnterEvent event) {
		//if (!LoginView.class.equals(event.getNavigationTarget())
		//    && !SecurityUtils.isUserLoggedIn()) {
		//	event.rerouteTo(LoginView.class);
	//	}
	}

	private void initLanguage(UI ui) {

		//SpringUtils.getBean("I18NProvider");

		System.out.println("ConfigureUIServiceInitListener-->initLanguage...");
		Optional<Cookie> localeCookie = Optional.empty();

		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		if (cookies != null) {
			localeCookie = Arrays.stream(cookies).filter(cookie -> "locale".equals(cookie.getName())).findFirst();
		}

		Locale locale;

		if (localeCookie.isPresent() && !"".equals(localeCookie.get().getValue())) {
			// Cookie found, use that
			locale = Locale.forLanguageTag(localeCookie.get().getValue());
		} else {
			// Try to use Vaadin's browser locale detection
			locale = VaadinService.getCurrentRequest().getLocale();
		}

		// If the detection fails, default to the first language we support.
		if (locale.getLanguage().equals("")) {
			//locale = i18nProvider.getProvidedLocales().get(0);
		}

		ui.setLocale(locale);
	}
}