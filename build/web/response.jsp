<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Admin Page</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>
    <body>
        <div id="responseModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="response" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Response</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Report ID</label>
                                <input value="${report.reportId}" readonly name="reportId" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>User ID</label>
                                <input value="${report.uid}" readonly name="userId" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Purpose</label>
                                <input value="${report.purpose}" readonly name="purpose" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Response</label>
                                <textarea name="responseUser" class="form-control" required>${report.response}</textarea>
                            </div>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Response">
                            </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
