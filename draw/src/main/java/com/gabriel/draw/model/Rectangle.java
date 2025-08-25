package com.gabriel.draw.model;

import com.gabriel.draw.service.RectangleRendererService;
import com.gabriel.drawfx.model.Shape;
import java.awt.Color;
import java.awt.Point;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        super(new Point(x, y));  // Use top-left corner as start point
        this.width = width;
        this.height = height;
        this.setColor(Color.BLUE);  // Choose default color
        this.setRendererService(new RectangleRendererService());
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
