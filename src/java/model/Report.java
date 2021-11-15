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
public class Report {
    private int reportId;
    private String content;
    private String purpose;
    private int uid;
    private String response;

    public Report() {
    }

    public Report(int reportId, String content, String purpose, int uid, String response) {
        this.reportId = reportId;
        this.content = content;
        this.purpose = purpose;
        this.uid = uid;
        this.response = response;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", content=" + content + ", purpose=" + purpose + ", uid=" + uid + ", response=" + response + '}';
    }
    
    
}
