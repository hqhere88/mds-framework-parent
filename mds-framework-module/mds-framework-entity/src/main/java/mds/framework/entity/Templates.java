package mds.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Templates {

    private String richTextHtml;

    public String getRichTextHtml() {
        return richTextHtml;
    }

    public void setRichTextHtml(String richTextHtml) {
        this.richTextHtml = richTextHtml;
    }

    public void setLoremIpsum() {
    }

}
