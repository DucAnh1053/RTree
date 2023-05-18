package rtree;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import shape.Boundable;
import shape.Point;
import shape.Rectangle;
import utils.Pair;
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
     private String[] searchFields;
     private JCheckBox checkBox;

     @SuppressWarnings("unchecked")
     public PlotFrame(RTree<T> tree, int type) {
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

          searchFields = new String[] { "RangeSearch", "KNN" };

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
                         try {
                              float xMin = Float.parseFloat(tf1.getText());
                              float xMax = Float.parseFloat(tf2.getText());
                              float yMin = Float.parseFloat(tf3.getText());
                              float yMax = Float.parseFloat(tf4.getText());
                              tf1.setText("");
                              tf2.setText("");
                              tf3.setText("");
                              tf4.setText("");
                              tree.insert((Record<T>) new Record<Rectangle>(new Rectangle(xMin, xMax, yMin, yMax),
                                        id + ""));
                              id++;
                              repaint();
                         } catch (NumberFormatException err) {
                              tf1.setText("");
                              tf2.setText("");
                              tf3.setText("");
                              tf4.setText("");
                              JOptionPane.showMessageDialog(null, "An error occurred: " + err.getMessage(), "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                         }
                    }
               });
               b2 = new JButton("Xoá");
               b2.addActionListener(e -> {
                    int option = JOptionPane.showConfirmDialog(null, rectangleFields, "Nhập toạ độ",
                              JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                         try {
                              float xMin = Float.parseFloat(tf1.getText());
                              float xMax = Float.parseFloat(tf2.getText());
                              float yMin = Float.parseFloat(tf3.getText());
                              float yMax = Float.parseFloat(tf4.getText());
                              tf1.setText("");
                              tf2.setText("");
                              tf3.setText("");
                              tf4.setText("");
                              tree.delete(
                                        (Record<T>) new Record<Rectangle>(new Rectangle(xMin, xMax, yMin, yMax), null));
                              repaint();
                         } catch (NumberFormatException err) {
                              tf1.setText("");
                              tf2.setText("");
                              tf3.setText("");
                              tf4.setText("");
                              JOptionPane.showMessageDialog(null, "An error occurred: " + err.getMessage(), "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                         }
                    }
               });
          }
          b3 = new JButton("Tìm kiếm");
          b3.addActionListener(e -> {
               int result = JOptionPane.showOptionDialog(null, "Chọn một kiểu dữ liệu R-tree để chạy",
                         "Tìm kiếm",
                         JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, searchFields,
                         searchFields[0]);
               if (result == 0) {
                    int option = JOptionPane.showConfirmDialog(null, rectangleFields, "Nhập toạ độ",
                              JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                         try {
                              float xMin = Float.parseFloat(tf1.getText());
                              float xMax = Float.parseFloat(tf2.getText());
                              float yMin = Float.parseFloat(tf3.getText());
                              float yMax = Float.parseFloat(tf4.getText());
                              tf1.setText("");
                              tf2.setText("");
                              tf3.setText("");
                              tf4.setText("");
                              Rectangle rectangle = new Rectangle(xMin, xMax, yMin, yMax);
                              List<Record<T>> lRecords = tree.rangeSearch(rectangle);
                              StringBuilder sb = new StringBuilder();
                              for (Record<T> record : lRecords) {
                                   sb.append(record).append("\n");
                              }
                              sb.delete(sb.length() - 1, sb.length());
                              JTextArea textArea = new JTextArea(sb.toString());
                              textArea.setEditable(false);
                              JScrollPane scrollPane = new JScrollPane(textArea);
                              scrollPane.setPreferredSize(new Dimension(400, 300));
                              panel.setRectangle(rectangle);
                              repaint();
                              JOptionPane.showMessageDialog(null, scrollPane, "Kết quả",
                                        JOptionPane.INFORMATION_MESSAGE);
                              panel.setRectangle(null);
                              repaint();
                         } catch (NumberFormatException err) {
                              tf1.setText("");
                              tf2.setText("");
                              tf3.setText("");
                              tf4.setText("");
                              JOptionPane.showMessageDialog(null, "An error occurred: " + err.getMessage(), "Lỗi",
                                        JOptionPane.ERROR_MESSAGE);
                         }
                    }
               } else if (result == 1) {
                    if (type == 0) {
                         int option = JOptionPane.showConfirmDialog(null, pointFields, "Nhập toạ độ",
                                   JOptionPane.OK_CANCEL_OPTION);
                         if (option == JOptionPane.OK_OPTION) {
                              try {
                                   float x = Float.parseFloat(tf1.getText());
                                   float y = Float.parseFloat(tf2.getText());
                                   tf1.setText("");
                                   tf2.setText("");
                                   JOptionPane.showConfirmDialog(null, new Object[] { "Số lượng", tf1 }, "KNN",
                                             JOptionPane.OK_CANCEL_OPTION);
                                   int num = Integer.parseInt(tf1.getText());
                                   tf1.setText("");
                                   List<Pair<Record<T>, Float>> lPairs = tree.nearestNeighborsSearch(
                                             (Record<T>) new Record<Point>(new Point(x, y), null), num);
                                   StringBuilder sb = new StringBuilder();
                                   for (Pair<Record<T>, Float> pair : lPairs) {
                                        sb.append(pair).append("\n");
                                   }
                                   sb.delete(sb.length() - 1, sb.length());
                                   float maxDis = lPairs.get(lPairs.size() - 1).getSecond();
                                   Rectangle rectangle = new Rectangle(x - maxDis, x + maxDis, y - maxDis, y + maxDis);
                                   panel.setCircle(rectangle);
                                   repaint();
                                   JTextArea textArea = new JTextArea(sb.toString());
                                   textArea.setEditable(false);
                                   JScrollPane scrollPane = new JScrollPane(textArea);
                                   scrollPane.setPreferredSize(new Dimension(400, 300));
                                   JOptionPane.showMessageDialog(null, scrollPane, "Kết quả",
                                             JOptionPane.INFORMATION_MESSAGE);
                                   panel.setCircle(null);
                                   repaint();
                              } catch (NumberFormatException err) {
                                   tf1.setText("");
                                   tf2.setText("");
                                   JOptionPane.showMessageDialog(null, "An error occurred: " + err.getMessage(), "Lỗi",
                                             JOptionPane.ERROR_MESSAGE);
                              }
                         }
                    } else {
                         int option = JOptionPane.showConfirmDialog(null, rectangleFields, "Nhập toạ độ",
                                   JOptionPane.OK_CANCEL_OPTION);
                         if (option == JOptionPane.OK_OPTION) {
                              try {
                                   float xMin = Float.parseFloat(tf1.getText());
                                   float xMax = Float.parseFloat(tf2.getText());
                                   float yMin = Float.parseFloat(tf3.getText());
                                   float yMax = Float.parseFloat(tf4.getText());
                                   tf1.setText("");
                                   tf2.setText("");
                                   tf3.setText("");
                                   tf4.setText("");
                                   JOptionPane.showConfirmDialog(null, new Object[] { "Số lượng", tf1 }, "KNN",
                                             JOptionPane.OK_CANCEL_OPTION);
                                   int num = Integer.parseInt(tf1.getText());
                                   tf1.setText("");
                                   List<Pair<Record<T>, Float>> lPairs = tree.nearestNeighborsSearch(
                                             (Record<T>) new Record<Rectangle>(new Rectangle(xMin, xMax, yMin, yMax),
                                                       null),
                                             num);
                                   StringBuilder sb = new StringBuilder();
                                   for (Pair<Record<T>, Float> pair : lPairs) {
                                        sb.append(pair).append("\n");
                                   }
                                   sb.delete(sb.length() - 1, sb.length());
                                   JTextArea textArea = new JTextArea(sb.toString());
                                   textArea.setEditable(false);
                                   JScrollPane scrollPane = new JScrollPane(textArea);
                                   scrollPane.setPreferredSize(new Dimension(400, 300));
                                   JOptionPane.showMessageDialog(null, scrollPane, "Kết quả",
                                             JOptionPane.INFORMATION_MESSAGE);
                              } catch (NumberFormatException err) {
                                   tf1.setText("");
                                   tf2.setText("");
                                   tf3.setText("");
                                   tf4.setText("");
                                   JOptionPane.showMessageDialog(null, "An error occurred: " + err.getMessage(), "Lỗi",
                                             JOptionPane.ERROR_MESSAGE);
                              }
                         }
                    }
               }
          });
          p2.add(b);
          p2.add(b2);
          p2.add(b3);
          if (type == 0) {
               checkBox = new JCheckBox("Show Coordinate");
               checkBox.setSelected(true);
               checkBox.addActionListener(e -> {
                    if (checkBox.isSelected()) {
                         panel.showCoordinate();
                         repaint();
                    } else {
                         panel.hideCoordinate();
                         repaint();
                    }
               });
               p2.add(checkBox);
          }

          this.add(p);
          this.add(p2);
          this.pack();
          // this.setSize(1200, 1000);
          this.setLocationRelativeTo(null);
          this.setVisible(true);
          // this.setResizable(false);
     }
}
