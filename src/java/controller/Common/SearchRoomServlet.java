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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Place;
import model.Room;
import model.Type;

/**
 *
 * @author ADMIN
 */
public class SearchRoomServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchRoomServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchRoomServlet at " + request.getContextPath() + "</h1>");
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
        GeneralDAO gdb= new GeneralDAO();
        ArrayList<Place> plas= gdb.getAllPlace();
        ArrayList<Type> tys= gdb.getAllType();
        String placeId= request.getParameter("place");
        String typeId= request.getParameter("type");
        String numBed= request.getParameter("numBed");
        String page_raw= request.getParameter("page");
        int page= Integer.parseInt(page_raw);
        List<Room> list= gdb.searchRoom(placeId, typeId, numBed, null, "0");
        int count= list.size();
        int pageNum=6;
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
        request.setAttribute("place", placeId);
        request.setAttribute("type", typeId);
        request.setAttribute("bedNum", numBed);
        request.setAttribute("types", tys);
        request.setAttribute("places", plas);
        request.setAttribute("end", endPage);
        request.setAttribute("rooms", room);
        request.getRequestDispatcher("search.jsp").forward(request, response);
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
        GeneralDAO gdb= new GeneralDAO();
        ArrayList<Place> plas= gdb.getAllPlace();
        ArrayList<Type> tys= gdb.getAllType();
        String placeId= request.getParameter("place");
        String typeId= request.getParameter("type");
        String numBed= request.getParameter("numBed");
        String page_raw= request.getParameter("page");
        int page= Integer.parseInt(page_raw);
        String search= request.getParameter("search");
        String sort= request.getParameter("orderBy");
        List<Room> list= gdb.searchRoom(placeId, typeId, numBed,search, sort );
        int count= list.size();
        int pageNum=6;
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
        request.setAttribute("place", placeId);
        request.setAttribute("type", typeId);
        request.setAttribute("bedNum", numBed);
        request.setAttribute("types", tys);
        request.setAttribute("places", plas);
        request.setAttribute("end", endPage);
        request.setAttribute("rooms", room);
        request.getRequestDispatcher("search.jsp").forward(request, response);
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
