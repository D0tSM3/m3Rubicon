package com.gabriel.draw.view;

import com.gabriel.draw.command.SetColorCommand;
import com.gabriel.drawfx.command.Command;
import com.gabriel.drawfx.command.CommandService;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class DrawingView extends JPanel {
    private final AppService appService;
    private final JColorChooser colorChooser;

    public DrawingView(AppService appService){
        this.appService = appService;
        appService.setView(this);

        this.setLayout(new BorderLayout());

        // Embedded color chooser panel for live color selection
        colorChooser = new JColorChooser(appService.getColor());
        colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Color selectedColor = colorChooser.getColor();
                Command cmd = new SetColorCommand(appService, selectedColor);
                CommandService.ExecuteCommand(cmd);
            }
        });
        this.add(colorChooser, BorderLayout.PAGE_END);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Drawing drawing = (Drawing) appService.getModel();
        if (drawing != null) {
            for (Shape shape : drawing.getShapes()) {
                shape.getRendererService().render(g, shape, true);
            }
        }
    }
}
