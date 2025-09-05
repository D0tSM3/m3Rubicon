package com.gabriel.drawfx.model;

import com.gabriel.drawfx.service.RendererService;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.awt.*;
@Data
@NoArgsConstructor
public abstract class Shape {
    int id;
    private Point location;
    private Point end;
    private Color color;
    private Color fill;
    private RendererService rendererService;
    public Shape(Point location){
        this.setLocation(location);
        this.setEnd(location);
  }

  public abstract java.awt.Rectangle getBounds();

  public void setEnd(Point point) {
      this.end = point;
  }

  public Point getEnd() {
      return this.end;
  }

  public void setLocation(Point point) {
      this.location = point;
  }

  public Point getLocation() {
      return this.location;
  }

  // ...existing code...

  public void setRendererService(RendererService rendererService) {
      this.rendererService = rendererService;
  }

  public RendererService getRendererService() {
      return this.rendererService;
  }

  // ...existing code...
  

}
