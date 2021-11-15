/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.AdminServlet;

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
import model.Place;
import model.Room;
import model.Type;

/**
 *
 * @author MY LAPTOP
 */
@WebServlet(name = "roomDetailServlet", urlPatterns = {"/roomdetail"})
public class roomDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet roomDetailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet roomDetailServlet at " + request.getContextPath() + "</h1>");
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
        String raw_id= request.getParameter("rid");
        int rid= Integer.parseInt(raw_id);
        roomDAO db= new roomDAO();
        Room r= db.getRoomByID(rid);
        GeneralDAO dao = new GeneralDAO();
        List<Place> listP = dao.getAllPlace();
        List<Type> listT = dao.getAllType();
        
        request.setAttribute("room", r);
        request.setAttribute("listP", listP);
        request.setAttribute("listT", listT);
        request.getRequestDispatcher("roomDetailAdmin.jsp").forward(request, response);
        
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
        String submit = request.getParameter("submit");
        if(submit.equalsIgnoreCase("reset")){
            String raw_id= request.getParameter("rid");
            int rid= Integer.parseInt(raw_id);
            roomDAO db= new roomDAO();
            Room r= db.getRoomByID(rid);
            request.setAttribute("room", r);
            request.getRequestDispatcher("roomDetailAdmin.jsp").forward(request, response);
        }else{           
            String raw_id= request.getParameter("rid");
            int rid= Integer.parseInt(raw_id);
            String rName = request.getParameter("rname");
            String desc = request.getParameter("desc");
            String owner = request.getParameter("owner");
            String status_raw = request.getParameter("status");
            Boolean status= true;
            if(status_raw.equalsIgnoreCase("0")){
                status=false;
            }
            String area = request.getParameter("area");
            String bednum = request.getParameter("bednum");
            String price = request.getParameter("price");
            String rating = request.getParameter("rating");
            String placeId = request.getParameter("placeId");
            String typeId = request.getParameter("typeId");
            roomDAO dao = new roomDAO();
            dao.updateRoom(rName, desc, owner, status, area, bednum, price, rating, placeId, typeId, rid);           
            response.sendRedirect("roomdetail?rid="+rid);
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
