package rtree;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import shape.Boundable;
import shape.Rectangle;
import utils.Visualizer;

public class PlotPanel<T extends Boundable> extends JPanel {
     private RTree<T> tree;
     private Visualizer<T> visualizer;
     private float scale;
     private int lastMouseX, lastMouseY;
     private boolean dragging;
     private double[] position;
     private Rectangle rectangle;

     public PlotPanel(RTree<T> tree) {
          this.tree = tree;
          this.setPreferredSize(new Dimension(1000, 1000));
          visualizer = new Visualizer<>();
          scale = 1;
          position = new double[] { 0, 0 };
     }

     @Override
     public void paint(Graphics g) {
          super.paint(g);
          Graphics2D g2D = (Graphics2D) g;
          AffineTransform transform = new AffineTransform();
          transform.scale(scale, scale);
          g2D.setTransform(transform);
          g2D.translate(position[0] / scale, position[1] / scale);
          visualizer.createVisualization(tree, g2D);
          if (rectangle != null) {
               visualizer.drawRange(tree, g2D, rectangle);
          }
     }

     public void zoomIn() {
          scale += 0.1;
          repaint();
     }

     public void zoomOut() {
          scale -= 0.1;
          repaint();
     }

     @Override
     public void addNotify() {
          super.addNotify();
          MouseAdapter listener = new MouseAdapter() {
               @Override
               public void mousePressed(MouseEvent e) {
                    lastMouseX = e.getX();
                    lastMouseY = e.getY();
                    dragging = true;
                    // repaint();
               }

               @Override
               public void mouseReleased(MouseEvent e) {
                    dragging = false;
                    // repaint();
               }

               @Override
               public void mouseDragged(MouseEvent e) {
                    if (dragging) {
                         position[0] = e.getX() - lastMouseX + position[0];
                         position[1] = e.getY() - lastMouseY + position[1];
                         lastMouseX = e.getX();
                         lastMouseY = e.getY();
                         AffineTransform transform = new AffineTransform();
                         transform.translate(position[0] / scale, position[1] / scale);
                         ((Graphics2D) getGraphics()).transform(transform);
                         repaint();
                    }
               }

               @Override
               public void mouseWheelMoved(MouseWheelEvent e) {
                    int notches = e.getWheelRotation();
                    if (notches < 0) {
                         zoomIn();
                    } else {
                         zoomOut();
                    }
               }
          };
          addMouseListener(listener);
          addMouseMotionListener(listener);
          addMouseWheelListener(listener);
     }

     public void setRectangle(Rectangle rectangle) {
          this.rectangle = rectangle;
     }

     public void showCoordinate() {
          visualizer.setShowCoordinate(true);
     }

     public void hideCoordinate() {
          visualizer.setShowCoordinate(false);
     }
}
