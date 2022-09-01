<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="m_70 boardList-main">
    <div class="login-container">
        <h1 class="boardsubject-title">회원가입</h1>
        <form method="post" id="form-login">
            <div class="mb-3 mt-3">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
            </div>
            <div class="mb-3">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
            </div>
            <div class="mb-3 mt-3">
                <label for="email">name:</label>
                <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
            </div>
            <div class="mb-3">
                <label for="pwd">phoneNumber:</label>
                <input type="text" class="form-control" id="phoneNumber" placeholder="Enter phoneNumber" name="phoneNumber">
            </div>
            <div class="mb-3 mt-3">
                <label for="imgPath">img:</label>
                <input type="file" class="form-control" id="imgPath" name="imgPath" accept="image/*" onchange="readURL(this);">
                <img id="preview" />
            </div>


            <button type="button" class="btn btn-primary" id="login-btn" onclick="signupsubmit()">Submit</button>
        </form>
    </div>
</div>

<script type="text/javascript">
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('preview').src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            document.getElementById('preview').src = "";
        }
    }

    function duplicateEmailCheck(){
        let formsubmitSerialArray  = $('#form-login').serializeArray(); // serialize 사용
        let formsubmit = JSON.stringify(objectifyForm(formsubmitSerialArray));
        console.log(formsubmit); // password = 5151

        $.ajax({
            url:"<%=request.getContextPath()%>/api/v1/user/duplicateCheck?email="+formsubmit.email,
            type : "get",

        })
    }

    function signupsubmit(){

    }

</script>