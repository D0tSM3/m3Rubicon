package com.gabriel.drawfx.model;

import com.gabriel.drawfx.service.RendererService;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.awt.*;
@Data
@NoArgsConstructor
public abstract class Shape {
    private Point location;
    private Point end;
    private Color color;
    private RendererService rendererService;
    public Shape(Point location){
        this.setLocation(location);
        this.setEnd(location);
    }
    @SuppressWarnings("unused")
    public int getX() {
        return location.x;
    }

    public void setX(int x) {
        this.location.x = x;
    }

    @SuppressWarnings("unused")
    public int getY() {
        return location.y;
    }

    public void setY(int y) {
        this.location.y = y;
    }
}
