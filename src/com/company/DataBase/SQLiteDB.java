
import java.sql.*;


public class SqliteDB {
    Connection c = null;
    Statement stmt = null;

    SqliteDB() {
        try {
            //Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:NetworkTraffic.sqlite");
            System.out.println("Conncted");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    public HashSet listKnown(){
        HashSet permitted = new HashSet();
        try{
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Known_Devices");

            while(rs.next()){
                String Mac_address = rs.getString("Mac_Address");
                String IP_Address = rs.getString("IP_Add");
                String Dev_Name = rs.getString("Device_Name");
                permitted.add(Mac_address);
                System.out.println(Mac_address+ "  "+IP_Address + "  " + Dev_Name );
            }
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
        return(permitted);
    }
    public void insertIntoTable(String Mac_Address){
        try{
            String sql = "INSERT INTO Known_Devices(Mac_Address) VALUES (" + Mac_Address + ")";
                PreparedStatement insert = c.prepareStatement(sql);
                insert.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(){

        try{
            this.stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Known_Devices(\n"
                    + "    Mac_Address VarChar(20) PRIMARY KEY,\n"
                    + "    IP_Add text ,\n"
                    + "    Device_Name text\n"
                    + ");";
            stmt.execute(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    public void closeConnection(){
        try {
            c.close();
        }catch(Exception e){

        }
    }
}

