/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Common;

import dal.GeneralDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Place;
import model.Room;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PlaceServlet", urlPatterns = {"/place"})
public class PlaceServlet extends HttpServlet {

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
            out.println("<title>Servlet PlaceServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PlaceServlet at " + request.getContextPath() + "</h1>");
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
        int pid= Integer.parseInt(request.getParameter("id"));
        GeneralDAO gdb= new GeneralDAO();
        Place p= gdb.getPlaceById(pid);
        List<Room> list= gdb.getRoonPlace(pid, null, "0");
       String page_raw= request.getParameter("page");
        int page= Integer.parseInt(page_raw);
        int count= list.size();
        int pageNum=3;
        int endPage = count/pageNum;
        if(count%pageNum!=0){
            endPage +=1;
        }
        List<Room> room= new ArrayList<>();
        if(page*pageNum < count){
        for(int i= (page-1)*pageNum; i<page*pageNum; i++){
            room.add(list.get(i));
        }
        } else{
            for(int i= (page-1)*pageNum; i<count; i++){
            room.add(list.get(i));
        }
        }
        request.setAttribute("place", p);
        request.setAttribute("rooms", room);
       request.setAttribute("end", endPage);
       request.removeAttribute("key");
        request.getRequestDispatcher("place.jsp").forward(request, response);
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
        int pid= Integer.parseInt(request.getParameter("id"));
        String search= request.getParameter("search");
        String orderBy= request.getParameter("orderBy");
        GeneralDAO gdb= new GeneralDAO();
        Place p= gdb.getPlaceById(pid);
        List<Room> list= gdb.getRoonPlace(pid, search, orderBy);
        String page_raw= request.getParameter("page");
        int page= Integer.parseInt(page_raw);
        int count= list.size();
        int pageNum=3;
        int endPage = count/pageNum;
        if(count%pageNum!=0){
            endPage +=1;
        }
        List<Room> room= new ArrayList<>();
        if(page*pageNum < count){
        for(int i= (page-1)*pageNum; i<page*pageNum; i++){
            room.add(list.get(i));
        }
        } else{
            for(int i= (page-1)*pageNum; i<count; i++){
            room.add(list.get(i));
        }
        }
        request.setAttribute("place", p);
        request.setAttribute("rooms", room);
        request.setAttribute("end", endPage);
        request.setAttribute("key", search);
        request.setAttribute("sort", orderBy);
        request.getRequestDispatcher("place.jsp").forward(request, response);
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
