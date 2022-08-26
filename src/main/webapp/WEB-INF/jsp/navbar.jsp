<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>



<body>

<nav class="nav_left">
    <div class="nav_logo">
        로고
    </div>
    <div class="user_info">
        <div class="profile_img">프로필</div>
        <span>사용자</span>
    </div>
    <div class="nav_btns">
        <button type="button" class="btn btn-user btn-wsmall">마이페이지</button>
        <button type="button" class="btn btn-user btn-wsmall">로그아웃</button>
    </div>
    <ul class="board_menu ul_m10" id="board_menu_ul">
<%--        <li class="boardItem btn itemS004" id=""><a href="<%=request.getContextPath()%>/board/">공지사항</a></li>
        <li class="boardItem btn itemS004">자유게시판</li>
        <li class="boardItem btn itemS004">운영자초이스</li>
        <li class="boardItem btn itemS004">QnA</li>
        <li class="boardItem btn itemS001">추가게시판1</li>
        <li class="boardItem btn itemS001">추가게시판2</li>--%>
    </ul>

    <ul class="board_menu admin_menu ul_m10">
        <li class="boardItem btn">회원관리</li>
        <li class="boardItem btn" ><a href="<%=request.getContextPath()%>/boardsubject/boardsubjectList">게시판관리</a></li>
    </ul>
</nav>
</body>
<script>



    $.ajax({
        type:"get",
        url:"<%=request.getContextPath()%>/api/boardSubject/list/S004",
        datatype:"json",
    }).done(function(data){
        $("#board_menu_ul").empty();
        let board_menu_li = "";
        $.each(data, function(idx,item){
            //console.log(item);
            board_menu_li = "<li class='boardItem btn item"+item.state+"' ><a href='<%=request.getContextPath()%>/board/boardList/"+item.id+"'>"+item.subject+"</a></li>";
            $("#board_menu_ul").append(board_menu_li);
        });
    })

    $.ajax({
        type:"get",
        url:"<%=request.getContextPath()%>/api/boardSubject/list/S001",
        dataType:"json"
    }).done(function(data){
        let board_menu_li = "";
        $.each(data,function(idx,item){
            board_menu_li = "<li class='boardItem btn item"+item.state+"' ><a href='<%=request.getContextPath()%>/board/boardList/"+item.id+"'>"+item.subject+"</a></li>";
            $("#board_menu_ul").append(board_menu_li);
        })
    })
</script>
</html>