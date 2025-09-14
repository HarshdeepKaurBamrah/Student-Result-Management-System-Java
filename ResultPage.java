import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ResultPage {
    JFrame frame = new JFrame("Result Entry");

    public ResultPage() {
        frame.setLayout(null);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background color
        frame.getContentPane().setBackground(new Color(255, 255, 240)); // Light yellow

        // Title label
        JLabel titleLabel = new JLabel("Add Student Result", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setBounds(150, 20, 300, 40);
        frame.add(titleLabel);

        // Student ID
        JLabel lblStudentId = new JLabel("Student ID:");
        lblStudentId.setBounds(50, 80, 150, 30);
        frame.add(lblStudentId);

        JTextField txtStudentId = new JTextField();
        txtStudentId.setBounds(200, 80, 200, 30);
        frame.add(txtStudentId);

        // Course Name
        JLabel lblCourse = new JLabel("Course Name:");
        lblCourse.setBounds(50, 120, 150, 30);
        frame.add(lblCourse);

        JTextField txtCourse = new JTextField();
        txtCourse.setBounds(200, 120, 200, 30);
        frame.add(txtCourse);

        // Marks
        JLabel lblMarks = new JLabel("Marks:");
        lblMarks.setBounds(50, 160, 150, 30);
        frame.add(lblMarks);

        JTextField txtMarks = new JTextField();
        txtMarks.setBounds(200, 160, 200, 30);
        frame.add(txtMarks);

        // Save Result Button
        JButton btnSaveResult = new JButton("Save Result");
        btnSaveResult.setBounds(150, 220, 150, 50);
        btnSaveResult.setBackground(new Color(255, 69, 0)); // Red-Orange
        btnSaveResult.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(btnSaveResult);

        // Clear Button with Green Background
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(320, 220, 100, 50);
        btnClear.setBackground(new Color(34, 139, 34)); // Green color for Clear button
        btnClear.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(btnClear);

        // Action listener for Save Result button
        btnSaveResult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveResult(txtStudentId.getText(), txtCourse.getText(), txtMarks.getText());
            }
        });

        // Action listener for Clear button
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear all fields
                txtStudentId.setText("");
                txtCourse.setText("");
                txtMarks.setText("");
            }
        });

        frame.setVisible(true);
    }

    // Method to save result into MySQL database
    private void saveResult(String studentId, String courseName, String marks) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO ResultPage (student_id, course_name, marks) VALUES (?, ?, ?)")) {

            // Insert student result into the database
            stmt.setString(1, studentId);
            stmt.setString(2, courseName);
            stmt.setString(3, marks);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Student Result Saved!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving result: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ResultPage();
    }
}
