package com.slimtrade.gui.buttons;

import com.slimtrade.core.managers.ColorManager;

import javax.swing.*;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class CustomArrowButton extends BasicArrowButton {

    // TODO : direction only constructor
    private static Color background = ColorManager.PRIMARY;
    private static Color shadow = Color.GREEN;
    private static Color darkShadow = ColorManager.TEXT;
    private static Color highlight = Color.blue;
    private int direction = 0;

    public CustomArrowButton(int direction) {
        super(direction, background, shadow, darkShadow, highlight);
        this.direction = direction;
    }

//    public CustomArrowButton(int direction, Color background, Color shadow, Color darkShadow, Color highlight) {
//        super(direction, background, shadow, darkShadow, highlight);
//
//    }

    public void paint(Graphics g) {
        Color origColor;
        boolean isPressed, isEnabled;
        int w, h, size;

        w = getSize().width;
        h = getSize().height;
        origColor = g.getColor();
        isPressed = getModel().isPressed();
        isEnabled = isEnabled();

        g.setColor(getBackground());
        g.fillRect(1, 1, w-2, h-2);

        /// Draw the proper Border
//        if (getBorder() != null && !(getBorder() instanceof UIResource)) {
//            paintBorder(g);
//        } else if (isPressed) {
//            g.setColor(shadow);
//            g.drawRect(0, 0, w-1, h-1);
//        } else {
//            // Using the background color set above
//            g.drawLine(0, 0, 0, h-1);
//            g.drawLine(1, 0, w-2, 0);
//
//            g.setColor(highlight);    // inner 3D border
//            g.drawLine(1, 1, 1, h-3);
//            g.drawLine(2, 1, w-3, 1);
//
//            g.setColor(shadow);       // inner 3D border
//            g.drawLine(1, h-2, w-2, h-2);
//            g.drawLine(w-2, 1, w-2, h-3);
//
//            g.setColor(darkShadow);     // black drop shadow  __|
//            g.drawLine(0, h-1, w-1, h-1);
//            g.drawLine(w-1, h-1, w-1, 0);
//        }

        // If there's no room to draw arrow, bail
        if(h < 5 || w < 5)      {
            g.setColor(origColor);
            return;
        }

//        if (isPressed) {
//            g.translate(1, 1);
//        }

        // Draw the arrow
//        size = Math.min((h - 4) / 2, (w - 4) / 2);
////        size = Math.max(size, 3);
//        size = 2;
        size = 5;
        w = w/2;
        h = h/2;
        // TODO : Switch to draw polygon
        paintTriangle(g, w-(size/2), h-(size/2), size, direction, isEnabled);

        // Reset the Graphics back to it's original settings
//        if (isPressed) {
//            g.translate(-1, -1);
//        }
        g.setColor(origColor);

    }

}
