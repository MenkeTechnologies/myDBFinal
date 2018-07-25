/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPI;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.nio.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author jacobmenke
 */

@MultipartConfig
public class Upload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String uploadedDirectory = "/home/pi/Uploads";
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        System.out.println("the FileName was " + fileName);
        String resultString = "";

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadedDirectory + "/" + fileName)),1_000_000);
                BufferedInputStream in = new BufferedInputStream(filePart.getInputStream(), 1_000_000)) {

            int read = 0;
            final byte[] bytes = new byte[8192];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            resultString = "New File Created at " + uploadedDirectory + "/" + fileName;

        } catch (Exception e) {

            resultString = "Did You Choose a FILE?: ";

        }

        request.setAttribute("result", resultString);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/app/upload.jsp");
        requestDispatcher.forward(request, response);

    }

    private String getFileName(final Part part) {

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
