/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MY LAPTOP
 */
public class Room {
    private int rid;
    private String rName;
    private String desc;
    private String picture;
    private int ownerId;
    private boolean status;
    private int area;
    private int bedNum;
    private double price;
    private float rating;
    private int placeId;
    private int typeId;

    public Room() {
    }

    public Room(int rid, String rName, String desc, String picture, int ownerId, boolean status, int area, int bedNum, double price, float rating, int placeId, int typeId) {
        this.rid = rid;
        this.rName = rName;
        this.desc = desc;
        this.picture = picture;
        this.ownerId = ownerId;
        this.status = status;
        this.area = area;
        this.bedNum = bedNum;
        this.price = price;
        this.rating = rating;
        this.placeId = placeId;
        this.typeId = typeId;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Room{" + "rid=" + rid + ", rName=" + rName + ", desc=" + desc + ", picture=" + picture + ", ownerId=" + ownerId + ", status=" + status + ", area=" + area + ", bedNum=" + bedNum + ", price=" + price + ", rating=" + rating + ", placeId=" + placeId + ", typeId=" + typeId + '}';
    }

    
    
    
}
