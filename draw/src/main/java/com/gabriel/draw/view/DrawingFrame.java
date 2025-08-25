package com.gabriel.draw.view;

import com.gabriel.draw.controller.DrawingWindowController;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;

public class DrawingFrame extends JFrame {

    public DrawingFrame(AppService appService) {
        DrawingWindowController drawingWindowController = new DrawingWindowController(appService);
        this.addWindowListener(drawingWindowController);
        this.addWindowFocusListener(drawingWindowController);
        this.addWindowStateListener(drawingWindowController);

        // Set the layout to BorderLayout for the content pane
        this.getContentPane().setLayout(new BorderLayout());

        DrawingView drawingView = new DrawingView(appService);
        ShapeSelectorPanel shapeSelectorPanel = new ShapeSelectorPanel(appService);

        // Add the shape selector panel to the top (NORTH) of the frame
        this.getContentPane().add(shapeSelectorPanel, BorderLayout.NORTH);

        // Add the drawing view to center of the frame
        this.getContentPane().add(drawingView, BorderLayout.CENTER);
    }
}
