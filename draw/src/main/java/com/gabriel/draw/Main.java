package com.gabriel.draw;

import com.gabriel.draw.component.DrawingMenuBar;
import com.gabriel.draw.controller.DrawingController;
import com.gabriel.draw.service.DeawingCommandAppService;
import com.gabriel.draw.service.DrawingAppService;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.draw.view.DrawingFrame;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.service.AppServiceImpl;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Choose which AppService implementation to use
        // Using AppServiceImpl here, but can change to DrawingAppService or DeawingCommandAppService if needed
        AppService appService = new AppServiceImpl();

        // Create view
        DrawingView drawingView = new DrawingView(appService);

        // Create controller with the AppService and DrawingView (2-arg constructor)
        DrawingController drawingController = new DrawingController(appService, drawingView);

        // Register mouse listeners on the view
        drawingView.addMouseMotionListener(drawingController);
        drawingView.addMouseListener(drawingController);

        // Create frame and menu bar with the same AppService instance
        DrawingFrame drawingFrame = new DrawingFrame(appService);
        DrawingMenuBar drawingMenuBar = new DrawingMenuBar(appService);

        // Add the drawing view to the frame
        drawingFrame.add(drawingView);

        // Set up menu bar and make it visible
        drawingFrame.setJMenuBar(drawingMenuBar);
        drawingMenuBar.setVisible(true);

        // Final frame setup
        drawingFrame.setSize(500, 500);
        drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingFrame.setVisible(true);
    }
}
