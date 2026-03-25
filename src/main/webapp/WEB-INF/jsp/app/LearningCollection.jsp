<%-- 
    Document   : Learning
    Created on : Jan 2, 2017, 4:53:52 PM
    Author     : jacobmenke
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Electronics Collection</title>

    <link href="/db/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Pacifico" type="text/css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto" type="text/css">
    <link href="/db/css/DatabaseCollection.css" rel="stylesheet" type="text/css"/>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

    <style>
        textarea {
            display: block;
            border-radius: 4px;
            height: 70px;
            width: 100%;
            font-family: Arial;
        }

        #nameTextField {
            display: block;
            width: 8vw;
        }

        #learnedTextField {
            display: block;
            width: 50vw;
        }

        /*#bottomrow td input {*/
            /*display: inline;*/
            /*font-size: 20px;*/

            /*-webkit-box-shadow: inset 0 0 10px #000;*/
            /*background: space;*/

            /*-webkit-border-radius: 50px;*/
            /*-moz-border-radius: 10px;*/
            /*border-radius: 10px;*/

        /*}*/

        .rightButtons {
            border-radius: 10px;
            background: linear-gradient(25deg, white, gray);

        }

        .rightButtons:hover {
            -webkit-transform: scale(1.1);
        }

        #updateButton {
            width: 100px;
            height: 30px;

        }

        h4 {
            margin-top: 100px;
            font-family: Pacifico;
            font-size: 5em;
            text-align: center;

        }

        #learnedHeader {
            padding-left: 10px;

        }


    </style>

</head>
<body>
<div class="container-fluid">
    <h1 id="head">Tips and Tricks</h1>

    <form name="addForm" action="/db/app/DB_LearningServlet" id="addForm">

        <ul class="list-group" id="topInputs">
            <li class="list-group-item disabled" style="font-size: 40px">Something You Learned</li>
            <li class="list-group-item">
                <span class="badge"></span>
                <input type="text" placeholder="Category" name="category" id="categoryTextField" value="Programming"/> Category
            </li>
            <li class="list-group-item">
                <span class="badge"></span>
                <textarea placeholder="What you learned..." name="learning" id="learningTextField"></textarea>

            </li>

        </ul>

        <input id="addOrClear" type="hidden" name="action" value="add"/>

    </form>


    <div class="jumbotron">

        <p>
        <div class="btn-group btn-group-lg" id="addDiv" role="group">

            <button id="addButton" type="button" type="submit" name="action" value="add" class="btn btn-default"><span
                    class="glyphicon glyphicon-plus"></span> Add
            </button>
            <!--                    <button id="clearButton" type="button" type="submit" name="action" value="clear list" class="btn btn-default" ><span class="glyphicon glyphicon-trash"></span> Clear The List</button>-->
            <button class="btn btn-default"><a href="/db/app/directory.jsp"><span
                    class="glyphicon glyphicon-chevron-left"></span> Back to Home Screen</a></button>
            <!--                ?name=h&onFilePath=h&offFilePath=home&action=add-->
        </div>

    </div>

    </p>

    <hr/>


    <h3 id="error">${errorMessage}</h3>


    <table id="secondTable" class="table-class-second">
        <tr>
            <th>Category</th>
            <th id="learnedHeader">Learned</th>
            <th>Added @</th>
        </tr>


        <c:forEach var="tip" items="${LearningCollection}" varStatus="loopStatus">


            <tr>
                <form action="/db/app/DB_LearningServlet" style="margin-left: 30px">
                    <td><input id="nameTextField" type="text" name="category" value="${tip.category}"/></td>
                    <td><input type="text" id="learnedTextField" name="learning" value='${tip.learning}'/></td>
                    <td id="date">${tip.dateAdded}</td>

                    <aside id="bottomrow">
                        <td colspan="4"><input type="submit" id="updateButton" class="rightButtons" name="action"
                                               value="Update"/></td>
                        <td><input type="submit" class="rightButtons" name="action" value="Remove"/></td>

                        <td><input type="hidden" name="index" value="${tip.index}"/></td>
                    </aside>


                </form>
            </tr>


        </c:forEach>
    </table>

    <h4>Learning Achievements: ${count}</h4>

    <br><br>


    <p id="footer">Created by Jacob Menke. <a href="mailto:jamenk@email.wm.edu">Contact</a></p>

</div>

<script>
    $(document).ready(function (e) {
        $("#categoryTextField").focus();
    });

    var anim = "easeInOutElastic";
    var title = "#head";
    var elem = "body";

    $("#learningTextField").keypress(function (e) {
        if (e.which === 13){
            $("#addButton").click();
        }

    });

    $(title).click(function () {

        $(elem).css("position", "relative");

        $(elem).animate({
            left: 2000

        }, 2000, anim, function () {

        });

        $(elem).animate({
            left: 0,
        }, 2000, anim, function () {

        });
    });


</script>


<script>
    var addOrClearInput = document.getElementById("addOrClear");
    var addButton = document.getElementById("addButton");
    var clearButton = document.getElementById("clearButton");
    var addForm = document.getElementsByName("addForm")[0];


    addButton.addEventListener("click", addLearning);


    function addLearning(e) {
        addOrClearInput.value = "add";
        addForm.submit();

    }


</script>

</body>
</html>
