import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Result Management System");
        frame.setLayout(null);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a soft pastel background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Title label
        JLabel titleLabel = new JLabel("Student Result Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setBounds(50, 20, 500, 40);
        frame.add(titleLabel);

        // Course and Student Details Button
        JButton btnCourseStudent = new JButton("Course and Student Details");
        btnCourseStudent.setBounds(150, 80, 250, 50);
        btnCourseStudent.setBackground(new Color(102, 205, 170)); // Medium sea green
        btnCourseStudent.setFont(new Font("Arial", Font.BOLD, 16));

        // Result Button
        JButton btnResult = new JButton("Result");
        btnResult.setBounds(150, 150, 250, 50);
        btnResult.setBackground(new Color(135, 206, 235)); // Sky Blue
        btnResult.setFont(new Font("Arial", Font.BOLD, 16));

        // Display Result Button
        JButton btnDisplayResult = new JButton("Display Result");
        btnDisplayResult.setBounds(150, 220, 250, 50);
        btnDisplayResult.setBackground(new Color(255, 165, 0)); // Orange
        btnDisplayResult.setFont(new Font("Arial", Font.BOLD, 16));

        // Action listeners for buttons
        btnCourseStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CourseStudentPage();
                frame.setVisible(false);
            }
        });

        btnResult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ResultPage();
                frame.setVisible(false);
            }
        });

        btnDisplayResult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DisplayResultPage();
                frame.setVisible(false);
            }
        });

        // Add buttons to frame
        frame.add(btnCourseStudent);
        frame.add(btnResult);
        frame.add(btnDisplayResult);

        frame.setVisible(true);
    }
}
