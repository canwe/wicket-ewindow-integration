package com.vk.ewindow;

import org.apache.wicket.util.template.PackagedTextTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author victor.konopelko
 *         Date: 17.05.11
 */
public class TextTemplate {

    private final Map<String, Object> variables = new HashMap<String, Object>();

    public TextTemplate(Map<String, Object> map) {
        if (null != map) {
            variables.putAll(map);
        }
    }

    public String fill() {
        //UTF-8 encoding is forced to set
        org.apache.wicket.util.template.TextTemplate template = new PackagedTextTemplate(TextTemplate.class, "TextTemplate.tpl", "text", "UTF-8");
        return template.asString(variables);
    }

    public static TextTemplate mock() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subtitle", "Subtitle");
        map.put("description", "Description goes here. Very long description :)");
        map.put("about", "2011");
        return new TextTemplate(map);
    }
}
