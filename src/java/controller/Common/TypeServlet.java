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
import model.Room;
import model.Type;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TypeServlet", urlPatterns = {"/type"})
public class TypeServlet extends HttpServlet {

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
            out.println("<title>Servlet TypeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TypeServlet at " + request.getContextPath() + "</h1>");
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
        int tid= Integer.parseInt(request.getParameter("id"));
        GeneralDAO gdb= new GeneralDAO();
        Type p= gdb.getTypeById(tid);
        List<Room> list= gdb.getRoonType(tid, null, "0");
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
        request.setAttribute("end", endPage);
        request.setAttribute("type", p);
        request.setAttribute("rooms", list);
        request.getRequestDispatcher("type.jsp").forward(request, response);
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
        int tid= Integer.parseInt(request.getParameter("id"));
        String search= request.getParameter("search");
        String orderBy= request.getParameter("orderBy");
        GeneralDAO gdb= new GeneralDAO();
        Type p= gdb.getTypeById(tid);
        List<Room> list= gdb.getRoonType(tid, search, orderBy);
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
        request.setAttribute("end", endPage);
        request.setAttribute("type", p);
        request.setAttribute("rooms", room);
        request.getRequestDispatcher("type.jsp").forward(request, response);
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
