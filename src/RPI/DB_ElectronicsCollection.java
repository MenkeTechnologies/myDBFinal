package RPI;

import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jacobmenke
 */
public class DB_ElectronicsCollection {

    static String errorMessage = "";

    public static String update(Statement statement, HttpServletRequest request) {

        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("clear list")) {
                errorMessage = DB_Electronics.remove(-1, statement);
                return errorMessage;

            }

            String name = request.getParameter("name");
            String onFilePath = request.getParameter("onFilePath");
            String offFilePath = request.getParameter("offFilePath");
            String program = request.getParameter("program");
            
//            String dateAdded = request.getParameter("dateAdded");
            String dateAdded = "NOW()";

            DB_Electronics DB_Device = new DB_Electronics(name, onFilePath, offFilePath, dateAdded);

            String strIndex;
            int index;
            String path;

            switch (action) {

                case "add":
                    if (name.equals("")) {
                        errorMessage = "Name cannot be empty.";
                    } else {
                        errorMessage = DB_Device.insert(statement);

                    }
                    break;
                case "Remove":
                    System.out.println("the name is " + name);
                    strIndex = request.getParameter("index");
                    index = Integer.parseInt(strIndex);
                    System.out.println("From the remove the index is " + strIndex);
                    errorMessage = DB_Electronics.remove(index, statement);
                    break;
                case "Update":

                    strIndex = request.getParameter("index");
                    index = Integer.parseInt(strIndex);
                    errorMessage = DB_Device.update(index, statement);

                    break;

                case "TurnOn":

                    path = request.getParameter("onFilePath");
//                    System.out.println("path is" + path);
                    errorMessage = runExecutable(path);
                    break;

                case "TurnOff":
                    path = request.getParameter("offFilePath");
                    errorMessage = runExecutable(path);
                    break;

            }

        }

        ArrayList<DB_Electronics> electronicsCollection = new ArrayList<>();
        errorMessage += DB_Electronics.getDevices(statement, electronicsCollection);
        request.setAttribute("ElectronicsCollection", electronicsCollection);

        return errorMessage;

    }

    static String runExecutable(String path) {

        String executor = "python3";

//        Thread t = new Thread(() -> {

            ProcessBuilder pb = new ProcessBuilder(executor, path, "2>", "/dev/null");
            System.out.println("in the exectutor " + path);
            try {
                Process p = pb.start();
                Scanner scan = new Scanner(p.getInputStream());
                while (scan.hasNextLine()){
                    errorMessage = scan.nextLine();
                    
                }
                System.out.println(errorMessage);
            } catch (IOException e) {
                errorMessage = e.toString();

            }
//        });
//
//        t.start();

        return errorMessage;

    }

}
