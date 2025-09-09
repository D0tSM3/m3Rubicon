package com.gabriel.draw.command;

import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public class FillColorShapeCommand implements Command {
    private AppService appService;
    private Color fillColor;
    private Shape targetShape;
    private Color prevFillColor;

    public FillColorShapeCommand(AppService appService, Color fillColor){
        this.fillColor = fillColor;
        this.appService = appService;
        this.targetShape = appService.getSelectedShape();
        
        // Only store previous color if there's a selected shape
        if (targetShape != null) {
            this.prevFillColor = targetShape.getFill();
        }
    }

    @Override
    public void execute() {
        // Only fill if there's a selected shape
        if (targetShape != null) {
            targetShape.setFill(fillColor);
            appService.repaint();
        }
        // Do nothing if no shape is selected
    }

    @Override
    public void undo() {
        // Only undo if there was a selected shape
        if (targetShape != null) {
            targetShape.setFill(prevFillColor);
            appService.repaint();
        }
    }

    @Override
    public void redo() {
        // Only redo if there was a selected shape
        if (targetShape != null) {
            targetShape.setFill(fillColor);
            appService.repaint();
        }
    }
}