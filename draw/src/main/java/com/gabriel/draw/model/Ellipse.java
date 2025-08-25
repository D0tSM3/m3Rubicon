package com.gabriel.draw.model;

import com.gabriel.draw.service.EllipseRendererService;
import com.gabriel.drawfx.model.Shape;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
import java.awt.Point;

@EqualsAndHashCode(callSuper = true)
@Data
public class Ellipse extends Shape {
    private int width;
    private int height;

    public Ellipse(int x, int y, int width, int height) {
        super(new Point(x, y));  // Use top-left corner as start point
        this.width = width;
        this.height = height;
        this.setColor(Color.GREEN);  // Default color for ellipse
        this.setRendererService(new EllipseRendererService());
    }

    public void setX(int x) {
        getLocation().x = x;
    }

    public void setY(int y) {
        getLocation().y = y;
    }

    public int getX() {
        return getLocation().x;
    }

    public int getY() {
        return getLocation().y;
    }
}
