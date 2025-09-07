package com.gabriel.draw.view;

import com.gabriel.draw.component.DrawingMenuBar;
import com.gabriel.draw.controller.DrawingWindowController;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;

public class DrawingFrame extends JFrame {

    public DrawingFrame(AppService appService){
        super("Drawing Application");

        DrawingWindowController drawingWindowController = new DrawingWindowController(appService);
        this.addWindowListener(drawingWindowController);
        this.addWindowFocusListener(drawingWindowController);
        this.addWindowStateListener(drawingWindowController);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);  // Centers window on screen

        // Create the drawing view panel, which handles painting shapes
        DrawingView drawingView = new DrawingView(appService);

        // Add menu bar for editing, drawing modes, undo/redo, etc.
        this.setJMenuBar(new DrawingMenuBar(appService));

        // Use BorderLayout and add the drawing view in the center
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(drawingView, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
