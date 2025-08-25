package com.gabriel.draw.view;

import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.ShapeMode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Example panel containing shape selection buttons
public class ShapeSelectorPanel extends JPanel {
    private final AppService appService;

    public ShapeSelectorPanel(AppService appService) {
        this.appService = appService;
        setLayout(new FlowLayout());

        JButton lineButton = new JButton("Line");
        JButton rectangleButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");

        // Add action listeners to buttons to switch ShapeMode
        lineButton.addActionListener(e -> appService.setShapeMode(ShapeMode.Line));
        rectangleButton.addActionListener(e -> appService.setShapeMode(ShapeMode.Rectangle));
        ellipseButton.addActionListener(e -> appService.setShapeMode(ShapeMode.Ellipse));

        add(lineButton);
        add(rectangleButton);
        add(ellipseButton);
    }
}
