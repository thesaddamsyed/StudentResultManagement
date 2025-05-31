public class TestConnection{
    public static void main(String[] args) {
        try{
            if(DBConnection.getConnection() != null){
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
