<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
    <h1 class="boardsubject-title">게시글 작성</h1>
    <section class="article-detail table-common con row">
        <div class="article-writer cell">
            <div class="user_info">
                <div class="profile_img">프로필</div>
                <span>작성자</span>
            </div>
            <form name="write" id="boardwrite-form">
                <span class="subtitle">게시판 선택</span>
                <select name="boardSubjectId" class="board-select">
                    <c:forEach var="boardsubject" items="${boardSubjectBasic}">
                        <option value="${boardsubject.id}">${boardsubject.subject}</option>
                    </c:forEach>
                </select>
                <span class="subtitle">제목</span>
                <input name="title" class="board-input" placeholder="title" maxlength="60"/>
                <span class="subtitle">내용</span>
                <textarea name="contents" class="board-textarea" placeholder="content"></textarea>
                <input name="state" class="board-input"  value="S001"/>
                <div class="btn-submit">
                    <button id="btn-submit" class="btn btn-primary" onclick="boardWrite();">작성</button>
                    <a type="button" class="btn btn-primary" href="javascript:history.back()">취소</a>
                </div>
            </form>
        </div>
    </section>
</div>
<script type="text/javascript">



   function boardWrite () {
       let formsubmitSerialArray  = $('#boardwrite-form').serializeArray(); // serialize 사용
       let formsubmit = JSON.stringify(objectifyForm(formsubmitSerialArray));
        console.log(formsubmit);
        $.ajax({
            type: 'post',
            url: '/api/board/save',
            dataType:"json",
            contentType : "application/json; charset=utf-8",
            data: formsubmit,
        }).done(function(data){
            alert('글이 등록되었습니다.');
            console.log('<%=request.getContextPath()%>/api/board/save');
            window.location.href = "/board/boardDetail/${boardId}";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }

    function objectifyForm(formArray) {  //serializeArray data function
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }

</script>


