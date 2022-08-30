<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>



<body>

<nav class="nav_left">
    <a href="<%=request.getContextPath()%>/">
    <div class="nav_logo" style="background-image: url('/resources/static/main_logo.png')">
    </div></a>
    <div class="user_info">
        <div class="profile_img">프로필</div>
        <span>사용자</span>
    </div>
    <div class="nav_btns">
        <button type="button" class="btn btn-user btn-wsmall">마이페이지</button>
        <button type="button" class="btn btn-user btn-wsmall">로그아웃</button>
    </div>
    <ul class="board_menu ul_m10" id="board_menu_ul_S004">
<%--        <li class="boardItem btn itemS004" id=""><a href="<%=request.getContextPath()%>/board/">공지사항</a></li>
        <li class="boardItem btn itemS004">자유게시판</li>
        <li class="boardItem btn itemS004">운영자초이스</li>
        <li class="boardItem btn itemS004">QnA</li>
        <li class="boardItem btn itemS001">추가게시판1</li>
        <li class="boardItem btn itemS001">추가게시판2</li>--%>

    </ul>
    <ul class="board_menu ul_m10" id="board_menu_ul_S001">

    </ul>

    <ul class="board_menu admin_menu ul_m10">
        <li class="boardItem btn">회원관리</li>
        <li class="boardItem btn" ><a href="<%=request.getContextPath()%>/boardsubject/boardsubjectList">게시판관리</a></li>
    </ul>
</nav>
</body>

</html>