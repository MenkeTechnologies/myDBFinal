/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.IP;
import jdbc.MyConnection;

/**
 *
 * @author jacobmenke
 */
public class LearningCollectionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Connection connection;
            Statement statement;
            String errorMessage = "";

            try {

                connection = MyConnection.getConnection("root", "root", new IP().getPasswd());

                statement = connection.createStatement();

                if (statement != null) {

                    errorMessage = DB_LearningCollection.update(statement, request);
                    if (!errorMessage.equals("")) {
                        System.out.println("in the inner servlet error is " + errorMessage);
                    }

                    statement.close();
                }

            } catch (Exception e) {
                errorMessage = e.toString();
                System.out.println(e.getCause());

            }

            if (!errorMessage.equals("")) {
                System.out.println("in the servlet error is " + errorMessage);
            }

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/app/LearningCollection.jsp");
            dispatcher.forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
