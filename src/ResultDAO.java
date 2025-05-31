import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    public static boolean deleteResultByRollAndSubject(int rollNo, String subject){
        String sql = "DELETE FROM results WHERE roll_no ="+rollNo +" AND subject = '"+subject+"'";
        try(Connection conn = DBConnection.getConnection()){
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);
            return rows > 0;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteResultByRollNo(int rollNo){
        String sql = "DELETE FROM results WHERE roll_no ="+rollNo;
        try(Connection conn = DBConnection.getConnection()){
            Statement stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);
            return rows > 0;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateMarks(int rollNo, String subject, int marks){
        String sql = "UPDATE results SET marks = " +marks+ " WHERE roll_no = " +rollNo+" AND subject = '"+subject+"'";
        boolean updated = false;
        try(Connection conn = DBConnection.getConnection()){
            Statement stmt = conn.createStatement();
            int row = stmt.executeUpdate(sql);
            if(row > 0){
                updated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public static List<Result> getAllResults(){
        List<Result> resultList = new ArrayList<>();
        try(Connection conn = DBConnection.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM results");
            while( rs.next()){
                Result result = new Result(
                        rs.getInt("roll_no"),
                        rs.getString("subject"),
                        rs.getInt("marks")
                );
                resultList.add(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }








    public static void addResult(Result result){
        try(Connection conn = DBConnection.getConnection()){
            String sql = "INSERT INTO results (roll_no, subject, marks) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, result.getRollNo());
            ps.setString(2,result.getSubject());
            ps.setInt(3, result.getMarks());
            ps.executeUpdate();
            System.out.println("✅👍 Result added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌👎 Error while adding result.");
        }
    }

    public static List<Result> getResultByRollNo(int rollNo){
        Result result = null;
        List<Result> resultList = new ArrayList<>();
        try(Connection conn = DBConnection.getConnection()){
            String sql = "SELECT * FROM results WHERE roll_no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,rollNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result = new Result(
                        rs.getInt("roll_no"),
                        rs.getString("subject"),
                        rs.getInt("marks")
                        );
                resultList.add(result);
            }
        } catch (Exception e) {
            System.out.println("results not found.");
            e.printStackTrace();

        }
        return resultList;
    }
}
