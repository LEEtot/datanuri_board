<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="m_70 boardList-main">
    <div class="login-container">
        <h1 class="boardsubject-title">회원가입</h1>
        <form method="post" id="form-sign">
            <div class="mb-3 mt-3">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
            </div>
            <div class="mb-3">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password"> 영문자+숫자+특수조합(8-25자리 입력)
            </div>
            <div class="mb-3 mt-3">
                <label for="name">name:</label>
                <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
            </div>
            <div class="mb-3">
                <label for="phoneNumber">phoneNumber:</label>
                <input type="text" class="form-control" id="phoneNumber" placeholder="Enter phoneNumber" name="phoneNumber"> ex "-"없이 숫자만 입력
            </div>
            <div class="mb-3 mt-3">
                <label for="imgPath">img:</label>
                <input type="file" class="form-control" id="imgPath" name="imgPath" accept="image/*" onchange="readURL(this);">
                <img id="preview" />
            </div>
            <input name="role" value="R003" hidden />
            <input name="state" value="S001" hidden/>

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

    function duplicateEmailCheck(formsubmit){

        console.log(formsubmit);

        let requestparam = {"email" : ""};

        let formObject = JSON.parse(formsubmit); //object로 변환
        requestparam.email =  formObject.email;
        $.ajax({
            url:"<%=request.getContextPath()%>/api/v1/user/duplicateCheck",
            type : "get",
            dataType:"json",
            contentType : "application/json; charset=utf-8",
            data : requestparam,
            success : function(data){
                console.log(data);
                if(data==true){
                    alert("중복된 이메일이 있습니다");
                    $("#email").focus();
                    return false;
                }else{
                    singUp(formsubmit);
                }
            }
        })
    }

    function beforeSignup(){
        //이메일 형식 확인
        var email_reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

        if($("#email").val()==""||email_reg.test($("#email").val())==false){

            alert("이메일형식에 맞지 않습니다");
            $("#email").focus();
            return false;
        }
        //비밀번호 빈칸 확인
        var pwd_reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
        if(!pwd_reg.test($("#pwd").val())){
            alert("비밀번호형식에 맞지 않습니다");
            $("#pwd").focus();
            return false;
        }

        //이름 빈칸 확인
        if($("#name").val()==""){
            alert("이름을 입력하세요");
            $("#name").focus();
            return false;
        }

        //전화번호 형식 확인
        var phone_reg = /^[0-9]+/g; //숫자만 입력
        if(!phone_reg.test($("#phoneNumber").val())){
            alert("전화번호는 숫자만 입력할 수 있습니다");
            $("#phoneNumber").focus();
            return false;
        }
    }

    function singUp(formsubmit){
        console.log(formsubmit);
        let formObject = JSON.parse(formsubmit); //object로 변환
        console.log(formObject);
        $.ajax({
            url:"<%=request.getContextPath()%>/api/v1/user/signup",
            type: "post",
            dataType: "text",
            contentType : "application/json; charset=utf-8",
            data : formsubmit,
            success : function(data){
                console.log(data);
                alert("회원가입이 완료되었습니다");
                location.href='<%=request.getContextPath()%>/';
            },
            error : function(request,status,error){
                console.log(request);
                console.log(status);
                console.log(error);
                alert("다시 시도해주세요");
            }

        })
    }

    function signupsubmit(){
        let formsubmitSerialArray  = $('#form-sign').serializeArray(); // serialize 사용
        let formsubmit = JSON.stringify(objectifyForm(formsubmitSerialArray)); //String으로 변환

        if(beforeSignup()!=false){
            duplicateEmailCheck(formsubmit);
        }


    }

</script>