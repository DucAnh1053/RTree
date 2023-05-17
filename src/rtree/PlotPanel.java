package rtree;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import shape.Boundable;
import utils.Visualizer;

public class PlotPanel<T extends Boundable> extends JPanel {
     private RTree<T> tree;
     private Visualizer<T> visualizer;
     private double scale;

     public PlotPanel(RTree<T> tree) {
          this.tree = tree;
          this.setPreferredSize(new Dimension(1000, 1000));
          visualizer = new Visualizer<>();
          scale = 1;
     }

     @Override
     public void paint(Graphics g) {
          if (tree.num_entries == 0) {
               super.paint(g);
               return;
          }
          Graphics2D g2D = (Graphics2D) g;
          AffineTransform transform = new AffineTransform();
          transform.scale(scale, scale);
          g2D.setTransform(transform);
          visualizer.createVisualization(tree, g2D);
     }

     public void zoomIn() {
          scale += 0.1;
     }

     public void zoomOut() {
          scale -= 0.1;
     }
}
