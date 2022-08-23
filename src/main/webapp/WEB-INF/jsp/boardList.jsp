<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width" , initial-scale="1">
    <title>boardproject</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/static/css/base.css" />
    <script src="/resources/static/js/jquery-3.6.0.js" ></script>
</head>
<body>
<c:import url="navbar.jsp"/>
<main class="main">
    <h3>공지게시판</h3>
    <button>새글 쓰기</button>

    <input type="text" placeholder="검색할 내용을 입력하세요">
    <button>확인</button>

    <ul id="orderby">
        <li><a>최신순</a></li>
        <li><a>추천순</a></li>
        <li><a>댓글순</a></li>
        <li><a>조회순</a></li>
    </ul>

    <div class="board_tb">
        <ul class="list-group-container">
            <li class="list-group-item board_tab">
                <div class="tab_item"><a href="#">글제목가아ㅓ라ㅓ</a></div>
                <div class="tab_item mr_12">추천수</div>
                <div class="tab_item mr_12">조회수</div>
                <div class="tab_item">작성자 : 김보영</span> <br> 작성일 : <span>2022년8월17일</span></div>
            </li>
            <li class="list-group-item board_tab">
                <div class="tab_item"><a href="#">글제목가아ㅓ라ㅓ</a></div>
                <div class="tab_item mr_12">추천수</div>
                <div class="tab_item mr_12">조회수</div>
                <div class="tab_item">작성자 : 김보영</span> <br> 작성일 : <span>2022년8월17일</span></div>
            </li>
            <li class="list-group-item board_tab">
                <div class="tab_item"><a href="#">글제목가아ㅓ라ㅓ</a></div>
                <div class="tab_item mr_12">추천수</div>
                <div class="tab_item mr_12">조회수</div>
                <div class="tab_item">작성자 : 김보영</span> <br> 작성일 : <span>2022년8월17일</span></div>
            </li>
            <li class="list-group-item board_tab">
                <div class="tab_item"><a href="#">글제목가아ㅓ라ㅓ</a></div>
                <div class="tab_item mr_12">추천수</div>
                <div class="tab_item mr_12">조회수</div>
                <div class="tab_item">작성자 : 김보영</span> <br> 작성일 : <span>2022년8월17일</span></div>
            </li>
            <li class="list-group-item board_tab">
                <div class="tab_item"><a href="#">글제목가아ㅓ라ㅓ</a></div>
                <div class="tab_item mr_12">추천수</div>
                <div class="tab_item mr_12">조회수</div>
                <div class="tab_item">작성자 : 김보영</span> <br> 작성일 : <span>2022년8월17일</span></div>
            </li>
        </ul>

    </div>

</main>
</body>
</html>