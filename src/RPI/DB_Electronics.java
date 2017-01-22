package RPI;

/**
 * Created by jacobmenke on 11/6/16.
 */
import java.sql.*;
import java.util.ArrayList;

public class DB_Electronics {
    String name;
    String onFilePath;
    String offFilePath;
    String dateAdded;
    String program;
    int index;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DB_Electronics that = (DB_Electronics) o;

        if (index != that.index) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (onFilePath != null ? !onFilePath.equals(that.onFilePath) : that.onFilePath != null) return false;
        if (offFilePath != null ? !offFilePath.equals(that.offFilePath) : that.offFilePath != null) return false;
        return dateAdded != null ? dateAdded.equals(that.dateAdded) : that.dateAdded == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (onFilePath != null ? onFilePath.hashCode() : 0);
        result = 31 * result + (offFilePath != null ? offFilePath.hashCode() : 0);
        result = 31 * result + (dateAdded != null ? dateAdded.hashCode() : 0);
        result = 31 * result + index;
        return result;


    }

    public DB_Electronics(String name, String onFilePath, String offFilePath, String dateAdded, int index) {
        this.name = name;
        this.onFilePath = onFilePath;
        this.offFilePath = offFilePath;
        this.dateAdded = dateAdded;
        this.index = index;
        
    }

    public DB_Electronics(String name, String onFilePath, String offFilePath, String dateAdded) {
        this(name, onFilePath, offFilePath, dateAdded, -1);
    }
    
     @Override
    public String toString() {
        return "DB_Electronics{" +
                "name='" + name + '\'' +
                ", onFilePath='" + onFilePath + '\'' +
                ", offFilePath='" + offFilePath + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", index=" + index +
                '}';
    }

    public String getName() {
        return name;
    }
    
       public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnFilePath() {
        return onFilePath;
    }

    public void setOnFilePath(String onFilePath) {
        this.onFilePath = onFilePath;
    }

    public String getOffFilePath() {
        return offFilePath;
    }

    public void setOffFilePath(String offFilePath) {
        this.offFilePath = offFilePath;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }


    public String update(int index, Statement statement) {

        if (name.equals("")) {
            return "Name cannot be empty";

        }

        String sql = "update ElectronicsCollection set name=" + q_surround(name)
                + ", onFile=" + q_surround(onFilePath) + ", offFile=" + q_surround(offFilePath)
                + " where id=" + index;
        return executeUpdate(sql, statement);
    }

    public String insert(Statement statement) {

        // First find out if the book is already in the collection:
        String sql = "select * from ElectronicsCollection where name=" + q_surround(name)
                + " AND onFile=" + q_surround(onFilePath) + " AND offFile=" + q_surround(offFilePath);
        
       
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return "Device already exists";
            }
        } catch (SQLException e) {
            return e.toString();
        }

        sql = "insert into ElectronicsCollection values(" + q_surround(name) + ","
                + q_surround(onFilePath) + "," + q_surround(offFilePath) + ", NOW()" + ", null)";
        return executeUpdate(sql, statement);
    }

    // Note index =-1 will delete all rows
    public static String remove(int index, Statement statement) {
        String sql = "delete from ElectronicsCollection ";
        if (index >= 0) {
            sql += " where id=" + index;
        }
        System.out.println("clearList " + sql);
        return executeUpdate(sql, statement);
    }

    private static String executeUpdate(String sql, Statement statement) {
        String error = "";
        try {
            System.out.println("sql=" + sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            error = e.toString();
        }
        return error;
    }

    public static String getDevices(Statement statement, ArrayList<DB_Electronics> electronicDevices) {
        String error = "";
        try {
            String sql = "select * from ElectronicsCollection";
            System.out.println("sql=" + sql);
            ResultSet rs = statement.executeQuery(sql);
            electronicDevices.clear();
            while (rs.next()) {
                String name = rs.getString("name");
                
                String onFilePath = rs.getString("onFile");
                String offFilePath = rs.getString("offFile");
                String dateAdded = rs.getString("dateAdded");
                int ind = rs.getInt("id");
                DB_Electronics newDevice = new DB_Electronics(name, onFilePath, offFilePath, dateAdded, ind);

                electronicDevices.add(newDevice);
               
            }
        } catch (SQLException ex) {
            error = ex.toString();
        }
        return error;
    }

    // Surround with single quote
    private String q_surround(String s) {
        return "\'" + s + "\'";
    }

}
