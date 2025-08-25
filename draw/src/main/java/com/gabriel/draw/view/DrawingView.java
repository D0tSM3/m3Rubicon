package com.gabriel.draw.view;

import com.gabriel.draw.controller.DrawingController;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;

public class DrawingView extends JPanel {
    private final AppService appService;

    public DrawingView(AppService appService) {
        this.appService = appService;
        // Instantiate your controller here if it manages input and interaction
        new DrawingController(appService, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears background properly

        Drawing drawing = (Drawing) appService.getModel();
        for (Shape shape : drawing.getShapes()) {
            shape.getRendererService().render(g, shape, false);
        }
    }
}
