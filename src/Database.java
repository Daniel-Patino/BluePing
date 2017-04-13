import java.sql.*;
import java.util.*;

public class Database {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    private String url = null;
    private String user = null;
    private String password = null;

    public Database(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() throws Exception {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	public ArrayList<String> runStringQuery(String query) throws Exception {
        ArrayList<String> returnedValues = new ArrayList<>();

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                returnedValues.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return returnedValues;
    }

	public ArrayList<Integer> runIntQuery (String query) throws Exception {
        ArrayList<Integer> returnedValues = new ArrayList<>();

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                returnedValues.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return returnedValues;
    }

    public void prepareQuery (String query) throws Exception {
        try {
            pst = con.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setQueryId (int id) throws Exception {
        try {
            pst.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setQueryId (String id) throws Exception {
        try {
            pst.setString(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String runStringPrepQuery() throws Exception {
        String returnedValue = null;

        try {
            rs = pst.executeQuery();

            if (rs.next()) {
                returnedValue = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnedValue;
    }

	public int runIntPrepQuery() throws Exception {
        int returnedValue = 0;

        try {
            rs = pst.executeQuery();

            if (rs.next()) {
                returnedValue = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return returnedValue;

    }

	public int count() throws Exception {
        int returnedValue = 0;

        try {
            pst = con.prepareStatement("SELECT COUNT (*) FROM beacon1");
            rs = pst.executeQuery();

            if (rs.next()) {
                returnedValue = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return returnedValue;
    }

    public void returnAll() throws Exception {
        try {
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement("SELECT * FROM beacon1");
            rs = pst.executeQuery();

            System.out.println("Beacon1:");

            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.print(rs.getString(2));
                System.out.print(": ");
                System.out.print(rs.getInt(3));
                System.out.print(": ");
                System.out.println(rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close() throws Exception {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
