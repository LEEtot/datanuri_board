<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="m_70 boardList-main">
    <h1 class="boardsubject-title">게시판 관리</h1>


    <div class="search-div">
        <input type="text" placeholder="검색할 제목을 입력하세요" class="search-input m_12" id="search-title">
        <button class="btn ok-btn m_12" onclick="getBoardListBySearch()">찾기</button>
        <button class="btn write-btn m_12">새글 쓰기</button>
    </div>




    <ul id="orderby">
        <li><a href="javascript:;" onclick="javascript:getBoardList('listLatest');" class="order-alink a-active">최신순</a></li>
        <li><a href="#" onclick="javascript:getBoardList('listRecommend');" class="order-alink">추천순</a></li>
        <li><a href="#" onclick="javascript:getBoardList('listView');" class="order-alink">조회순</a></li>
    </ul>

    <div class="boardList_tb">
        <ul class="boardlist-group-container">

        </ul>

    </div>





</div>