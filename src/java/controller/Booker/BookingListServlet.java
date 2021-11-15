/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Booker;

import dal.BookerDAO;
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
import model.Account;
import model.Booking;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "BookingListServlet", urlPatterns = {"/booking_list"})
public class BookingListServlet extends HttpServlet {

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
            out.println("<title>Servlet BookingListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingListServlet at " + request.getContextPath() + "</h1>");
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
        String raw_page = request.getParameter("page");
        int page = Integer.parseInt(raw_page);
       HttpSession session = request.getSession();
        Object o = session.getAttribute("acc");
        Account a = null;
       if (o != null) {
           a = (Account) o;
        }
        BookerDAO bdb= new BookerDAO();
        List<Booking> list= bdb.getAllBooking(a.getId(), null, "0", "0");
        int count= list.size();
        int pageNum=6;
        int endPage = count/pageNum;
        if(count%pageNum!=0){
            endPage +=1;
        }
        List<Booking> booking= new ArrayList<>();
        if(page*pageNum < count){
        for(int i= (page-1)*pageNum; i<page*pageNum; i++){
            booking.add(list.get(i));
        }
        } else{
            for(int i= (page-1)*pageNum; i<count; i++){
            booking.add(list.get(i));
        }
        }
        request.setAttribute("end", endPage);
        request.setAttribute("bookings", list);
        request.getRequestDispatcher("bookingList.jsp").forward(request, response);
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
        String raw_page = request.getParameter("page");
        int page = Integer.parseInt(raw_page);
        
       HttpSession session = request.getSession();
        Object o = session.getAttribute("acc");
        Account a = null;
       if (o != null) {
           a = (Account) o;
        }
        String search= request.getParameter("search");
        String status= request.getParameter("status");
        String orderBy= request.getParameter("orderBy");
        BookerDAO bdb= new BookerDAO();
        List<Booking> list= bdb.getAllBooking(a.getId(), search, status, orderBy);
        int count= list.size();
        int pageNum=6;
        int endPage = count/pageNum;
        if(count%pageNum!=0){
            endPage +=1;
        }
        List<Booking> booking= new ArrayList<>();
        if(page*pageNum < count){
        for(int i= (page-1)*pageNum; i<page*pageNum; i++){
            booking.add(list.get(i));
        }
        } else{
            for(int i= (page-1)*pageNum; i<count; i++){
            booking.add(list.get(i));
        }
        }
        request.setAttribute("end", endPage);
        request.setAttribute("search", search);
        request.setAttribute("bookings", list);
        request.getRequestDispatcher("bookingList.jsp").forward(request, response);
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
