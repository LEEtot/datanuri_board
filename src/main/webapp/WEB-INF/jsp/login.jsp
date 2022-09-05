<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="m_70 boardList-main">
    <div class="login-container">
        <h1 class="boardsubject-title">로그인</h1>
        <form method="post" id="form-login">
            <div class="mb-3 mt-3">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
            </div>
            <div class="mb-3">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
            </div>
            <span id="check-msg" class="dis-none">이메일이나 비밀번호를 확인해주세요</span>
            <div class="form-check mb-3">
                <%--<label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="remember"> Remember me
                </label>--%>
            </div>
            <button type="button" class="btn btn-primary" id="login-btn" onclick="loginsubmit()">Submit</button>
        </form>
    </div>
</div>

<script type="text/javascript">



    function loginsubmit(){
        var formsubmitSerialArray  = $('#form-login').serializeArray() // serialize 사용
        var formsubmit = JSON.stringify(objectifyForm(formsubmitSerialArray));
        console.log(formsubmit); // password = 5151

        //var token = localStorage.getItem("token");
        //console.log(token);
        $.ajax({
            type:"post",
            url:"<%=request.getContextPath()%>/api/v1/user/login",
            dataType:"json",
            contentType : "application/json; charset=utf-8",
            data : formsubmit,
            success : function(data) {
                console.log(data);
                localStorage.setItem('token', data.accessToken);
                location.href="<%=request.getContextPath()%>/";
            },
            error : function(request, status, error) {
                console.log(status);
                $("#check-msg").removeClass("dis-none");
            }
        })
    }


</script>