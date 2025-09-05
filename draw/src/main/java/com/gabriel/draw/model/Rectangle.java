package com.gabriel.draw.model;

import com.gabriel.draw.service.RectangleRendererService;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(Point start, Point end){
        super(start);
        this.setEnd(end);
        this.setColor(Color.RED);
        this.setRendererService(new RectangleRendererService());
    }
    @Override
    public java.awt.Rectangle getBounds() {
        int x = Math.min(getLocation().x, getEnd().x);
        int y = Math.min(getLocation().y, getEnd().y);
        int width = Math.abs(getEnd().x - getLocation().x);
        int height = Math.abs(getEnd().y - getLocation().y);
        return new java.awt.Rectangle(x, y, width, height);
    }

}
