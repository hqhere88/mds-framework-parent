package org.vaadin.example.views.MenuBar;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import mds.framework.entity.Country;
import mds.framework.entity.CurrencyPairs;
import mds.framework.service.ICurrencyPairsService;
import mds.framework.service.IDataService;
import org.vaadin.example.views.MainLayout;

import javax.annotation.Resource;
import java.awt.*;
import java.awt.event.KeyEvent;

@PageTitle("Menu Bar View")
@Route(value = "Menu-Bar-View", layout = MainLayout.class)
public class MenuBarView extends VerticalLayout {
    @Resource(name="currencyPairsService")
    private ICurrencyPairsService currencyPairsService;

    @Resource(name="dataService")
    private IDataService dataService;

    public MenuBarView(IDataService dataService) {
        this.currencyPairsService=currencyPairsService;
        this.dataService = dataService;
        init();
    }

    private void init(){
        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);

        menuBar.addItem("View");
        MenuItem edit = menuBar.addItem("Back");
        edit.getElement().setAttribute("disabled", false);

        MenuItem share = menuBar.addItem("Share");
        SubMenu shareSubMenu = share.getSubMenu();
        loadSubMenu(share,shareSubMenu);

        share.addClickListener(event -> clickEvent(share));

        //                Notification.show("Shortcut triggered")
        share.addClickShortcut(Key.KEY_F, KeyModifier.SHIFT);
        add(menuBar);
        add(ComboBoxCustomEntry1());
    }

    private void clickEvent(MenuItem share){
        Notification.show(isOpened(share));
        share.setVisible(true);
        share.getElement().setAttribute("aria-selected",true);
        share.getElement().setAttribute("aria-expanded",true);
       // keyPressDown();
        Notification.show(isOpened(share));
       // share.getElement().setProperty("opened", true);
        //GeneratedVaadinContextMenu
      //  Notification.show(share.getElement().getProperty("opened").toString());

        Notification.show(share.getElement().toString());
    }

    private String isOpened(MenuItem share){
        String message = "share.getContextMenu() is null!";
        if (share!=null) {
            boolean isOpen = share.isVisible();
            if (isOpen) {
                message = "Opened";
            } else {
                message = "Closed";
            }
        }
        return message;
    }

    private void loadSubMenu(MenuItem share,SubMenu shareSubMenu){
        shareSubMenu.removeAll();
      //  shareSubMenu.addItem(ComboBoxCustomEntry1());
        addSubMenu(shareSubMenu);
    }

    private SubMenu addSubMenu(SubMenu shareSubMenu){
        shareSubMenu.addItem("By email")
                .getElement()
                .setAttribute("disabled", true);
        shareSubMenu.addItem("Get Link");
        return shareSubMenu;
    }

    private ComboBox<CurrencyPairs> ComboBoxCustomEntry1() {
        ComboBox<CurrencyPairs> comboBox = new ComboBox<>("");
        comboBox.setAutoOpen(true);
        comboBox.setVisible(true);
        comboBox.setAutofocus(true);
        comboBox.focus();
       // comboBox.setAllowCustomValue(true);
        comboBox.setPlaceholder("Search");
       // add(comboBox);
        //comboBox.setItems("Chrome", "Edge", "Firefox", "Safari");
        ComboBox.ItemFilter<CurrencyPairs> filter = (currencyPairs, filterString) -> currencyPairs.getCurrencyPairs().toLowerCase().startsWith(filterString.toLowerCase());
        comboBox.setItems(filter, dataService.getCurrencyPairs());

        comboBox.getStyle().set("--vaadin-combo-box-overlay-width", "350px");
        comboBox.setItemLabelGenerator(CurrencyPairs::getCurrencyPairs);

        return comboBox;
    }

    private void keyPressDown(){
        Robot robot = null;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
