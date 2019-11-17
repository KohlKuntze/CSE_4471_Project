
package com.company.DataBase;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class SQLiteDB {

    private static Connection getConnection() {
        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:NetworkTraffic.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    private static void closeConnection(Connection c){
        try {
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Set<String> getPermittedDevices(){
        Set<String> permitted = new HashSet<>();
        try{
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("Select * from Known_Devices");

            while(rs.next()){
                String Mac_address = rs.getString("Mac_Address");
                String IP_Address = rs.getString("IP_Add");
                String Dev_Name = rs.getString("Device_Name");
                permitted.add(Mac_address);
                System.out.println(Mac_address+ "  "+IP_Address + "  " + Dev_Name );
            }

            closeConnection(c);

        } catch (Exception e){
            //System.out.print(e.getMessage());
        }
        return(permitted);
    }

    public static void giveDevicePermission(String Mac_Address){
        try{
            Connection c = getConnection();

            String sql = "INSERT INTO Known_Devices(Mac_Address) VALUES (" + Mac_Address + ")";
            PreparedStatement insert = c.prepareStatement(sql);
            insert.executeUpdate();

            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeDevicePermission(String Mac_Address) {
        String sql = "DELETE FROM Known_Devices WHERE Mac_Address = " + Mac_Address;

        try {
            Connection c = getConnection();

            PreparedStatement delete = c.prepareStatement(sql);
            delete.executeUpdate();

            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable(){
        /*
        try{
            Connection c = getConnection();
            Statement stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Known_Devices(\n"
                    + "    Mac_Address VarChar(20) PRIMARY KEY,\n"
                    + "    IP_Add text ,\n"
                    + "    Device_Name text\n"
                    + ");";
            stmt.execute(sql);

            closeConnection(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        */
    }
}