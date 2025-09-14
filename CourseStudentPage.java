import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CourseStudentPage {
    JFrame frame = new JFrame("Course and Student Details");

    public CourseStudentPage() {
        frame.setLayout(null);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice blue

        // Title label
        JLabel titleLabel = new JLabel("Enter Course and Student Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setBounds(50, 20, 500, 40);
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

        // Student Name
        JLabel lblStudentName = new JLabel("Student Name:");
        lblStudentName.setBounds(50, 160, 150, 30);
        frame.add(lblStudentName);

        JTextField txtStudentName = new JTextField();
        txtStudentName.setBounds(200, 160, 200, 30);
        frame.add(txtStudentName);

        // Save Button with Background Color
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(150, 200, 100, 50);
        btnSave.setBackground(new Color(50, 205, 50)); // Lime green
        btnSave.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(btnSave);

        // Clear Button with Red Background Color
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(300, 200, 100, 50);
        btnClear.setBackground(Color.RED); // Red color for Clear button
        btnClear.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(btnClear);

        // Action listener for Save button
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveCourseStudentDetails(txtStudentId.getText(), txtCourse.getText(), txtStudentName.getText());
            }
        });

        // Action listener for Clear button
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear all fields
                txtStudentId.setText("");
                txtCourse.setText("");
                txtStudentName.setText("");
            }
        });

        frame.setVisible(true);
    }

    // Method to save course and student details to MySQL
    private void saveCourseStudentDetails(String studentId, String courseName, String studentName) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO CourseStudent (student_id, student_name, course_name) VALUES (?, ?, ?)")) {

            // Insert student and course details into the same table
            stmt.setString(1, studentId);
            stmt.setString(2, studentName);
            stmt.setString(3, courseName);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Course and Student Details Saved!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving details: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new CourseStudentPage();
    }
}
