package com.gabriel.draw.service;

import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.model.Shape;

import java.awt.Graphics;

public class EllipseRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        com.gabriel.draw.model.Ellipse ellipse = (com.gabriel.draw.model.Ellipse) shape;

        if (xor) {
            g.setXORMode(ellipse.getColor());
        } else {
            g.setColor(ellipse.getColor());
        }

        // Draw ellipse using drawOval which uses bounding rectangle
        g.drawOval(
                ellipse.getLocation().x,
                ellipse.getLocation().y,
                ellipse.getWidth(),
                ellipse.getHeight()
        );
    }
}
