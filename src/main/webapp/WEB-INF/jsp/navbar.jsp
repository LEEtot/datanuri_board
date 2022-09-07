<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>



<body>

<nav class="nav_left">
    <a href="<%=request.getContextPath()%>/">
    <div class="nav_logo" style="background-image: url('/resources/static/main_logo.png')">
    </div></a>

    <div class="user-login-divs dis-none">
    <div class="user_info">
        <div class="main-profile-img">프로필</div>
        <div>
            <h4 id="user_name" class="ml_12"></h4>
            <h5 id="user-role" class="ml_12"></h5>
        </div>


    </div>
    <div class="nav_btns">
        <button type="button" class="btn btn-user btn-wsmall">마이페이지</button>
        <button type="button" class="btn btn-user btn-wsmall" onclick="logout()">로그아웃</button>
    </div>
        <ul class="board_menu admin_menu ul_m10 dis-none" id="admin-menu-ul">
            <li class="boardItem btn"><a href="<%=request.getContextPath()%>/user/userList">회원관리</a></li>
            <li class="boardItem btn" ><a href="<%=request.getContextPath()%>/boardsubject/boardsubjectList">게시판관리</a></li>
        </ul>
    </div>

    <div class="board_menu nav_btns dis-none" id="login-btn-div">
        <button type="button" class="btn btn-user btn-login" onclick="location.href='<%=request.getContextPath()%>/signupPage'">회원가입</button>
        <button type="button" class="btn btn-user btn-login" onclick="location.href='<%=request.getContextPath()%>/loginPage'">로그인</button>
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


</nav>
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

    let token = localStorage.getItem('token');
    let me ='';
    function meInfo(){
        console.log(token);
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        console.log(JSON.parse(jsonPayload));
        return (JSON.parse(jsonPayload));


    }

    function navibarLogin(){
        console.log(me.name);
        $("#user_name").html(me.name);

        var role = "";

        if(me.auth == "R001"){
            role = "운영자";
        } else if(me.auth == "R002"){
            role = "관리자";
        } else if(me.auth =="R003"){
            role = "사용자";
        }
        $("#user-role").html(role);

        if ($(".user-login-divs").hasClass('dis-none')) {
            $(".user-login-divs").removeClass("dis-none");
            $("#login-btn-div").addClass("dis-none");
        }
        if(me.auth == "R001" || me.auth == "R002"){
            $("#admin-menu-ul").removeClass("dis-none");
        }
    }

    function navibarNoLogin(){
        $("#login-btn-div").removeClass("dis-none");
    }

    function logout(){
        localStorage.removeItem('token');
        location.href="<%=request.getContextPath()%>/"
    }

    function objectifyForm(formArray) {//serializeArray data function
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }

    function dateFomrmat(date){
        if(date==null){
            return "";
        }
        var dateString = date.replace("T"," ").replace(/\..*/,'');
        return dateString;
    }

    function roleName(role){
        if(role == "R001"){
            return "운영자";
        } else if(role == "R002"){
            return "관리자";
        } else{
            return "일반회원";
        }
    }

    function stateName(state){
        if(state == "S001"){
            return "일반";
        } else if(state == "S002"){
            return "정지";
        } else if(state == "S003"){
            return "탈퇴";
        }
    }

    $(document).ready(function(){
        getBoardSubjectList();
        getBoardSubjectList2();
        if(token != null){
            me = meInfo();
            navibarLogin();
        } else{
            navibarNoLogin();
        }


    })
</script>
</html>