/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.AdminServlet;

import dal.BlockDAO;
import dal.DAO;
import dal.MailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Mail;

/**
 *
 * @author MY LAPTOP
 */
@WebServlet(name = "editUser1Servlet", urlPatterns = {"/edituser"})
public class editUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editUser1Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editUser1Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);
        DAO db = new DAO();
        Account a = db.getAccountByID(id);
        String role;
        if (a.isIsAdmin()) {
            request.setAttribute("role", "Admin");
        }
        if (a.isIsBooker()) {
            request.setAttribute("role", "Booker");
        }
        if (a.isIsOwner()) {
            request.setAttribute("role", "Owner");
        }
        if (a.isBlock()) {
            request.setAttribute("block", "Block");
        } else {
            request.setAttribute("block", "No");
        }
        request.setAttribute("user", a);
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
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
        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);
        DAO db = new DAO();
        String role = request.getParameter("role");
        String submit = request.getParameter("submit");
        if (submit.equalsIgnoreCase("Update")) {
            if (db.editAdmin(id, role) != 0) {
                Account u = db.getAccountByID(id);
                if (u.isIsAdmin()) {
                    request.setAttribute("role", "Admin");
                }
                if (u.isIsBooker()) {
                    request.setAttribute("role", "Booker");
                }
                if (u.isIsOwner()) {
                    request.setAttribute("role", "Owner");
                }
                if (u.isBlock()) {
                    request.setAttribute("block", "Block");
                } else {
                    request.setAttribute("block", "No");
                }
                request.setAttribute("user", u);
                request.setAttribute("error", "Update suscessfully!");
                request.getRequestDispatcher("editUser.jsp").forward(request, response);
            } else {
                Account u = db.getAccountByID(id);
                if (u.isIsAdmin()) {
                    request.setAttribute("role", "Admin");
                }
                if (u.isIsBooker()) {
                    request.setAttribute("role", "Booker");
                }
                if (u.isIsOwner()) {
                    request.setAttribute("role", "Owner");
                }
                if (u.isBlock()) {
                    request.setAttribute("block", "Block");
                } else {
                    request.setAttribute("block", "No");
                }
                request.setAttribute("user", u);
                request.setAttribute("error", "Update false!");
                request.getRequestDispatcher("editUser.jsp").forward(request, response);
            }
        } else if (submit.equalsIgnoreCase("Block")) {
            String emailTo = "anhtuan161162@gmail.com";

            MailDAO mdb = new MailDAO();
            Mail reset = mdb.getMailById(1);
            //dang nhap email
            final String username = reset.getFrom();
            final String pass = reset.getPassword();
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            Session session;
            session = Session.getInstance(prop, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, pass);
                }
            });
            //gui email
            String emailSubject = reset.getSubject();
            String emailContent = reset.getContent();

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(emailTo));
                message.setSubject(emailSubject);
                message.setText(emailContent);
                Transport.send(message);
            } catch (Exception e) {

            }
            BlockDAO bdb = new BlockDAO();
            bdb.BlockUser(id, "block");
            request.setAttribute("error", "Block successfull!");
            response.sendRedirect("edituser?id=" + id);
        } else {
            String emailTo = "anhtuan161162@gmail.com";
            MailDAO mdb = new MailDAO();
            Mail mail = mdb.getMailById(2);
            //dang nhap email
             final String username = mail.getFrom();
            final String pass = mail.getPassword();
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            Session session;
            session = Session.getInstance(prop, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, pass);
                }
            });
            //gui email
            String emailSubject = mail.getSubject();
            String emailContent = mail.getContent();

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(emailTo));
                message.setSubject(emailSubject);
                message.setText(emailContent);
                Transport.send(message);
            } catch (Exception e) {

            }
            BlockDAO bdb = new BlockDAO();
            bdb.BlockUser(id, "unblock");
            request.setAttribute("error", "Unblock successfull!");
            response.sendRedirect("edituser?id=" + id);
        }
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
