<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width" , initial-scale="1">
    <title>boardproject</title>
    <script src="/resources/static/js/jquery-3.6.0.js" ></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/static/css/base.css" />
<%--    <script  type="text/javascript" src="../js/common.js"></script>--%>
</head>
<body>
<%--navbar 들어감--%>
<%--<jsp:include page="navbar.jsp" />--%>
<%--<%@include file="navbar.jsp"%>--%>
<c:import url="navbar.jsp"/>

<main class="main">
    <div class="board_tb" id="board_tb1">
        <h3 class="boardsubject-title"></h3>
        <ul class="list-group-container" id="list-container1">

        </ul>
    </div>

    <div class="board_tb" id="board_tb2">
        <h3  class="boardsubject-title"></h3>
        <ul class="list-group-container"  id="list-container2">

        </ul>
    </div>

    <div class="board_tb" id="board_tb3">
        <h3 class="boardsubject-title"></h3>
        <ul class="list-group-container"  id="list-container3">
        </ul>
    </div>


    <div class="board_tb" id="board_tb4">
        <h3  class="boardsubject-title"></h3>
        <ul class="list-group-container"  id="list-container4">

        </ul>
    </div>
</main>
</body>

<script type="text/javascript">

    function dateFomrmat(date){
        var dateString = date.replace("T"," ").replace(/\..*/,'');
        return dateString;
    }
    function getBoardMain(){
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/api/board/mainList",
            dataType:"json"
        }).done(function(data){
            console.log(data);
            let value_index = 0;
            //let list_ul = document.getElementsByClassName("list-group-container");

            $(".list-group-container").empty();
            $.each(data,function(key1,value){
                //var listgroup = $(".list-group-container")[value_index];
                let item_index = 0;
                var list_container = "#list-container"+(item_index+1);
                var board_subject_title = "";
                $.each(value,function(key2,item){
                    console.log(item);


                    var list_group_item = "<li class='list-group-item board_tab'><div class='tab_item'><a href='#'>"+item.title+"</a></div> <div class='tab_item mr_12'>추천수 : "+item.recommendCount+"</div> <div class='tab_item mr_12'>조회수 : "+item.viewCount+"</div> <div class='tab_item'>작성자 : "+item.creator+"</span> <br> 작성일 : <span>"+dateFomrmat(item.createdDate)+"</span></div> </li>";


                    //$(".list-group-container")[item_index].append(list_group_item);

                    $(list_container).append(list_group_item);
                    console.log(item_index);

                    board_subject_title = item.boardSubject.subject;
                })
                $(".boardsubject-title")[item_index].append(board_subject_title);
                item_index+=1;
                value_index = value_index + 1;
                //console.log(item_index);
            })
        })
    }

    $(document).ready(function(){
        getBoardMain();
    });
</script>
</html>