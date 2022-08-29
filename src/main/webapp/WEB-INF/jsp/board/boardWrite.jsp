<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 글쓰기</title>
    <script src="/resources/static/js/jquery-3.6.0.js" ></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/boardWrite.css" />
</head>
<body>
<div class="wrap">
    <c:import url="../navbar.jsp"/>
    <div class="form">
        <form name="write" onsubmit="return false;">
            <span class="subtitle">게시판 이름</span>
            <select name="boardsubject " class="board-select">
                <c:forEach var="boardsubject" items="${boardsubjects}">
                    <c:choose>
                        <c:when test="${boardsubject.boardSubjectId == 0}">
                            <c:if test="${loginUser.userId == 0}">
                                <option value="${boardsubject.boardsubjectId}">${boardsubject.boardSubjectName}</option>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <option value="${boardsubject.boardsubjectId}">${boardsubject.boardSubjectName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <span class="subtitle">제목</span>
            <input name="title" class="board-input" placeholder="title" maxlength="60"/>
            <span class="subtitle">내용</span>
            <textarea name="content" class="board-textarea" placeholder="content"></textarea>
            <div class="btn-submit">
                <button id="btn-submit" class="button1">작성</button>
                <button id="btn-back" class="button1">취소</button>
            </div>
        </form>
    </div>
</div>
<script src="/static/js/boardwrite.js"></script>
</body>
</html>