/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.AdminServlet;

import dal.MailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mail;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MailServlet", urlPatterns = {"/mail"})
public class MailServlet extends HttpServlet {

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
            out.println("<title>Servlet MailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MailServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        session.removeAttribute("mess");
        int id = Integer.parseInt(request.getParameter("id"));
        MailDAO mdb = new MailDAO();
        List<Mail> list = mdb.getAllMail();
        Mail m = mdb.getMailById(id);
        request.setAttribute("m", m);
        request.setAttribute("mails", list);
        request.getRequestDispatcher("Mail.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String from = request.getParameter("from");
        String pass = request.getParameter("password");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        Mail e = new Mail(id, name, from, pass, subject, content);
        request.setAttribute("email", e);
        MailDAO mdb = new MailDAO();
        if(mdb.isValidEmail(from)== false){
            session.removeAttribute("mess");
            session.setAttribute("mess", "Email không hợp lệ");
        } else if(pass.trim()== "" || pass==null){
            session.removeAttribute("mess");
            session.setAttribute("mess", "Pass not null");
        }else if(subject.trim()== "" || subject==null){
            session.removeAttribute("mess");
            session.setAttribute("mess", "Subject not null");
        }else if(content.trim()== "" || content==null){
            session.removeAttribute("mess");
            session.setAttribute("mess", "Content not null");
        }else if (mdb.EditEmail(e) > 0) {
            session.removeAttribute("mess");
            session.setAttribute("mess", "Update successfull!");
        } else {
            session.removeAttribute("mess");
            session.setAttribute("mess", "Update fail!");
        }
        List<Mail> list = mdb.getAllMail();
            request.setAttribute("mails", list);
            Mail m = mdb.getMailById(id);
            request.setAttribute("m", m);
            request.getRequestDispatcher("Mail.jsp").forward(request, response);
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
