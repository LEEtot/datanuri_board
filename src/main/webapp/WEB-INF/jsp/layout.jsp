<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width" , initial-scale="1">
    <title>boardproject</title>
    <script src="/resources/static/js/jquery-3.6.0.js" ></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/static/css/base.css" />
</head>
<body>
<tiles:insertAttribute name="left"/>
<tiles:insertAttribute name="body"/>
</body>

<script>


    function getBoardSubjectList(){
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/api/boardSubject/list/S004",
            datatype:"json",
        }).done(function(data){
            console.log(data);
            $("#board_menu_ul_S004").empty();
            let board_menu_li = "";
            $.each(data, function(idx,item){
                //console.log(item);
                board_menu_li = "<li class='boardItem btn item"+item.state+"' ><a href='<%=request.getContextPath()%>/board/boardList/"+item.id+"'>"+item.subject+"</a></li>";
                $("#board_menu_ul_S004").append(board_menu_li);
            });
        })

    }

    function getBoardSubjectList2(){
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/api/boardSubject/list/S001",
            dataType:"json"
        }).done(function(data){
            let board_menu_li = "";
            $("#board_menu_ul_S001").empty();
            $.each(data,function(idx,item){

                board_menu_li = "<li class='boardItem btn item"+item.state+"' ><a href='<%=request.getContextPath()%>/board/boardList/"+item.id+"'>"+item.subject+"</a></li>";
                $("#board_menu_ul_S001").append(board_menu_li);
            })
        })
    }

    $(document).ready(function(){
        getBoardSubjectList();
        getBoardSubjectList2();
    })
</script>
</html>