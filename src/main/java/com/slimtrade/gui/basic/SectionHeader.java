package com.slimtrade.gui.basic;

import com.slimtrade.App;
import com.slimtrade.core.managers.ColorManager;
import com.slimtrade.core.observing.improved.ColorUpdateListener;
import com.slimtrade.gui.FrameManager;

import javax.swing.*;
import java.awt.*;

public class SectionHeader extends JPanel implements ColorUpdateListener {

    public SectionHeader(String title) {
        App.eventManager.addListener(this);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.ipady = 0;
        gc.fill = GridBagConstraints.BOTH;
        JLabel label = new JLabel(title);
        this.add(label);
        this.setPreferredSize(new Dimension(400, 22));
        this.updateColor();

    }

    @Override
    public void updateColor() {
        this.setBackground(ColorManager.PRIMARY);
        this.setBorder(BorderFactory.createLineBorder(ColorManager.HIGH_CONTRAST_1));
    }
}
