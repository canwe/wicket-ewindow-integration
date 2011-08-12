package com.vk.ewindow.simple;

import com.vk.ewindow.GMapExampleApplication;
import com.vk.ewindow.TextTemplate;
import com.vk.ewindow.WicketExamplePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import wicket.contrib.gmap.GMap2;
import wicket.contrib.gmap.api.*;
import wicket.contrib.gmap.event.LoadListener;
import wicket.contrib.gmap.ewindow.EWindowHeaderContributor;

/**
 * SimplePage for the wicket-contrib-gmap2 project
 */
public class SimplePage extends WicketExamplePage
{

	private static final long serialVersionUID = 1L;

	public SimplePage()
	{
		final GMap2 map = new GMap2("map", GMapExampleApplication.get().getGoogleMapsAPIkey());
		map.setCenter(new GLatLng(52.37649, 4.888573));
        map.setZoom(13);
        map.add(new EWindowHeaderContributor());
        final EInfoWindow eInfoWindow = new EInfoWindow(map);
        map.addOverlay(eInfoWindow);

        map.add(new LoadListener() {
            @Override
            protected void onLoad(AjaxRequestTarget target) {
                final GMarker m = new GMarker(new GLatLng(52.37649, 4.888573));
                map.addOverlay(m);

                m.addListener(GEvent.click, new GEventHandler() {
                    @Override
                    public void onEvent(AjaxRequestTarget target) {
                        eInfoWindow.open(m, eInfoWindow.pretty("Title", TextTemplate.mock().fill()));
                    }
                });
            }
        });
		add(map);
	}
}
