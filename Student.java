/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author PRIYADHARSHINI
 */
public class Student {

    static final String URL =
        "jdbc:postgresql://localhost:5432/Student_db";

    static final String USER = "postgres";
    static final String PASSWORD = "6379669795";

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        

        try {
            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            System.out.println("Database Connected Successfully!");

            while (true) {

                System.out.println("\n--- STUDENT MANAGEMENT SYSTEM ---");
                System.out.println("1. Insert Student");
                System.out.println("2. Display Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        insertStudent(con);
                        break;

                    case 2:
                        displayStudent(con);
                        break;

                    case 3:
                        updateStudent(con);
                        break;

                    case 4:
                        deleteStudent(con);
                        break;

                    case 5:
                        con.close();
                        System.out.println("Program Exited!");
                        return;

                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    // INSERT
    static void insertStudent(Connection con)
            throws SQLException {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        String sql = "INSERT INTO student(name, age, department, email) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, dept);
        ps.setString(4, email);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student Inserted Successfully!");
        }

        ps.close();
    }

    // DISPLAY
    static void displayStudent(Connection con)
            throws SQLException {

        String sql = "SELECT * FROM student";

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n--- STUDENT RECORDS ---");

        while (rs.next()) {

            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Age: " + rs.getInt("age"));
            System.out.println("Department: " + rs.getString("department"));
            System.out.println("Email: " + rs.getString("email"));

            System.out.println("----------------------");
        }

        rs.close();
        st.close();
    }

    // UPDATE
    static void updateStudent(Connection con)
            throws SQLException {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter New Email: ");
        String email = sc.nextLine();

        String sql = "UPDATE student SET name=?, age=?, department=?, email=? WHERE id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, dept);
        ps.setString(4, email);
        ps.setInt(5, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student Updated Successfully!");
        } else {
            System.out.println("Student ID Not Found!");
        }

        ps.close();
    }

    // DELETE
    static void deleteStudent(Connection con)
            throws SQLException {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM student WHERE id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student Deleted Successfully!");
        } else {
            System.out.println("Student ID Not Found!");
        }

        ps.close();
    }
}

    
    

