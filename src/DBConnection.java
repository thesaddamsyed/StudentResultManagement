import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {


    public static Connection getConnection(){

        final String DRIVER = "oracle.jdbc.OracleDriver";
        final String URL = "jdbc:oracle:thin:@localhost:1522:xe";
        final String USER = "system";
        final String PASS = "saddam";
        Connection conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
