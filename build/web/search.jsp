<%-- 
    Document   : viewpage
    Created on : Sep 22, 2021, 6:39:18 AM
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ashion Template">
        <meta name="keywords" content="Ashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Online Booking</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css1/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css1/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css1/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css1/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css1/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css1/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css1/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css1/style.css" type="text/css">
        <link href="rhea/public/vendors/plyr/plyr.css" rel="stylesheet">
        <link href="rhea/public/assets/css/theme.css" rel="stylesheet" />
    </head>

    <body>
        
            <nav class="navbar navbar-expand-lg fixed-top py-3 backdrop" data-navbar-on-scroll="data-navbar-on-scroll">
                <div class="container"><a class="navbar-brand d-flex align-items-center fw-bold fs-2" href="homepage"> <img class="d-inline-block align-top img-fluid" src="assets/img/gallery/logo-icon.png" alt="" width="50" /><span class="text-primary fs-4 ps-2">Rhea</span></a>
                    <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse border-top border-lg-0 mt-4 mt-lg-0" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto pt-2 pt-lg-0">
                            <li class="nav-item"><a class="nav-link active" aria-current="page" href="homepage">Home</a></li>
                            <li class="nav-item"><a class="nav-link text-600" href="#featuresVideos">Video</a></li>
                            <li class="nav-item"><a class="nav-link text-600" href="places">Place</a></li>
                            <li class="nav-item"><a class="nav-link text-600" href="#booking">Booking </a></li>
                                <c:if test="${sessionScope.acc != null}">  
                                <li class="nav-item"><a class="nav-link text-600" href="profile">Profile </a></li> 
                                </c:if>
                                <c:if test="${sessionScope.acc.isAdmin == true}" >
                                <li class="nav-item dropdown no-arrow">
                                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.userName}</span>
                                        <img class="img-profile rounded-circle"
                                             src="img/undraw_profile.svg">
                                    </a>
                                    <!-- Dropdown - User Information -->
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                         aria-labelledby="userDropdown">
                                        <a class="dropdown-item" href="profile">
                                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Profile
                                        </a>
                                        <a class="dropdown-item" href="user">
                                            <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Dash board
                                        </a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="logout" >
                                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Logout
                                        </a>
                                    </div>
                                </li>

                            </c:if>
                            <c:if test="${sessionScope.acc.isAdmin == false}" >
                                <li class="nav-item dropdown no-arrow">
                                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.userName}</span>
                                        <img class="img-profile rounded-circle"
                                             src="img/undraw_profile.svg">
                                    </a>
                                    <!-- Dropdown - User Information -->
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                         aria-labelledby="userDropdown">
                                        <a class="dropdown-item" href="profile">
                                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Profile
                                        </a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="logout" >
                                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Logout
                                        </a>
                                    </div>
                                </li>

                            </c:if>
                            <c:if test="${sessionScope.acc == null}">
                                <li class="nav-item dropdown no-arrow">
                                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <img class="img-profile rounded-circle"
                                             src="img/undraw_profile.svg">
                                    </a>
                                    <!-- Dropdown - User Information -->
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                         aria-labelledby="userDropdown">
                                        <a class="dropdown-item" href="login">
                                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Login
                                        </a>
                                        <a class="dropdown-item" href="register">
                                            <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Sign Up
                                        </a>
                                </li>

                            </c:if>

                        </ul>


                    </div>
                </div>
            </nav>


        <!-- Shop Section Begin -->
        <section class="shop spad">
            <br/>
            <br/>
            <form action="search_room?page=1" method="post">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-3">
                            <div class="shop__sidebar">

                                <div class="sidebar__sizes">
                                    <div class="section-title">
                                        <h4>Place</h4>
                                    </div>
                                        <select class="form-select"  name="place">
                                            <option selected="" value="0">Select a place</option>
                                            <c:forEach items= "${requestScope.places}" var="q"> 
                                                <option value="${q.pId}">${q.place}</option>           
                                            </c:forEach>
                                        </select>
                                </div>
                                <div class="sidebar__color">
                                    <div class="section-title">
                                        <h4>Type of room</h4>
                                    </div>
                                   

                                        <select name="type" class="form-select" id="autoSizingSelect">
                                            <option selected="" value="0">Select type of room</option>
                                            <c:forEach items= "${requestScope.types}" var="p"> 
                                                <option value="${p.typeId}">${p.type}</option>           
                                            </c:forEach>                                    
                                        </select>
                              
                                </div>
                                <div class="sidebar__color">
                                    <div class="section-title">
                                        <h4>Number of Beds</h4>
                                    </div>
                                    <select name="numBed" class="form-select" id="autoSizingSelect">
                                        <option selected="" value="0">Number of Beds</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                    </select>
                                </div>

                                <input type="submit" name="submit" value="Search">

                            </div>
                        </div>
                        <div class="col-lg-9 col-md-9">
                            <input type="text" name="text" placeholder="Search by name">
                            <input type="submit" name="search" value="search">&nbsp;&nbsp;&nbsp;&nbsp;
                            <select name="orderBy" id="country" class="bg-light" onlick>
                                &emsp;<option value="0">&emsp;------------&emsp;</option>
                                <option value="name">&emsp;By Name A->Z&emsp;</option>
                                <option value="price">&emsp;By Price ascending&emsp;</option>
                                <option value="price desc">&emsp;By Price descending&emsp;</option>
                                <option value="rating">&emsp;By Rating ascending&emsp;</option>
                                <option value="rating desc">&emsp;By Rating descending&emsp;</option>
                            </select>
                            <input type="submit" name="submit" value="Sort">
                            <br>
                            <br>
                            <div class="row">
                                <c:forEach items="${requestScope.rooms}" var="r">
                                    <div class="col-lg-4 col-md-6">
                                        <div class="product__item">
                                            <div class="product__item__pic set-bg" data-setbg="rhea/public/assets/img/gallery/${r.picture}">
                                            </div>
                                            <div class="product__item__text">
                                                <h6><a href="#">${r.rName}</a></h6>
                                                <div class="product__price"><fmt:formatNumber type = "number" 
                                                                  maxFractionDigits = "3" value = "${r.price}" />VND</div>

                                            </div>
                                        </div>
                                    </div>   
                                </c:forEach>




                                <div class="col-lg-12 text-center">
                                    <div class="pagination__option">
                                        <c:forEach begin="1" end="${requestScope.end}" var="i">

                                            <button>${i}</button>
                                        </c:forEach>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </section>
        <!-- Shop Section End -->



        <!-- Footer Section Begin -->
        <footer class="footer">
            <div class="container">SS
                <div class="row">
                    <div class="col-lg-12">
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        <div class="footer__copyright__text">
                            <p>Copyright &copy; <script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a></p>
                        </div>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer Section End -->

        <!-- Search Begin -->
        <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>
        <!-- Search End -->

        <!-- Js Plugins -->
        <script src="js1/jquery-3.3.1.min.js"></script>
        <script src="js1/bootstrap.min.js"></script>
        <script src="js1/jquery.magnific-popup.min.js"></script>
        <script src="js1/jquery-ui.min.js"></script>
        <script src="js1/mixitup.min.js"></script>
        <script src="js1/jquery.countdown.min.js"></script>
        <script src="js1/jquery.slicknav.js"></script>
        <script src="js1/owl.carousel.min.js"></script>
        <script src="js1/jquery.nicescroll.min.js"></script>
        <script src="js1/main.js"></script>
    </body>

</html>