<%-- 
    Document   : detail
    Created on : Jul 10, 2021, 10:06:24 AM
    Author     : TrungLT 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking</title>
        <!-- for-mobile-apps -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Resort Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //for-mobile-apps -->
        <link href="la/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <!-- pop-up -->
        <link rel="stylesheet" href="la/css/lightbox.css">
        <!-- //pop-up -->
        <link rel="stylesheet" href="la/css/flexslider.css" type="text/css" media="screen" property="" />

        <link rel="stylesheet" type="text/css" href="la/css/zoomslider.css" />
        <link rel="stylesheet" type="text/css" href="la/css/style.css" />
        <link href="la/css/font-awesome.css" rel="stylesheet">
        <script type="text/javascript" src="la/js/modernizr-2.6.2.min.js"></script>
        <!--/web-fonts-->
        <link href="//fonts.googleapis.com/css?family=Dosis:200,300,400,500,600" rel="stylesheet">
        <link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i" rel="stylesheet">
        <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
        <!--//web-fonts-->
        <style>
            html,
            body {
                height: 100%;
                margin: 0;
                padding: 0;
            }

            #map {
                height: 50%;
                width: 100%;
            }

            #floating-panel {
                position: absolute;
                z-index: 5;
                padding: 5px;
                text-align: center;
                line-height: 30px;
                padding-left: 10px;
            }

            #ab{
                background-color: #f5f5f5;
                padding-top: 60px;
            }

            #ab p span{
                font-weight: bold;
                color: #ff401b;
            }

            input[type=number]{
                background: rgba(0, 0, 0, 0);
                border: solid 2px #ffffff;
                width: 293px;
                height: 35px;
                color: white;
            }

            input[type=text]::placeholder{
                color: white;
            }
            
            iframe{
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <!-- details -->
        <c:set value="${room}" var="r"/>
        <div class="about-bottom" id="ab">
            <div class="col-md-6 w3l_about_bottom_right two">
                <h3 class="tittle why">${room.rName}</h3>
                <p><span>Description:</span> ${room.desc}</p>
                <iframe src="https://calendar.google.com/calendar/embed?height=400&amp;wkst=2&amp;bgcolor=%23F4511E&amp;ctz=Asia%2FHo_Chi_Minh&amp;src=dmkudmlldG5hbWVzZSNob2xpZGF5QGdyb3VwLnYuY2FsZW5kYXIuZ29vZ2xlLmNvbQ&amp;color=%230B8043&amp;showNav=1&amp;showPrint=0&amp;showCalendars=0&amp;hl=en&amp;title=Vietnam%20Holidays" style="border-width:0" width="600" height="400" frameborder="0" scrolling="no"></iframe>
            </div>

            <div class="col-md-6 w3l_about_bottom_left">    
                <img src="rhea/public/assets/img/gallery/${room.picture}" alt="homestay-img" class="img-responsive" />
                <div class="w3l_about_bottom_left_video book-text">
                    <!--<h4>${room.price}$/night</h4>-->
                    <div class="book-form">
                        <h4>${room.price}$<span style="font-size:15px">/night</span></h4>
                        <form action="book" method="post" name="book" onsubmit="return checkDate(this)">
                            <input type="hidden" value="${room.rid}" name="id">
                            <div class="col-md-6 form-date-w3-agileits">
                                <label><i class="fa fa-calendar" aria-hidden="true"></i>Arrival Date :</label>
                                <input name="datefrom" type="date" required="" style="color: white;">
                            </div>
                            <div class="col-md-6 form-date-w3-agileits">
                                <label><i class="fa fa-calendar" aria-hidden="true"></i>Departure Date :</label>
                                <input name="dateto" type="date" required="" style="color: white;">
                            </div>
                            <div class="col-md-6 form-date-w3-agileits">
                                <label><i class="fa fa-users" aria-hidden="true"></i> No.of People :</label>
                            </div>
                            <div class="clearfix"> </div>
                            <div class="make wow shake" data-wow-duration="1s" data-wow-delay=".5s">
                                <input type="submit" value="Book Now">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <!-- //details -->


        <div id="floating-panel">
            <h3 id="address" style="color:white; margin-top: 30px">${h.address}</h3>
        </div>
        <div id="map"></div>

        <!--footer-->
        <!--//footer-->

        <script language="JavaScript" type="text/javascript">
            function checkDate(book) {
                var currDate = new Date();
                var arrDate = new Date(book.datefrom.value);
                var depDate = new Date(book.dateto.value);

                if (arrDate.getTime() < currDate.getTime() || depDate.getTime() < currDate.getTime()) {
                    alert("The date should be after the current date!");
                    return false;
                }

                if (depDate.getTime() <= arrDate.getTime()) {
                    alert("The arrival date should be after the departure date!");
                    return false;
                }

                return true;
            }
        </script>
    </body>
</html>
