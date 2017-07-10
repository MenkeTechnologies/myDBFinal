/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPI;

import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jacobmenke
 */
public class DB_LearningCollection {
    
    static String errorMessage = "";

    public static String update(Statement statement, HttpServletRequest request) {

        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("clear list")) {
                errorMessage = LearningItem.remove(-1, statement);
                return errorMessage;

            }
            
            String category = request.getParameter("category");
          
            String unescapedLearning = request.getParameter("learning");
            
            //mysql wont accept single quotes unless escaped
            String learning = unescapedLearning.replaceAll("'", "\\\\'");
            
            
            System.out.println("I am here and the the learning param is " + learning);
            
            String dateAdded = "NOW()";

            LearningItem learningItem = new LearningItem(category, learning, dateAdded);

            String strIndex;
            int index;
            String path;

            switch (action) {

                case "add":
                    
                    if (learning.equals("")) {
                        errorMessage = "Learning Item cannot be empty.";
                    } else {
                        errorMessage = learningItem.insert(statement);
                    }
                    break;
                case "Remove":
                    System.out.println("the category is " + category);
                    strIndex = request.getParameter("index");
                    
                    index = Integer.parseInt(strIndex);
                    System.out.println("From the remove the index is " + strIndex);
                    errorMessage = LearningItem.remove(index, statement);
                    break;
                case "Update":

                    strIndex = request.getParameter("index");
                    index = Integer.parseInt(strIndex);
                    errorMessage = learningItem.update(index, statement);

                    break;

            }

        }

        ArrayList<LearningItem> learningCollection = new ArrayList<>();
        errorMessage += LearningItem.getLearningItems(statement, learningCollection);
        
        if(action != null && action.equals("updateAll")){
            
            //use jquery to submit the form and action=updateAll and pass in all the values of inputs that have changed, use jquery blur or keyboard detection functions
            //servlet takes the array of indexes of call learningitem.update for each one
//            learningCollection.forEach(item->{
//                errorMessage += item.update(item.index, statement);
//                
//            });
        }
        
        Collections.reverse(learningCollection);
        
       
        request.setAttribute("count", learningCollection.size());
        
        request.setAttribute("LearningCollection", learningCollection);

        return errorMessage;

    }

}
