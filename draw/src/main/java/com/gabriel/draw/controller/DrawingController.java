package com.gabriel.draw.controller;

import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.draw.model.Ellipse;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController  implements MouseListener, MouseMotionListener {
    private Point start;
    private Point end;
    final private DrawingView drawingView;

    Shape currentShape;
    AppService appService;
     public DrawingController(AppService appService, DrawingView drawingView){
       this.appService = appService;
         this.drawingView = drawingView;
         drawingView.addMouseListener(this);
         drawingView.addMouseMotionListener(this);
         appService.setDrawMode(DrawMode.Idle);
         appService.setShapeMode(ShapeMode.Line);
     }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.Idle) {
            start = e.getPoint();

            if (appService.getShapeMode() == ShapeMode.Line) {
                currentShape = new Line(start, start);
            } else if (appService.getShapeMode() == ShapeMode.Rectangle) {
                currentShape = new Rectangle(start.x, start.y, 0, 0);
            } else if (appService.getShapeMode() == ShapeMode.Ellipse) {
                currentShape = new Ellipse(start.x, start.y, 0, 0);
            }


            appService.setDrawMode(DrawMode.MousePressed);
            drawingView.repaint();  // repaint to reflect new shape
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.MousePressed) {
            appService.create(currentShape);
            appService.setDrawMode(DrawMode.Idle);
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void updateShapeBounds(Shape shape, Point start, Point end) {
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(end.x - start.x);
        int height = Math.abs(end.y - start.y);

        if (shape instanceof Rectangle rect) {
            rect.setX(x);
            rect.setY(y);
            rect.setWidth(width);
            rect.setHeight(height);
        } else if (shape instanceof Ellipse ellipse) {
            ellipse.setX(x);
            ellipse.setY(y);
            ellipse.setWidth(width);
            ellipse.setHeight(height);
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.MousePressed) {
            Point end = e.getPoint();

            if (appService.getShapeMode() == ShapeMode.Line) {
                appService.scale(currentShape, end);
            } else if (appService.getShapeMode() == ShapeMode.Rectangle ||
                    appService.getShapeMode() == ShapeMode.Ellipse) {
                updateShapeBounds(currentShape, start, end);
            }

            drawingView.repaint(); // repaint on each drag
        }
    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
