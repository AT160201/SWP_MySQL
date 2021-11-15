/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dal.*;
import java.sql.Date;
import model.Room;

/**
 *
 * @author ADMIN
 */
public class Booking {

    private int bookingID;
    private int cusID;
    private Room room;
    private String date;
    private String checkin;
    private String checkout;
    private double total;
    private String status;

    public Booking() {
    }

    public Booking(int cusID, Room room, String date, String checkin, String checkout, double total, String status) {

        this.cusID = cusID;
        this.room = room;
        this.date = date;
        this.checkin = checkin;
        this.checkout = checkout;
        this.total = total;
        this.status = status;
    }

    public Booking(int cusID, Room room, String date, String checkin, String checkout, String status) {

        this.cusID = cusID;
        this.room = room;
        this.date = date;
        this.checkin = checkin;
        this.checkout = checkout;

        this.status = status;
    }

    public Booking(int bookingID, int cusID, Room room, String checkin, String checkout, double total, String status) {
        this.bookingID= bookingID;
        this.cusID = cusID;
        this.room = room;
        this.checkin = checkin;
        this.checkout = checkout;
        this.total = total;
        this.status = status;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
