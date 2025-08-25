package com.gabriel.draw;

import com.gabriel.drawfx.service.AppService;
import com.gabriel.draw.service.DrawingAppService;
import com.gabriel.draw.view.DrawingFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Recommended to launch GUI on Swing Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            AppService appService = new DrawingAppService();
            DrawingFrame drawingFrame = new DrawingFrame(appService);
            drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            drawingFrame.setSize(500, 500);
            drawingFrame.setVisible(true);
        });
    }
}
