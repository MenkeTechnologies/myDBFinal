/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LearningItem {

    String category;
    String learning;
    String dateAdded;
    int index;

    static String db_Name = "LearningCollection";

    public LearningItem(String category, String learning, String dateAdded, int index) {

        this.category = category;
        this.learning = learning;
        this.dateAdded = dateAdded;
        this.index = index;
    }
    
        public LearningItem(String category, String learning, String dateAdded) {
    
        this.category = category;
        this.learning = learning;
        this.dateAdded = dateAdded;
        this.index = -1;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LearningItem that = (LearningItem) o;

        if (index != that.index) {
            return false;
        }
        if (!category.equals(that.category)) {
            return false;
        }
        if (!learning.equals(that.learning)) {
            return false;
        }
        return dateAdded.equals(that.dateAdded);
    }

    @Override
    public int hashCode() {
        int result = category.hashCode();
        result = 31 * result + learning.hashCode();
        result = 31 * result + dateAdded.hashCode();
        result = 31 * result + index;
        return result;
    }

    @Override
    public String toString() {
        return "LearningItem{"
                + "category='" + category + '\''
                + ", learning='" + learning + '\''
                + ", dateAdded='" + dateAdded + '\''
                + ", index=" + index
                + '}';
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String update(int index, Statement statement) {

        if (learning.equals("")) {
            return "Learning Item cannot be empty";

        }

        String sql = "update " + db_Name + " set category=" + q_surround(category)
                + ", learning=" + q_surround(learning) + "where id=" + index;
        
        System.out.println("the update sql: " + sql);
        return executeUpdate(sql, statement);
    }
   
    public String insert(Statement statement) {

        // First find out if the book is already in the collection:
        String sql = "select * from " + db_Name + " where category=" + q_surround(category)
                + " AND learning=" + q_surround(learning);
        
        System.out.println(sql);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return "Learning Item already exists";
            }
        } catch (SQLException e) {
            return e.toString();
        }

        // insert into LearningCollection values('Programming', 'CSS background-size: cover', NOW(),0);
        sql = "insert into " + db_Name + " values(" + q_surround(category) + ","
                + q_surround(learning) + ",NOW(), null)";
        return executeUpdate(sql, statement);
    }

    // Note index =-1 will delete all rows
    public static String remove(int index, Statement statement) {
        String sql = "delete from " + db_Name;
        if (index >= 0) {
            sql += " where id=" + index;
        }

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

    public static String getLearningItems(Statement statement, ArrayList<LearningItem> learningItemsArrayList) {
        String error = "";
        try {
            String sql = "select * from " + db_Name;
            System.out.println("sql=" + sql);
            ResultSet rs = statement.executeQuery(sql);
            learningItemsArrayList.clear();
            while (rs.next()) {
                String category = rs.getString("category");

                String learningString = rs.getString("learning");
                String dateAdded = rs.getString("dateAdded");
                int ind = rs.getInt("id");
                LearningItem newLearningItem = new LearningItem(category, learningString, dateAdded, ind);

                learningItemsArrayList.add(newLearningItem);

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
