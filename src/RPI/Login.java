/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPI;

import jdbc.IP;
import jdbc.MyConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.mail.Message;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jacobmenke
 */
public class Login extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            Scanner scanner =new Scanner(new FileInputStream(getServletContext().getRealPath("WEB-INF") + "/ip.txt"));
            String ip = scanner.next();
            String passwd = scanner.next();
            System.err.println("__________Class:" + Thread.currentThread().getStackTrace()[1].getClassName()+ "____Line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() +
            "___________ " + ip);
            new IP().setIP(ip);
            new IP().setPasswd(passwd);
        } catch (Exception e){
           System.err.println("__________Class:" + Thread.currentThread().getStackTrace()[1].getClassName()+ "____Line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() +
           "___________ " + e);
        }

        String uname = request.getParameter("uname");
        String pw = request.getParameter("pw");

        try (Connection connection = MyConnection.getConnection("root", "root", new IP().getPasswd())) {
            Statement statement = connection.createStatement();

            String passwordQuery = String.format("select * from dbUsers where username = '%s'", uname);
            System.out.println("query was " + passwordQuery);
            ResultSet rs = statement.executeQuery(passwordQuery);
            String dbPassword = "";
            String salt = "";

            if (rs.next()) {
                dbPassword = rs.getString("password");
                salt = rs.getString("salt");
            }

            if ((new HasherSalter().hashFunction(pw + salt)).equals(dbPassword)) {
                request.getSession().setAttribute("user", uname);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/app/directory.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Welcome.jsp");
                request.setAttribute("error", "bad_login!");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
