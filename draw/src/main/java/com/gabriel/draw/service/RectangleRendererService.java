package com.gabriel.draw.service;

import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.model.Shape;
// Avoid importing java.awt.Rectangle here or use fully qualified names if needed

import java.awt.Graphics;

public class RectangleRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        com.gabriel.draw.model.Rectangle rect = (com.gabriel.draw.model.Rectangle) shape;

        if (xor) {
            g.setXORMode(rect.getColor());
        } else {
            g.setColor(rect.getColor());
        }

        // Draw rectangle
        g.drawRect(
                rect.getLocation().x,
                rect.getLocation().y,
                rect.getWidth(),
                rect.getHeight()
        );
    }
}
