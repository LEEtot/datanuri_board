<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form">
    <h1 class="boardsubject-title">게시글 작성</h1>
        <section class="article-detail table-common con row">
        <div class="article-writer cell">
            <form name="write" id="boardwrite-form">
                <div class="user_info">
                    <div class="write-profile_img">프로필</div>
                    <span id="board_writer">작성자</span>

                </div>

                <span class="selectedSubject-title" id="selectedSubject-title">${selectedBoardSubject.subject}</span>
                <input name="boardSubjectId" value="${selectedBoardSubject.id}" hidden />

                <span class="subtitle">제목</span>
                <input name="title" class="board-input" placeholder="title" maxlength="60"/>
                <span class="subtitle">내용</span>
                <textarea name="contents" class="board-textarea" placeholder="content"></textarea>
                <input type="hidden" name="state" class="board-input"  value="S001"/>
                <div class="btn-submit">
                    <button type="button" id="btn-submit" class="btn btn-primary" onclick="boardWrite();">작성</button>
                    <a type="button" class="btn btn-primary" href="javascript:history.back()">취소</a>
                </div>
            </form>
        </div>
    </section>
</div>
<script type="text/javascript">


   function boardWrite (){
       let formsubmitSerialArray  = $('#boardwrite-form').serializeArray(); // serialize 사용
       console.log(formsubmitSerialArray);
       let formsubmit = JSON.stringify(objectifyForm(formsubmitSerialArray));

        console.log(formsubmit);
        $.ajax({
            type: 'post',
            url: '/api/board/save',
            dataType:"json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type","application/json");
                xhr.setRequestHeader("Authorization", "Bearer "+ token);
            },
            data: formsubmit,
        }).done(function(data){
            alert('글이 등록되었습니다.');
            console.log('<%=request.getContextPath()%>/api/board/save');
            <%--window.location.href = "<%=request.getContextPath()%>/board/boardDetail/${boardId}";--%>
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
       return false;
    }

/*   function objectifyForm(formArray) {  //serializeArray data function
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }*/


$(document).ready(function(){
    console.log(`${selectedBoardSubject}`);
    document.getElementById("board_writer").textContent=me.name;

})
</script>


