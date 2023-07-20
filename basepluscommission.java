package DB;

import java.sql.*;

public class BasePlusCommission {

    public static void show() {
        {
            Connection conn = null;
            try {
                conn = DBConnection.getConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            PreparedStatement stmt;
            try {
                stmt = conn.prepareStatement("SELECT * FROM baseplus");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ResultSet rs;
            try {
                rs = stmt.executeQuery();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            int id = 1;
            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String fname = null;
                String lname = null;
                int ssn;
                Double rate = null;
                Double baseSalary;
                Double earning;
                int sales;
                try {
                    fname = rs.getString("fname");
                    lname = rs.getString("lname");
                    ssn = rs.getInt("ssn");
                    baseSalary = rs.getDouble("baseSalary");
                    sales = rs.getInt("sales");
                    rate = rs.getDouble("rate");
                    earning = rs.getDouble("earning");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("ID: "+ id + "\nFirst Name: " + fname + "\nLast Name: " + lname + "\nSocial Security No: " + ssn + "\nBase Salary: " + baseSalary  + "\nSales: " + sales + "\nCommission Rate :" + rate + "\nEarning :" + earning );
                id++;
            }
        }

    }

    public static void add(String fn, String ln, int sn, double baseSalary, int sales, double rate,double earning){
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            stm.executeUpdate("INSERT INTO baseplus (fname, lname, ssn, baseSalary, sales, rate, earning) " +
                    "VALUES ('" + fn + "', '" + ln + "', '" + sn + " ', '" + baseSalary +" ', '" + sales + "', '" + rate + "', '" + earning +"')");
            System.out.println("Base + Commission Employee created successfully!");

        } catch (SQLException e) {
            System.out.println("Err" + e);
        }
    }

    public static void update(String fn, String ln, int sn, Double baseSalary, int sales, double rate){
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement("UPDATE baseplus SET fname=?, lname=?, baseSalary=?, sales=?, rate=? WHERE ssn=? ");
            stm.setString(1, fn);
            stm.setString(2, ln);
            stm.setInt(3,sn);
            stm.setDouble(4,baseSalary);
            stm.setInt(5,sales);
            stm.setDouble(6,rate);

            int rows = stm.executeUpdate();
            if (rows > 0) {
                System.out.println("Base + Commission Employee Updated successfully!");
            }
            else{
                System.out.println("Update Failed");
            }

        } catch (SQLException e) {
            System.out.println("Err" + e);
        }
    }
    public static void delete(int sn2) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String query = "DELETE FROM baseplus WHERE ssn='" + sn2 + "'";
            int row = stm.executeUpdate(query);
            if (row > 0) {
                System.out.println("Base + Commission Employee Deleted successfully!");
            } else {
                System.out.println("Deletion Failed");
            }
        } catch (SQLException e) {
            System.out.println("Err" + e);
        }
    }
}
