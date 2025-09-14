import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DisplayResultPage {
    JFrame frame = new JFrame("Display Student Result");

    public DisplayResultPage() {
        frame.setLayout(null);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice blue

        // Title label
        JLabel titleLabel = new JLabel("Student Result", JLabel.CENTER);
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

        // Display Result Button with Background Color
        JButton btnDisplay = new JButton("Display Result");
        btnDisplay.setBounds(150, 200, 150, 50);
        btnDisplay.setBackground(new Color(50, 205, 50)); // Lime green
        btnDisplay.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(btnDisplay);

        // Action listener for Display button
        btnDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayStudentResult(txtStudentId.getText(), txtCourse.getText());
            }
        });

        frame.setVisible(true);
    }

    // Method to display student result
    private void displayStudentResult(String studentId, String courseName) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
             PreparedStatement stmt = conn.prepareStatement("SELECT marks FROM ResultPage WHERE student_id = ? AND course_name = ?")) {

            // Set the parameters for the query
            stmt.setInt(1, Integer.parseInt(studentId));  // Parse studentId to INT
            stmt.setString(2, courseName);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int marks = rs.getInt("marks");
                // Display the result in a message dialog
                JOptionPane.showMessageDialog(frame, "Marks for Student ID " + studentId + " in course " + courseName + ": " + marks);
            } else {
                JOptionPane.showMessageDialog(frame, "No result found for Student ID " + studentId + " in course " + courseName);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error fetching result: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new DisplayResultPage();
    }
}
