package com.vk.ewindow;

import java.awt.*;
import java.util.Random;

import static java.lang.Integer.toHexString;

/**
 * RandomColor is a class that allows the user to create random
 * colors and random grays.
 *
 * @author William Austad
 * @author Victor Konopelko
 * @version 5/12/03
 */
public class RandomColor
{

    private static Random rand = new Random();

    /**
     * Constructor for objects of class RandomColor initializes the
     * random number generator
     */
    public RandomColor()
    {
        //
    }

    /**
     * randomColor returns a pseudorandom Color
     *
     * @return a pseudorandom Color
     */
    public static Color randomColor()
    {
        return(new Color(rand.nextInt(256),
                         rand.nextInt(256),
                         rand.nextInt(256)));
    }

    /**
     * randomGray returns a pseudorandom gray Color
     *
     * @return a pseudorandom Color
     */
    public static Color randomGray()
    {
        int intensity = rand.nextInt(256);
        return(new Color(intensity, intensity, intensity));
    }

    public static String hex(Color c) {
        return hex(c, "0x");
    }

    public static String cssHex(Color c) {
        return hex(c, "#");
    }

    public static String hex(Color c, String prefix) {
        return prefix + toHexString(c.getRed()) + toHexString(c.getGreen()) + toHexString(c.getBlue());
    }
}
