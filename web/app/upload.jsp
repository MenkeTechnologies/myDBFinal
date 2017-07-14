<%-- 
    Document   : upload
    Created on : Dec 18, 2016, 9:26:41 PM
    Author     : jacobmenke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Electronics Collection</title>

    <link href="/db/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Pacifico" type="text/css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto" type="text/css">
    <link href="/db/css/DatabaseCollection.css" rel="stylesheet" type="text/css"/>
</head>
<body >
<div class="container-fluid">
    <h1 id="head">Upload</h1>

    <form name="addForm" action="/db/DB_Upload" id="addForm" method="post" enctype="multipart/form-data">

        <ul class="list-group" id="topInputs">
            <li class="list-group-item disabled" style="font-size: 40px">Your File</li>

            <li class="list-group-item">
                <span class="badge">Your file ready to go!</span>
                <input type="file" name="file" style="padding-top: 20px;
                      height: 60px"/>
            </li>

        </ul>

    </form>

    <div class="jumbotron">
        <p>
        <div class="btn-group btn-group-lg" id="addDiv" role="group">

            <button id="upButton" type="button" type="submit" name="action" value="Upload" class="btn btn-default"><span class="glyphicon glyphicon-save"></span> UPLOAD</button>
            <button class="btn btn-default"><a href="/db/app/directory.jsp"><span class="glyphicon glyphicon-chevron-left"></span> Back to Home Screen</a></button>
            <!--                ?name=h&onFilePath=h&offFilePath=home&action=add-->
        </div>

    </div>
    </p>
    <hr/>

    <h3>${result}</h3>

    </form>

    <br><br><br><br><br><br><br><br>

    <p id="footer">Created by Jacob Menke. <a href="mailto:jamenk@email.wm.edu">Contact</a></p>

</div>

<script>
    //    var addOrClearInput = document.getElementById("addOrClear");
    //    var addButton = document.getElementById("addButton");
    //    var clearButton = document.getElementById("clearButton");
    var addForm = document.getElementsByName("addForm")[0];
    //    addButton.addEventListener("click", addDevice);
    //    clearButton.addEventListener("click", clearList);
    //    function addDevice(e) {
    //        addOrClearInput.value = "add";
    //        addForm.submit();
    //    }
    //    function clearList(e) {
    //        addOrClearInput.value = "clear list";
    //        addForm.submit();
    //    }

    var uploadButton = document.getElementById("upButton");

    uploadButton.addEventListener("click", upload);

    function upload(e){

        addForm.submit();
    }

</script>
</body>
</html>