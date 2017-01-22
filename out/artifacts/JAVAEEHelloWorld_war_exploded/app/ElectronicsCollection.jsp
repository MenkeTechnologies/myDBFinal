<%-- 
    Document   : ControlCenter
    Created on : Dec 11, 2016, 7:14:22 PM
    Author     : jacobmenke
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>


<%!

%>

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
    </head>
    <body >
        <div class="container-fluid">
            <h1 id="head">Electronics Collection</h1>

            <form name="addForm" action="/db/app/DB_ElectronicsServlet" id="addForm">

                <ul class="list-group" id="topInputs">
                    <li class="list-group-item disabled" style="font-size: 40px">Add A Device</li>
                    <li class="list-group-item">
                        <span class="badge"></span>
                        <input type="text" placeholder="Enter a device name.." name="name" /> Device Name
                    </li>

                    <!--                    <li class="list-group-item">
                                            <span class="badge"></span>
                                            <input type="text" name="program"/> Program
                                            
                                        </li>-->


                    <li class="list-group-item">
                        <span class="badge"></span>
                        <input type="text" placeholder="Absolute Path to Turn On..." name="onFilePath" /> Executable Path = ON
                    </li>
                    <li class="list-group-item">
                        <span class="badge"></span>
                        <input type="text" placeholder="Absolute Path To Turn Off..." name="offFilePath" /> Executable Path = OFF

                    </li>

                </ul>

                <input id="addOrClear" type="hidden" name="action" value="add"/>

            </form>



            <div class="jumbotron">
              
                <div class="btn-group btn-group-lg" id="addDiv" role="group">

                    <button id="addButton" type="button" type="submit" name="action" value="add" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> Add</button>
                    <button id="clearButton" type="button" type="submit" name="action" value="clear list" class="btn btn-default" ><span class="glyphicon glyphicon-trash"></span> Clear The List</button>
                    <button class="btn btn-default"><a href="/db/app/directory.jsp"><span class="glyphicon glyphicon-chevron-left"></span> Back to Home Screen</a></button>
                    <!--                ?name=h&onFilePath=h&offFilePath=home&action=add-->
                </div>

            </div>




      




        <hr/>


        <h3 id="error">${errorMessage}</h3>

        <table id="secondTable" class="table-class-second">
            <tr><th>Name</th><th>On Exec</th><th>Off Exec</th><th>Added @</th></tr>
                    <c:forEach var="device" items="${ElectronicsCollection}" varStatus="loopStatus">
                <tr>


                <form action="/db/app/DB_ElectronicsServlet" style="margin-left: 30px">

                    <td><input id="nameTextField" type="text"  name="name" value="${device.name}" /></td>
                    <td><input type="text"  name="onFilePath" value="${device.onFilePath}" /></td>
                    <td><input type="text"  name="offFilePath" value="${device.offFilePath}" /></td>
                    <td id="date">${device.dateAdded}</td>

                    </tr>
                    <tr id="bottomrow">
                        <td colspan="4"><input type="submit" id="clearList" name="action" value="Update" />

                            <input type="submit" id="clearList" name="action" value="Remove" />

                            <input type="submit" id="exec" name="action" value="TurnOn"/>

                            <input type="submit" id="exec" name="action" value="TurnOff"/>
                        </td>
                        <td>
                            <input type="hidden" name="index" value="${device.index}"/> </td>
                        </td>

                    </tr>

                </form>


            </c:forEach>
        </table>



    </form>

    <br><br><br><br><br><br><br><br>



    <p id="footer">Created by Jacob Menke. <a href="mailto:jamenk@email.wm.edu">Contact</a></p>

</div>

<script>

    var anim = "easeInOutElastic";

    var title = "#head";

    var elem = "body";

    $(title).click(function () {

        $(elem).css("position", "relative");

        $(elem).animate({
            left: 1500

        }, 5000, anim, function () {

        });

        $(elem).animate({
            left: 0,
        }, 1000, anim, function () {

        });

    });

    var addOrClearInput = document.getElementById("addOrClear");
    var addButton = document.getElementById("addButton");
    var clearButton = document.getElementById("clearButton");
    var addForm = document.getElementsByName("addForm")[0];

    addButton.addEventListener("click", addDevice);

    clearButton.addEventListener("click", clearList);

    function addDevice(e) {
        addOrClearInput.value = "add";
        addForm.submit();

    }

    function clearList(e) {
        addOrClearInput.value = "clear list";
        addForm.submit();
    }



</script>

</body>
</html>