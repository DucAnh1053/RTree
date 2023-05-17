package rtree;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import shape.Boundable;
import shape.Point;
import shape.Rectangle;
import utils.Benchmark;
import utils.Record;

public class PlotFrame<T extends Boundable> extends JFrame {
     private PlotPanel<T> panel;
     private JTextField tf1;
     private JTextField tf2;
     private JTextField tf3;
     private JTextField tf4;
     private JButton b;
     private JButton b1;
     private JButton b2;
     private JButton b3;
     private int id;

     private RTree<T> tree;

     @SuppressWarnings("unchecked")
     public PlotFrame(RTree<T> tree, int type) {
          this.tree = tree;

          panel = new PlotPanel<>(tree);

          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setTitle("R-Tree Visualization");
          this.setLayout(new FlowLayout());

          JPanel p = new JPanel();
          p.setBorder(new EmptyBorder(0, 0, 0, 0));
          p.add(panel);

          JPanel p2 = new JPanel();
          p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));

          if (type == 0) {
               tf1 = new JTextField();
               tf2 = new JTextField();

               p2.add(new JLabel("X:"));
               p2.add(tf1);
               p2.add(new JLabel("Y:"));
               p2.add(tf2);

               b = new JButton("Thêm");
               b.addActionListener(e -> {
                    float x = Float.parseFloat(tf1.getText());
                    float y = Float.parseFloat(tf2.getText());
                    tf1.setText("");
                    tf2.setText("");
                    tree.insert((Record<T>) new Record<Point>(new Point(x, y), id + ""));
                    id++;
                    repaint();
               });
               b1 = new JButton("Thêm ngẫu nhiên 10 điểm");
               b1.addActionListener(e -> {
                    int n = 10;
                    Point[] points = Benchmark.generateRandomPoints(n, new float[] { 0, n }, new float[] { 0, n });
                    List<Record<Point>> records = Benchmark.generateRecordsPoints(points);
                    for (Record<Point> r : records) {
                         tree.insert((Record<T>) r);
                    }
                    b1.setEnabled(false);
                    repaint();
               });
               p2.add(b);
               p2.add(b1);
          } else if (type == 1) {
               tf1 = new JTextField();
               tf2 = new JTextField();
               tf3 = new JTextField();
               tf4 = new JTextField();

               p2.add(new JLabel("Điểm dưới trái"));
               p2.add(new JLabel("X:"));
               p2.add(tf1);
               p2.add(new JLabel("Y:"));
               p2.add(tf2);

               p2.add(new JLabel("Điểm trên phải"));
               p2.add(new JLabel("X:"));
               p2.add(tf3);
               p2.add(new JLabel("Y:"));
               p2.add(tf4);

               b = new JButton("Thêm");
               b.addActionListener(e -> {
                    float blx = Float.parseFloat(tf1.getText());
                    float bly = Float.parseFloat(tf2.getText());
                    float urx = Float.parseFloat(tf3.getText());
                    float ury = Float.parseFloat(tf4.getText());
                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    tf4.setText("");
                    tree.insert((Record<T>) new Record<Rectangle>(new Rectangle(blx, urx, bly, ury), id + ""));
                    id++;
                    repaint();
               });
               b1 = new JButton("Thêm ngẫu nhiên 10 HCN");
               b1.addActionListener(e -> {
                    int n = 10;
                    Rectangle[] rectangles = Benchmark.generateRandomRectangles(n, new float[] { 0, n },
                              new float[] { 0, n });
                    List<Record<Rectangle>> records = Benchmark.generateRecordsRectangle(rectangles);
                    for (Record<Rectangle> r : records) {
                         tree.insert((Record<T>) r);
                    }
                    b1.setEnabled(false);
                    repaint();
               });
               p2.add(b);
               p2.add(b1);
          } else {
               throw new IllegalArgumentException();
          }
          b2 = new JButton("+");
          b2.addActionListener(e -> {
               panel.zoomIn();
               repaint();
          });
          b3 = new JButton("-");
          b3.addActionListener(e -> {
               panel.zoomOut();
               repaint();
          });
          p2.add(b2);
          p2.add(b3);

          this.add(p);
          this.add(p2);
          this.pack();
          // this.setSize(1200, 1000);
          this.setLocationRelativeTo(null);
          this.setVisible(true);
          // this.setResizable(false);
     }
}
