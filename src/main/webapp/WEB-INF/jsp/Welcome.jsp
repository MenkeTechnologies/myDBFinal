<%-- 
Document   : index
Created on : Dec 11, 2016, 6:57:48 PM
Author     : jacobmenke
--%>

<%@page import="java.time.LocalDate" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>my_DB</title>
    <meta name="viewport" content="width = device-width, initial scale=1">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Pacifico" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Roboto:100' rel='stylesheet' type='text/css'>

    <link href="WelcomePageStyles.css" rel="stylesheet" type="text/css"/>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

</head>
<body>
<div id="theCarousel" class="carousel slide span12" data-ride="carousel" data-interval="60000">

    <!--        <div id="theCarousel">-->

    <!-- Define how many slides to put in the carousel -->
    <ol class="carousel-indicators">
        <li data-target="#theCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#theCarousel" data-slide-to="1"></li>
        <li data-target="#theCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Define the text to place over the image -->
    <div class="carousel-inner">
        <div class="item active">
            <div class="slide1"></div>
            <div class="carousel-caption">
                <h1 id="header1"></h1>
                <form action="Login" method="post" id="login">
                    <div id="loginDiv">
                        <label id="firstLabel">Uname</label>
                        <input type="text" name="uname"/>

                        <label>Passwd</label>
                        <input type="password" name="pw"/>
                        <input id="submit" type="submit" value="Enter">
                    </div>

                </form>

                <p id="date"></p>

            </div>
        </div>
        <div class="item">
            <div class="slide2"></div>
            <div class="carousel-caption">
                <h1>RASPBERRY PI</h1>


            </div>
        </div>
        <div class="item ">
            <div class="slide3"></div>
            <div class="carousel-caption">
                <h1>Custom Electronics and PCBs</h1>

            </div>
        </div>
    </div>

    <!-- Set the actions to take when the arrows are clicked -->
    <a class="left carousel-control" href="#theCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"> </span>
    </a>
    <a class="right carousel-control" href="#theCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
    </a>
</div>


<script>

    var date = document.getElementById("date");

    setInterval(function () {
        date.innerHTML = Date.now();

    }, 30);


    <% if (request.getAttribute("error") != null) {
        String error = (String) request.getAttribute("error");

        if (error.equals("bad_login!")) {
            String forJS = "alert('That was a BAD login')";
            %>


    <%
}
}%>

    var anim = "easeInOutElastic";

    var elem = "body";

    $(elem).keypress(function(e){

        if (e.which === 13){
            $("#submit").click();
            return false;
        }
    });

    $(elem).click(function () {

        $(elem).css("position", "relative");

        $(elem).animate({
            left: 2000

        }, 2000, anim, function () {

        });

        $(elem).animate({
            left: 0
        }, 2000, anim, function () {

        });


    });


</script>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
