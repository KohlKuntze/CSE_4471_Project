
package com.company.DataBase;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class SQLiteDB {
    //Establish a connection with the Empty Database file
    //Or Filled file depending when this program is run
    private static Connection getConnection() {
        Connection c = null;

        try {
            //Use the JDBC connection string with vendor specific sqlite attached
            c = DriverManager.getConnection("jdbc:sqlite:NetworkTraffic.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }
    //close the connection after use
    private static void closeConnection(Connection c){
        try {
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //close the statement after use
    private static void closeStatement(Statement s){
        try {
            s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //close the result set after use
    private static void closeResultSet(ResultSet rs){
        try {
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Get the list of known devices from the Database and return a hash set containing the Mac address strings
    public static Set<String> getPermittedDevices(){
        Set<String> permitted = new HashSet<>();
        try{
            //Set up a connection
            Connection c = getConnection();
            //Create a sql statement
            Statement stmt = c.createStatement();

            //Run the query and store it in a result set
            ResultSet rs = stmt.executeQuery("Select * from Known_Devices");

            //Iterate through the result set and print out each device Mac address,IP and name
            while(rs.next()){
                String Mac_address = rs.getString("Mac_Address");
                String IP_Address = rs.getString("IP_Add");
                String Dev_Name = rs.getString("Device_Name");
                permitted.add(Mac_address);
                System.out.println(Mac_address+ "  "+IP_Address + "  " + Dev_Name );
            }

            //Close the statements, resultset, and connection
            closeStatement(stmt);
            closeResultSet(rs);
            closeConnection(c);

        } catch (Exception e){
            //System.out.print(e.getMessage());
        }
        return(permitted);
    }

    //This will add the mac address to the known devices table so it has access to the network
    public static void giveDevicePermission(String Mac_Address){
        try{
            Connection c = getConnection();

            //SQL command to insert into the table
            String sql = "INSERT INTO Known_Devices(Mac_Address) VALUES ('" + Mac_Address + "')";
            PreparedStatement insert = c.prepareStatement(sql);
            //Execute the Update
            insert.executeUpdate();

            //Close the connection
            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Remove the device that is currently allowed from the known address
    public static void removeDevicePermission(String Mac_Address) {
        System.out.println("Removing permission for " + Mac_Address);

        //SQL command to delete the record from the table
        String sql = "DELETE FROM Known_Devices WHERE Mac_Address = \"" + Mac_Address + "\"";

        try {
            Connection c = getConnection();

            PreparedStatement delete = c.prepareStatement(sql);
            //Execute the deletion
            delete.executeUpdate();

            //close the connection
            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //First time user will create the table and if it already exsist then it won't
    public static void createTable(){
        try{
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Known_Devices(\n"
                    + "    Mac_Address VarChar(20) PRIMARY KEY,\n"
                    + "    IP_Add text ,\n"
                    + "    Device_Name text\n"
                    + ");";
            stmt.execute(sql);
            closeStatement(stmt);
            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //This will create the seen devices table
    public static void createSeenTable(){
        try{
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Seen_Devices(\n"
                    + "    Mac_Address VarChar(20) PRIMARY KEY,\n"
                    + "    IP_Add text ,\n"
                    + "    Device_Name text\n"
                    + ");";
            stmt.execute(sql);
            closeStatement(stmt);
            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //Delete the seen devices table
    public static void deleteSeenTable(){
        try{
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            String sql = "DROP TABLE Seen_Devices";
            stmt.execute(sql);
            closeStatement(stmt);
            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //Insert a new device into the seen table
    public static void insertIntoSeen(String Mac_Address){
        try{
            Connection c = getConnection();

            //SQL command to insert into the table
            String sql = "INSERT INTO Seen_Devices(Mac_Address) VALUES ('" + Mac_Address + "')";
            PreparedStatement insert = c.prepareStatement(sql);
            //Execute the Update
            insert.executeUpdate();

            //Close the connection
            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}