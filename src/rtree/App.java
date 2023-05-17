package rtree;

import javax.swing.JOptionPane;

import rtree.node.QuadraticSplitter;
import shape.Point;
import shape.Rectangle;

public class App {
     public static void main(String[] args) {
          int min_records = 2;
          int max_records = 4;
          String[] options = { "Point", "Rectangle" };
          int result = JOptionPane.showOptionDialog(null, "Chọn một kiểu dữ liệu R-tree để chạy",
                    "R-tree Visualization",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
          if (result == 0) {
               RTree<Point> tree = new RTree<>(min_records, max_records, new QuadraticSplitter<>(min_records));
               new PlotFrame<>(tree, 0);
          } else if (result == 1) {
               RTree<Rectangle> tree = new RTree<>(min_records, max_records, new QuadraticSplitter<>(min_records));
               new PlotFrame<>(tree, 1);
          }
     }
}
