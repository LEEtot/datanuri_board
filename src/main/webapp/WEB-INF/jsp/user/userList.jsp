<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="m_70 boardList-main">
    <h1 class="boardsubject-title">회원 관리</h1>

    <div id="userList-S002-div" class="dis-none">
    <h3>관리자 목록</h3>
    <div class="boardList_tb mb-150" >
        <ul class="boardsubjectlist-group-container">
            <li class="list-group-item boardsubjectList-head">
                <div class="tab_item mr_12 tab_item_w150">회원번호</div>
                <div class="tab_item mr_12 tab_item_w250">이메일</div>
                <div class="tab_item mr_12 tab_item_w150">이름</div>
                <div class="tab_item mr_12 tab_item_w150">역할</div>
                <div class='tab_item mr_12 tab_item_w150'>상태</div>
                <div class="tab_item mr_12 tab_item_w150">핸드폰번호</div>
                <div class='tab_item mr_12 tab_item_w250'>마지막 로그인시간</div>
                <div class='tab_item mr_12 tab_item_w250'>생성일</div>
                <div class="tab_item mr_12 tab_item_w150">권한해제</div>
            </li>
            <div id="userList-R002">

            </div>
        </ul>
    </div>
    </div>


    <h3>일반회원 목록</h3>
    <div class="admin-btns">
    <button id="toR002-btn" class="btn btn-admin btn-wbig dis-none" onclick="btnClickActive('updatesR002')">관리자 권한 부여</button>
    <button class="btn btn-admin btn-wsmall" onclick="btnClickActive('updatesS002')">정지</button>
        <button class="btn btn-admin btn-wsmall" onclick="btnClickActive('updatesS001')">정지풀기</button>
    <button class="btn btn-admin btn-wsmall" onclick="btnClickActive('updatesS003')">탈퇴</button>
    </div>
    <div class="boardList_tb mb-150" >
        <ul class="boardsubjectlist-group-container">
            <li class="list-group-item boardsubjectList-head">
                <div class="tab_item mr_12 tab_item_w50"><input type="checkbox" onclick="chckAll()" id="chckAll-btn"></div>
                <div class="tab_item mr_12 tab_item_w150">회원번호</div>
                <div class="tab_item mr_12 tab_item_w250">이메일</div>
                <div class="tab_item mr_12 tab_item_w150">이름</div>
                <div class="tab_item mr_12 tab_item_w150">역할</div>
                <div class='tab_item mr_12 tab_item_w150'>상태</div>
                <div class="tab_item mr_12 tab_item_w150">핸드폰번호</div>
                <div class='tab_item mr_12 tab_item_w250'>마지막 로그인시간</div>
                <div class='tab_item mr_12 tab_item_w250'>생성일</div>
            </li>
            <div id="userList-R003">

            </div>
        </ul>
    </div>
</div>

<script type="text/javascript">
    function getUserListR002(){
        console.log(token);
        $("#userList-R002").empty();
        $.ajax({
            url: '<%=request.getContextPath()%>/api/v1/user/userListR002',
            type:'get',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type","application/json");
                xhr.setRequestHeader("Authorization", "Bearer "+ token);
            },
            dataType : "json",
            success : function(data){
                console.log(data);

                $.each(data,function(idx,item){
                var userLi = '<li class="list-group-item boardList_tab"> '+
                    '<div class="tab_item mr_12 tab_item_w150">'+item.id+'</div>' +
                    '<div class="tab_item mr_12 tab_item_w250">'+item.email+'</div>' +
                    '<div class="tab_item mr_12 tab_item_w150">'+item.name+'</div>' +
                    '<div class="tab_item mr_12 tab_item_w150">'+roleName(item.role)+'</div>' +
                    '<div class="tab_item mr_12 tab_item_w150">'+stateName(item.state)+'</div>' +
                    '<div class="tab_item mr_12 tab_item_w150">'+item.phoneNumber+'</div>' +
                    '<div class="tab_item mr_12 tab_item_w250">'+dateFomrmat(item.lastLoginTime)+'</div>' +
                    '<div class="tab_item mr_12 tab_item_w250">'+dateFomrmat(item.createdDate)+'</div>' +
                    '<div class="tab_item mr_12 tab_item_w150"><button class="modi-btn btn" type="button" onclick="updateRole('+item.id+')">권한해제</button></div>' +
                '</li>'
                $("#userList-R002").append(userLi);
                })
            }
        })
    }

    function getUserListR003(){
        console.log(token);
        $("#userList-R003").empty();
        $.ajax({
            url: '<%=request.getContextPath()%>/api/v1/user/userListR003',
            type:'get',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type","application/json");
                xhr.setRequestHeader("Authorization", "Bearer "+ token);
            },
            dataType : "json",

            success : function(data){
                console.log(data);

                $.each(data,function(idx,item){
                    var userLi = '<li class="list-group-item boardList_tab"> '+
                        '<div class="tab_item mr_12 tab_item_w50"><input type="checkbox" class="chck" value="'+item.id+'"></div>'+
                        '<div class="tab_item mr_12 tab_item_w150">'+item.id+'</div>' +
                        '<div class="tab_item mr_12 tab_item_w250">'+item.email+'</div>' +
                        '<div class="tab_item mr_12 tab_item_w150">'+item.name+'</div>' +
                        '<div class="tab_item mr_12 tab_item_w150">'+roleName(item.role)+'</div>' +
                        '<div class="tab_item mr_12 tab_item_w150">'+stateName(item.state)+'</div>' +
                        '<div class="tab_item mr_12 tab_item_w150">'+item.phoneNumber+'</div>' +
                        '<div class="tab_item mr_12 tab_item_w250">'+dateFomrmat(item.lastLoginTime)+'</div>' +
                        '<div class="tab_item mr_12 tab_item_w250">'+dateFomrmat(item.createdDate)+'</div>' +

                        '</li>'
                    $("#userList-R003").append(userLi);
                })
            }
        })
    }

    /*체크박스 전체선택*/
    function chckAll(){
        if($("#chckAll-btn").is(':checked')){
            $("input[class=chck]").prop("checked",true);
        }else{
            $("input[class=chck]").prop("checked",false);
        }
    }

    $(document).on("click","input:checkbox[class=chck]",function(e){

        var chks = document.getElementsByClassName("chck");
        var chksChecked = 0;

        for(var i =0; i<chks.length; i++){
            var cbox = chks[i];

            if(cbox.checked){
                chksChecked++;
            }
        }

        if(chks.length == chksChecked){
            $("#chckAll-btn").prop("checked",true);
        } else{
            $("#chckAll-btn").prop("checked",false);
        }
    })

    function getChckId(){
        var chckArray = [];

        $("input:checkbox[class=chck]:checked").each(function(){
            chckArray.push(this.value);
        })
        return chckArray;
    }

    function updateRole(reqId){
        console.log(reqId);
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/api/v1/user/updateR003?reqId="+reqId,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type","application/json");
                xhr.setRequestHeader("Authorization", "Bearer "+ token);
            },
            success : function(){
                checkAuth();
            },
            error : function(request, status, error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        })
    }

    function btnClickActive(how){
        var ids = getChckId();

        ids = JSON.stringify(ids);
        console.log(ids);
        $.ajax({
            type : "post",
            url : "<%=request.getContextPath()%>/api/v1/user/"+how,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type","application/json");
                xhr.setRequestHeader("Authorization", "Bearer "+ token);
            },

            data : ids,
            success : function(){
                checkAuth();
            },
            error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }

        })
    }

    function checkAuth(){
        console.log(me);
        if(me.auth=="R001"){
            $("#userList-S002-div").removeClass('dis-none');
            $("#toR002-btn").removeClass('dis-none');
            getUserListR002();
            getUserListR003();
        } else if(me.auth=='R002'){
            getUserListR003();
        }
    }

    $(document).ready(function(){
        checkAuth();
    })
</script>