package com.gabriel.draw.controller;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController implements MouseListener, MouseMotionListener {
    private Point end;
    private final DrawingView drawingView;

    private Shape currentShape;
    private AppService appService;

    public DrawingController(AppService appService, DrawingView drawingView){
        this.appService = appService;
        this.drawingView = drawingView;

        drawingView.addMouseListener(this);
        drawingView.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();

        // Fetch shapes from your model
        for (Shape shape : appService.getModel().getShapes()) {
            if (shape.getBounds().contains(clickPoint)) {
                appService.setSelectedShape(shape);

                // Request UI repaint to show the selection
                appService.repaint();
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.Idle) {
            Point start = e.getPoint();

            switch (appService.getShapeMode()){
                case Line:
                    currentShape = new Line(start, start);
                    break;
                case Rectangle:
                    currentShape = new Rectangle(start, start);
                    break;
                case Ellipse:
                    currentShape = new Ellipse(start, start);
                    break;
            }

            // Render initial shape preview
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, false);
            appService.setDrawMode(DrawMode.MousePressed);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.MousePressed){
            end = e.getPoint();

            // Finalize creation of the shape via the service
            appService.create(currentShape);

            // Reset draw mode
            appService.setDrawMode(DrawMode.Idle);

            // Refresh the UI to show the new shape
            appService.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.MousePressed) {
            end = e.getPoint();

            // Render shape dynamically as mouse is dragged
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true );

            appService.scale(currentShape, end);

            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true );

            appService.repaint();
        }
    }

    public void deleteSelectedShape() {
        Shape selectedShape = appService.getSelectedShape();
        if (selectedShape != null) {
            appService.delete(selectedShape);
            appService.repaint();
        }
    }

    // Unused event methods - implement if needed or leave empty
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
