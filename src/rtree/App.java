package rtree;

import java.util.List;

import javax.swing.JOptionPane;

import rtree.node.QuadraticSplitter;
import shape.Point;
import shape.Rectangle;
import utils.Benchmark;
import utils.Record;

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
               int n = 10;
               Point[] points = Benchmark.generateRandomPoints(n, new float[] { 0, n }, new float[] { 0, n });
               List<Record<Point>> records = Benchmark.generateRecordsPoints(points);
               for (Record<Point> r : records) {
                    tree.insert(r);
               }
               new PlotFrame<>(tree, 0);
          } else if (result == 1) {
               RTree<Rectangle> tree = new RTree<>(min_records, max_records, new QuadraticSplitter<>(min_records));
               int n = 10;
               Rectangle[] rectangles = Benchmark.generateRandomRectangles(n, new float[] { 0, 500 },
                         new float[] { -100, 600 });
               List<Record<Rectangle>> records = Benchmark.generateRecordsRectangle(rectangles);
               for (Record<Rectangle> r : records) {
                    tree.insert(r);
               }
               new PlotFrame<>(tree, 1);
          }
     }
}
