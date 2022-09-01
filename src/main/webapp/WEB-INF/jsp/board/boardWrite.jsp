<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글쓰기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/static/css/boardWrite.css"/>
</head>
<div class="form">
    <h1 class="boardsubject-title">게시글 작성</h1>
    <section class="article-detail table-common con row">
        <div class="article-writer cell">
            <div class="user_info">
                <div class="profile_img">프로필</div>
                <span>작성자</span>
            </div>
            <form name="write" id="boardwrite-form">
                <span class="board-input">게시판 선택</span>
                <select name="boardSubjectId " class="board-select">
                    <c:forEach var="boardsubject" items="${boardSubjectBasic}">
                        <option value="${boardsubject.id}">${boardsubject.subject}</option>
                    </c:forEach>
                </select>
                <span class="subtitle">제목</span>
                <input name="title" class="board-input" placeholder="title" maxlength="60"/>
                <span class="subtitle">내용</span>
                <input name="state" class="board-input" placeholder="title" value="S001"/>
                <textarea name="contents" class="board-textarea" placeholder="content"></textarea>
                <div class="btn-submit">
                    <button id="btn-submit" class="btn btn-primary">작성</button>
                    <a type="button" class="btn btn-primary" href="javascript:history.back()">취소</a>
                </div>
            </form>
        </div>
    </section>
</div>
<script>

    $(document).ready(function () {
        $('#btn-submit').click($.write);
    });

    $.write = function () {
        let formsubmitSerialArray  = $('#boardwrite-form').serializeArray(); // serialize 사용
        let formsubmit = JSON.stringify(objectifyForm(formsubmitSerialArray));
        console.log(formsubmit);
        $.ajax({
            type: 'post',
            url: '/api/board/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: formsubmit,
        }).done(function(){
            alert('글이 등록되었습니다.');
            //window.location.href = "/board/boardDetail/${boardId}";
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


