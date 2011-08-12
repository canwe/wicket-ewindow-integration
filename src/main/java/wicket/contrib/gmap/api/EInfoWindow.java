/*
 *
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.contrib.gmap.api;

import org.apache.wicket.ajax.AjaxRequestTarget;
import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.ewindow.EWindowStyle;
import wicket.contrib.gmap.js.Constructor;

/**
 * @author victor.konopelko
 *         Date: 18.05.11
 */
public class EInfoWindow extends GOverlay {

    private EWindowStyle style = EWindowStyle.E_STYLE_9;
    private GMap2 map;

    public EInfoWindow(GMap2 map) {
        this.map = map;
    }

    public EInfoWindow(GMap2 map, EWindowStyle style) {
        if (null != style) {
            this.style = style;
        }
        this.map = map;
    }

    @Override
	public String getJSconstructor()
	{
        String invoke1 = map.getJSinvoke("");
        final String jsGMap2 = invoke1.substring(0, invoke1.length() - 3) + ".map";

		Constructor constructor = new Constructor("EWindow").add(jsGMap2).add(style);
        //System.out.println(constructor.toJS());
		return constructor.toJS();
	}

    @Override
    protected void updateOnAjaxCall(AjaxRequestTarget target, GEvent overlayEvent) {
        //TODO
    }

    public EInfoWindow open(GMarker marker, String html)
	{
		if (AjaxRequestTarget.get() != null)
		{
			AjaxRequestTarget.get().appendJavascript(getJSopen(marker, html));
		}

		return this;
	}

	public void close()
	{
		if (AjaxRequestTarget.get() != null)
		{
			AjaxRequestTarget.get().appendJavascript(getJSclose());
		}
	}

    public static String escapeSingleQuotes(String html) {
        return html.replace('\'', '¬').replace("¬", "\\\"");
    }

    public static String convertDoubleQuotesToSingle(String html) {
        return html.replace('\"', '¬').replace("¬", "\'");
    }

    public static String removeBRsAndTabs(String html) {
        return html.replace("\r\n", " ")
                   .replace(System.getProperty("line.separator"), " ")
                   .replace("\n", " ")
                   .replace("\t", " ")
                   .replace("\r", " ");
    }

    public String pretty(String title, String html) {
        String invoke1 = map.getJSinvoke("resolveOverlay('" + this.getId() + "')");
        final String jsEWindow = escapeSingleQuotes(invoke1.substring(0, invoke1.length() - 2));

        return "<table border='0' cellpadding='0' cellspacing='0'><tr><td width='100%' class='EWTitle'>"
                + title
                + "<a href='javascript:" + jsEWindow + ".hide()'><img width='18' height='17' title='Close the EWindow' src='images/eclose.gif' border='0' style='position:absolute;right:4px;top:4px'></a>"
                + "</td></tr>"
                + "<tr><td>" + removeBRsAndTabs(convertDoubleQuotesToSingle(html)) + "</td></tr></table>";
    }

	private String getJSopen(GMarker marker, String html)
	{
        String invoke1 = map.getJSinvoke("resolveOverlay('" + this.getId() + "')");
        final String jsEWindow = invoke1.substring(0, invoke1.length() - 2);

        String invoke2 = map.getJSinvoke("resolveOverlay('" + marker.getId() + "')");
        final String jsMarker = invoke2.substring(0, invoke2.length() - 2);

		StringBuilder buffer = new StringBuilder();

		buffer.append(jsEWindow).append(".openOnMarker(");
		buffer.append(jsMarker).append(",").append("\"").append(html).append("\"");
		buffer.append(")");

        //System.out.println(buffer.toString());
		return buffer.toString();
	}

	private String getJSclose()
	{
        String invoke = map.getJSinvoke("resolveOverlay('" + this.getId() + "')");
        final String jsEWindow = invoke.substring(0, invoke.length() - 2);
		return "" + jsEWindow + ".hide(); return false;";
	}
}
