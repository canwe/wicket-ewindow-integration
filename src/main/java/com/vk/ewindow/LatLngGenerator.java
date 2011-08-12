package com.vk.ewindow;

import wicket.contrib.gmap.api.GLatLng;


public class LatLngGenerator {

	private LatLngGenerator(){}

    public static GLatLng next() {
        double latitude = (Math.random()*180);
		double longitude = (Math.random()*360);

        double lat, lon;

        if(latitude > 90)
			lat = (latitude-90);
		else
			lat = (-(latitude-90));

		if(longitude >180)
			lon = (longitude - 180);
		else
			lon = -(longitude - 180);

        return new GLatLng(lat, lon);
    }
}
