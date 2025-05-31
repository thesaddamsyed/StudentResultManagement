import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static boolean deleteStudent(int rollNo){
        String sql = "DELETE FROM students WHERE roll_no = "+rollNo;
        try(Connection conn = DBConnection.getConnection()){
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);
            return rows>0;
        } catch( Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static List<Student> getAllStudents(){
        Student student = null;
        List<Student> studentList = new ArrayList<>();
        try(Connection conn = DBConnection.getConnection()){
            String sql = "SELECT * FROM students";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                student = new Student(rs.getInt("roll_no"), rs.getString("name"),rs.getString("department"));
                studentList.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void addStudent(Student student){
        try(Connection conn = DBConnection.getConnection()){
            String sql = "INSERT INTO students (roll_no, name, department) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getRollNo());
            ps.setString(2,student.getName());
            ps.setString(3, student.getDepartment());
            ps.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Student getStudentByRollNo(int rollNo){
        Student student = null;
        try(Connection conn = DBConnection.getConnection()){
            String sql = "SELECT * FROM students WHERE roll_no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,rollNo);
            ResultSet rs = ps.executeQuery();
            while( rs.next()){
                 student = new Student(
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("department")
                );
            }

        } catch (Exception e) {
            System.out.println("Error fetching student.");
            e.printStackTrace();
        }

        return student;
    }
}
