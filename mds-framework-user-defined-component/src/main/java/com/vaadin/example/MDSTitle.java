package com.vaadin.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;

@Tag("mds-title")
@JsModule("./src/mds-title.js")
@CssImport(value = "./styles/my-styles.css", themeFor = "mds-title")
public class MDSTitle extends Component {

}
