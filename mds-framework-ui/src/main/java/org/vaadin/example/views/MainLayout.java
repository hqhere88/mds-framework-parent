package org.vaadin.example.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import org.vaadin.example.views.MenuBar.MenuBarView;
import org.vaadin.example.views.Message.MessagesBasic;
import org.vaadin.example.views.empty.EmptyView;
import org.vaadin.example.views.helloworld.HelloWorldView;
import org.vaadin.example.views.list.ListView;
import com.vaadin.flow.component.button.Button;
/**
 * The main view is a top-level placeholder for other views.
 */

//@PWA(name = "Mds framework Examples", shortName = "Mds framework Examples", enableInstallPrompt = false)
//@Push
//@Theme(themeFolder = "flowcharts")

//@PWA(name = "Vaadin Application",shortName = "Vaadin App",description = "This is an example Vaadin application.",enableInstallPrompt = false)
@Push
@Theme(themeFolder = "flowcrmtutorial")
@PWA(
        name = "Vaadin Application",
        shortName = "Vaadin App",
        offlinePath="./offline.html",
        offlineResources = { "./images/offline.png"},
        enableInstallPrompt = false
)
public class MainLayout extends AppLayout {

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames("flex", "mx-s", "p-s", "relative", "text-secondary");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames("font-medium", "text-s");

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        /**
         * Simple wrapper to create icons using LineAwesome iconset. See
         * https://icons8.com/line-awesome
         */
        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                // Use Lumo classnames for suitable font size and margin
                addClassNames("me-s", "text-l");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassName("text-secondary");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");


        viewTitle = new H1();
        //viewTitle.addClassNames("text-l", "m-m");
        viewTitle.addClassNames("m-0", "text-l");
        Button logout = new Button("Log out", e -> Notification.show("Hello "));

        Avatar avatar = new Avatar("logo");
        avatar.setImage("images/logo.png");

        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);

        MenuItem menuItem = menuBar.addItem(avatar);
        SubMenu subMenu = menuItem.getSubMenu();
        subMenu.addItem("Profile");
        subMenu.addItem("Account");
        subMenu.addItem("Preferences");
        subMenu.addItem("Settings");
        subMenu.addItem("Help");
        subMenu.add(new Hr());
        subMenu.addItem(logout);

        HorizontalLayout header = new HorizontalLayout(toggle, viewTitle, menuBar);
        /** Header header = new Header(toggle, viewTitle);
         *   header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center","w-full");
         */
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(viewTitle);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");
        return header;
    }

    private Component createDrawerContent() {
        H2 appName = new H2("Mds framework Examples");
        appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("list-none", "m-0", "p-0");
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }
        return nav;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Empty", "la la-file", EmptyView.class), //

                new MenuItemInfo("Hello World", "la la-globe", HelloWorldView.class), //
               // new MenuItemInfo("MyList", "la la-th", MyListView.class), //
                new MenuItemInfo("Messages-basic", "la la-th", MessagesBasic.class), //

                new MenuItemInfo("List", "la la-th", ListView.class), //
                new MenuItemInfo("Menu-Bar-View", "la la-th", MenuBarView.class), //
/*
                new MenuItemInfo("Credit Card Form", "la la-credit-card", CreditCardFormView.class), //



                new MenuItemInfo("Checkout Form", "la la-credit-card", CheckoutFormView.class), //
*/
        };
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
