<%@ page import="jdbc.IP" %><%--
    Document   : directory
    Created on : Jan 2, 2017, 3:49:48 PM
    Author     : jacobmenke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>$my_DIR</title>
    <link rel="stylesheet" href="/db/css/directory.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Pacifico" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Roboto:100' rel='stylesheet' type='text/css'>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

</head>
<body>
<h1>$my_DIR</h1>

<a href="/db/app/DB_ElectronicsServlet">Electronics Collection</a>
<a href="/db/app/books/index.html">Books Collection</a>
<a href="/db/app/cookbook">Cookbook</a>
<a href="/db/app/DB_LearningServlet" id="tips">Tips and Tricks</a>
<a href="/db/app/upload.jsp">Upload</a>
<a href="http://<%=new IP().getIP()+":2"%>/tipCalculator.html">Other Work</a>
<a href="/db/Welcome.jsp">$_^D</a>


<script>

    $(document).ready(function () {
        var elem = "body";

        $(elem).keypress(function (e) {

            console.log(e.keyCode + " pressed");
            $("#tips")[0].click();
        });

    })
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

</body>
</html>