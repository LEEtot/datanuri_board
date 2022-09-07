<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="m_70 boardList-main">
    <h1 class="boardsubject-title">회원 관리</h1>

    <h3>관리자 목록</h3>
    <div class="boardList_tb" id="userList-R">
        <ul class="boardsubjectlist-group-container">
            <li class="list-group-item boardsubjectList-head">
                <div class="tab_item mr_12 tab_item_subjectnum">회원번호</div>
                <div class="tab_item mr_12 tab_item_subjecttitle">이메일</div>
                <div class="tab_item mr_12 tab_item_readAuth">이름</div>
                <div class="tab_item mr_12 tab_item_writeAuth">역할</div>
                <div class="tab_item mr_12 tab_item_boardsCount">핸드폰번호</div>
                <div class='tab_item mr_12 tab_item_subjectcreator'>생성자</div>
                <div class='tab_item mr_12 tab_item_createdDate'>생성일</div>
                <div class="tab_item mr_12 tab_item_modibtn"></div>
            </li>

           <%-- <c:forEach var="boardSubjectBasic" items="${boardSubjectBasic}">
                <li class="list-group-item boardsubjectList_tab">
                    <div class="tab_item mr_12 tab_item_subjectnum">${boardSubjectBasic.id}</div>
                    <div class="tab_item mr_12 tab_item_subjecttitle">${boardSubjectBasic.subject}</div>
                    <div class="tab_item mr_12 tab_item_readAuth">
                        <c:choose>
                            <c:when test="${boardSubjectBasic.readAuthority eq 'R001'}">일반유저</c:when>
                            <c:when test="${boardSubjectBasic.readAuthority eq 'R002'}">운영자</c:when>
                            <c:when test="${boardSubjectBasic.readAuthority eq 'R003'}">관리자</c:when>
                        </c:choose>
                    </div>
                    <div class="tab_item mr_12 tab_item_writeAuth">
                        <c:choose>
                            <c:when test="${boardSubjectBasic.writeAuthority eq 'R001'}">일반유저</c:when>
                            <c:when test="${boardSubjectBasic.writeAuthority eq 'R002'}">운영자</c:when>
                            <c:when test="${boardSubjectBasic.writeAuthority eq 'R003'}">관리자</c:when>
                        </c:choose>
                    </div>
                    <div class="tab_item mr_12 tab_item_boardsCount">${boardSubjectBasic.boardsCount}</div>
                    <div class='tab_item mr_12 tab_item_subjectcreator'>${boardSubjectBasic.creator}</div>
                    <div class='tab_item mr_12 tab_item_createdDate'>
                        <fmt:parseDate var="resultRegDt" value="${boardSubjectBasic.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" type="both"/>
                        <fmt:formatDate value="${resultRegDt}" pattern="yyyy-MM-dd HH:mm" />
                    </div>
                    <div class="tab_item mr_12 tab_item_modibtn"></div>
                </li>
            </c:forEach>--%>

        </ul>
    </div>


    <h3>일반회원 목록</h3>
    <%--<ul id="orderby" class="boardSubject_order">
        <li><a href="javascript:;" onclick="javascript:getBoardList('listLatest');" class="order-alink a-active">게시판 번호순</a></li>
        <li><a href="#" onclick="javascript:getBoardList('listRecommend');" class="order-alink">게시글 순</a></li>
    </ul>--%>
    <div class="boardList_tb" id="boardSubjectList_moditb">
        <ul class="boardsubjectlist-group-container">
            <li class="list-group-item boardsubjectList-head">
                <div class="tab_item mr_12 tab_item_subjectnum">게시판번호</div>
                <div class="tab_item mr_12 tab_item_subjecttitle">이름</div>
                <div class="tab_item mr_12 tab_item_readAuth">쓰기권한</div>
                <div class="tab_item mr_12 tab_item_writeAuth">읽기권한</div>
                <div class="tab_item mr_12 tab_item_boardsCount">게시글 수</div>
                <div class='tab_item mr_12 tab_item_subjectcreator'>생성자</div>
                <div class='tab_item mr_12 tab_item_subjectstate'>상태</div>
                <div class='tab_item mr_12 tab_item_createdDate'>생성일</div>
                <div class="tab_item mr_12 tab_item_modibtn"></div>
            </li>

            <%--<c:forEach var="boardSubject" items="${boardSubjectModi}">
                <li class="list-group-item boardsubjectList_tab">
                    <div class="tab_item mr_12 tab_item_subjectnum">${boardSubject.id}</div>
                    <div class="tab_item mr_12 tab_item_subjecttitle">${boardSubject.subject}</div>
                    <div class="tab_item mr_12 tab_item_readAuth">
                        <c:choose>
                            <c:when test="${boardSubject.readAuthority eq 'R001'}">일반유저</c:when>
                            <c:when test="${boardSubject.readAuthority eq 'R002'}">운영자</c:when>
                            <c:when test="${boardSubject.readAuthority eq 'R003'}">관리자</c:when>
                        </c:choose>
                    </div>
                    <div class="tab_item mr_12 tab_item_writeAuth">
                        <c:choose>
                            <c:when test="${boardSubject.writeAuthority eq 'R001'}">일반유저</c:when>
                            <c:when test="${boardSubject.writeAuthority eq 'R002'}">운영자</c:when>
                            <c:when test="${boardSubject.writeAuthority eq 'R003'}">관리자</c:when>
                        </c:choose>
                    </div>
                    <div class="tab_item mr_12 tab_item_boardsCount">${boardSubject.boardsCount}</div>
                    <div class='tab_item mr_12 tab_item_subjectcreator'>${boardSubject.creator}</div>
                    <div class='tab_item mr_12 tab_item_subjectstate'>
                        <c:choose>
                            <c:when test="${boardSubject.state eq 'S001'}">visible</c:when>
                            <c:when test="${boardSubject.state eq 'S002'}">nonvisible</c:when>
                        </c:choose>
                    </div>
                    <div class='tab_item mr_12 tab_item_createdDate'>
                        <fmt:parseDate var="resultRegDt" value="${boardSubject.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" type="both"/>
                        <fmt:formatDate value="${resultRegDt}" pattern="yyyy-MM-dd HH:mm" />
                    </div>
                    <div class="tab_item mr_12 tab_item_modibtn"><button class="modi-btn btn" type="button" data-bs-toggle="modal" data-bs-target="#myModalModi" onclick="modalModiOpen(`${boardSubject.subject}`,`${boardSubject.state}`,`${boardSubject.readAuthority}`,`${boardSubject.writeAuthority}`,`${boardSubject.id}`)">수정하기</button></div>
                </li>
            </c:forEach>--%>
        </ul>
    </div>
</div>

<script type="text/javascript">
    function getUserList(){
        $.ajax({
            url: '<%=request.getContextPath()%>/'
        })
    }
</script>