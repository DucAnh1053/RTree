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
     private JButton b2;
     private JButton b3;
     private int id;
     private Object[] pointFields;
     private Object[] rectangleFields;

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

          tf1 = new JTextField();
          tf2 = new JTextField();
          tf3 = new JTextField();
          tf4 = new JTextField();

          pointFields = new Object[] {
                    "x", tf1,
                    "y", tf2
          };

          rectangleFields = new Object[] {
                    "xMin", tf1,
                    "xMax", tf2,
                    "yMin", tf3,
                    "yMax", tf4
          };

          if (type == 0) {
               b = new JButton("Thêm điểm");
               b.addActionListener(e -> {
                    int option = JOptionPane.showConfirmDialog(null, pointFields, "Nhập toạ độ",
                              JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                         try {
                              float x = Float.parseFloat(tf1.getText());
                              float y = Float.parseFloat(tf2.getText());
                              tf1.setText("");
                              tf2.setText("");
                              tree.insert((Record<T>) new Record<Point>(new Point(x, y), id + ""));
                              id++;
                              repaint();
                         } catch (NumberFormatException err) {
                              tf1.setText("");
                              tf2.setText("");
                              JOptionPane.showMessageDialog(null, "An error occurred: " + err.getMessage(), "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                         }
                    }
               });
               b2 = new JButton("Xoá");
               b2.addActionListener(e -> {
                    int option = JOptionPane.showConfirmDialog(null, pointFields, "Nhập toạ độ",
                              JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                         try {
                              float x = Float.parseFloat(tf1.getText());
                              float y = Float.parseFloat(tf2.getText());
                              tf1.setText("");
                              tf2.setText("");
                              tree.delete((Record<T>) new Record<Point>(new Point(x, y), null));
                              repaint();
                         } catch (NumberFormatException err) {
                              tf1.setText("");
                              tf2.setText("");
                              JOptionPane.showMessageDialog(null, "An error occurred: " + err.getMessage(), "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                         }
                    }
               });
          } else {
               b = new JButton("Thêm HCN");
               b.addActionListener(e -> {
                    int option = JOptionPane.showConfirmDialog(null, rectangleFields, "Nhập toạ độ",
                              JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                         float xMin = Float.parseFloat(tf1.getText());
                         float xMax = Float.parseFloat(tf2.getText());
                         float yMin = Float.parseFloat(tf3.getText());
                         float yMax = Float.parseFloat(tf4.getText());
                         tf1.setText("");
                         tf2.setText("");
                         tf3.setText("");
                         tf4.setText("");
                         tree.insert((Record<T>) new Record<Rectangle>(new Rectangle(xMin, xMax, yMin, yMax), id + ""));
                         id++;
                         repaint();
                    }
               });
               b2 = new JButton("Xoá");
               b2.addActionListener(e -> {
                    int option = JOptionPane.showConfirmDialog(null, rectangleFields, "Nhập toạ độ",
                              JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                         float xMin = Float.parseFloat(tf1.getText());
                         float xMax = Float.parseFloat(tf2.getText());
                         float yMin = Float.parseFloat(tf3.getText());
                         float yMax = Float.parseFloat(tf4.getText());
                         tf1.setText("");
                         tf2.setText("");
                         tf3.setText("");
                         tf4.setText("");
                         tree.delete((Record<T>) new Record<Rectangle>(new Rectangle(xMin, xMax, yMin, yMax), null));
                         repaint();
                    }
               });
          }
          b3 = new JButton("Tìm kiếm");
          b3.addActionListener(e -> {

          });
          p2.add(b);
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
