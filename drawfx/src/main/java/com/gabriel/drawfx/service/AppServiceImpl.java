package com.gabriel.drawfx.service;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;

import javax.swing.*;
import java.awt.*;

public class AppServiceImpl implements AppService {
    private Shape selectedShape;
    private ShapeMode shapeMode;
    private DrawMode drawMode;
    private Color color;
    private Color fill;
    private Drawing model;
    private JPanel view;

    public AppServiceImpl() {
        this.model = new Drawing();  // Initialize your drawing model here
        this.shapeMode = ShapeMode.Line;  // Example default
        this.drawMode = DrawMode.Idle;    // Example default
        this.color = Color.BLACK;
        this.fill = Color.WHITE;
    }

    @Override
    public void undo() {
        // TODO: implement undo logic here
    }

    @Override
    public void redo() {
        // TODO: implement redo logic here
    }

    @Override
    public Shape getSelectedShape() {
        return selectedShape;
    }

    @Override
    public void setSelectedShape(Shape shape) {
        this.selectedShape = shape;
    }

    @Override
    public ShapeMode getShapeMode() { return shapeMode; }

    @Override
    public void setShapeMode(ShapeMode shapeMode) { this.shapeMode = shapeMode; }

    @Override
    public DrawMode getDrawMode() { return drawMode; }

    @Override
    public void setDrawMode(DrawMode drawMode) { this.drawMode = drawMode; }

    @Override
    public Color getColor() { return color; }

    @Override
    public void setColor(Color color) { this.color = color; }

    @Override
    public Color getFill() { return fill; }

    @Override
    public void setFill(Color color) { this.fill = color; }

    @Override
    public void move(Shape shape, Point newLoc) {
        if (shape != null) {
            shape.setLocation(newLoc);
        }
    }

    @Override
    public void scale(Shape shape, Point newEnd) {
        if (shape != null) {
            shape.setEnd(newEnd);
        }
    }

    @Override
    public void create(Shape shape) {
        if (shape != null) {
            model.getShapes().add(shape);
        }
    }

    @Override
    public void delete(Shape shape) {
        if (shape != null) {
            model.getShapes().remove(shape);
        }
    }

    @Override
    public void close() {
        // TODO: implement resource cleanup if needed
    }

    @Override
    public Drawing getModel() {
        return model;
    }

    @Override
    public JPanel getView() {
        return view;
    }

    @Override
    public void setView(JPanel panel) {
        this.view = panel;
    }

    @Override
    public void repaint() {
        if (view != null) {
            view.repaint();
        }
    }
}
