/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Owner;

import dal.GeneralDAO;
import dal.roomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Place;
import model.Room;
import model.Type;

/**
 *
 * @author MY LAPTOP
 */
@WebServlet(name = "addRoomServlet", urlPatterns = {"/addroom"})
public class addRoomServlet extends HttpServlet {

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
            out.println("<title>Servlet addRoomServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addRoomServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");  
        GeneralDAO dao = new GeneralDAO();
        List<Place> listP = dao.getAllPlace();
        List<Type> listT = dao.getAllType();
        
        request.setAttribute("listP", listP);
        request.setAttribute("listT", listT);
        request.getRequestDispatcher("roomOwner.jsp").forward(request, response);
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");       
            String rName = request.getParameter("rname");
            String desc = request.getParameter("desc");
            String pic = request.getParameter("picture");
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("acc");
            int oid = a.getId();
            String status_raw = request.getParameter("status");
            Boolean status= true;
            if(status_raw.equalsIgnoreCase("0")){
                status=false;
            }
            String area = request.getParameter("area");
            String bednum = request.getParameter("bednum");
            String price = request.getParameter("price");
            String placeId = request.getParameter("placeId");
            String typeId = request.getParameter("typeId");
            roomDAO dao = new roomDAO();
            dao.addRoom(rName, desc, pic, oid, status, area, bednum, price, placeId, typeId);
            response.sendRedirect("ownerroom");
            
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
