package com.gabriel.draw.view;

import com.gabriel.draw.controller.DrawingController;
import com.gabriel.draw.controller.DrawingWindowController;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawingView extends JPanel {
    AppService appService;
    private static final int HANDLE_SIZE = 8;

    public DrawingView(AppService appService){

        this.appService = appService;
        appService.setView(this);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Drawing drawing = (Drawing) appService.getModel();

        for(Shape shape : drawing.getShapes()){
            shape.getRendererService().render(g, shape, false);

            // Draw selection indicator
            if (shape == drawing.getSelectedShape()) {
                Graphics2D g2d = (Graphics2D) g; // Cast to Graphics2D

                // Save the original stroke
                Stroke originalStroke = g2d.getStroke();

                Rectangle2D bounds = shape.getBounds();
                
                // Draw resize handles only (no border)
                drawResizeHandles(g2d, bounds);

                // Restore the original stroke
                g2d.setStroke(originalStroke);
            }
        }
        appService.setView(this);
    }

    private void drawResizeHandles(Graphics2D g2d, Rectangle2D bounds) {
        // Draw filled blue squares for the handles
        g2d.setColor(Color.BLUE);
        g2d.fillRect((int)bounds.getX() - HANDLE_SIZE/2, (int)bounds.getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE); // Top-left
        g2d.fillRect((int)(bounds.getX() + bounds.getWidth()) - HANDLE_SIZE/2, (int)bounds.getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE); // Top-right
        g2d.fillRect((int)bounds.getX() - HANDLE_SIZE/2, (int)(bounds.getY() + bounds.getHeight()) - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE); // Bottom-left
        g2d.fillRect((int)(bounds.getX() + bounds.getWidth()) - HANDLE_SIZE/2, (int)(bounds.getY() + bounds.getHeight()) - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE); // Bottom-right

        // Draw white borders around the handles for better visibility
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect((int)bounds.getX() - HANDLE_SIZE/2, (int)bounds.getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
        g2d.drawRect((int)(bounds.getX() + bounds.getWidth()) - HANDLE_SIZE/2, (int)bounds.getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
        g2d.drawRect((int)bounds.getX() - HANDLE_SIZE/2, (int)(bounds.getY() + bounds.getHeight()) - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
        g2d.drawRect((int)(bounds.getX() + bounds.getWidth()) - HANDLE_SIZE/2, (int)(bounds.getY() + bounds.getHeight()) - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
    }

    // Method to check if a point is on a resize handle
    public int getResizeHandle(Point point, Shape shape) {
        if (shape == null) return -1;

        Rectangle2D bounds = shape.getBounds();
        Rectangle[] handles = {
                new Rectangle((int)bounds.getX() - HANDLE_SIZE/2, (int)bounds.getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE),
                new Rectangle((int)(bounds.getX() + bounds.getWidth()) - HANDLE_SIZE/2, (int)bounds.getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE),
                new Rectangle((int)bounds.getX() - HANDLE_SIZE/2, (int)(bounds.getY() + bounds.getHeight()) - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE),
                new Rectangle((int)(bounds.getX() + bounds.getWidth()) - HANDLE_SIZE/2, (int)(bounds.getY() + bounds.getHeight()) - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE)
        };

        for (int i = 0; i < handles.length; i++) {
            if (handles[i].contains(point)) {
                return i;
            }
        }
        return -1;
    }
}