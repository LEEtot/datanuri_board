<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${board.title}</title>
    <link rel="stylesheet" href="/resources/static/css/boarddetail.css"/>
</head>
<div id="${board.boardId}" class="board_wrap">
    <div class="board_content_box">
        <div class="board_header">
            <div class="board_title">
                <span><c:if test="${board.boardsubject != '없음'}">[${board.boardsubject}]</c:if> ${board.title}</span>
            </div>
            <div class="writer_info">
                <div>
                    <div class="view_profile_box">
                        <c:choose>
                            <c:when test="${user.imgPath == null}">
                                <img src="/resources/static/img/null_profile.png" class="view_profile article_profile"/>
                            </c:when>
                            <c:otherwise>
                                <img src=" ">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="profile_area">
                        <div class="writer_nickname">
                            <span id="m${user.userId}" class="user">${user.username}</span>
                        </div>
                        <div class="write_time">
                            <span>${board.createdDate}</span>
                        </div>
                    </div>
                </div>
                <div class="views">
                    <span>조회수 ${board.viewCount}</span>
                </div>
            </div>
        </div>
        <div class="board_container">
            <div class="board_viewer">
                ${article.content}
            </div>
            <div class="comment_box">
            </div>
                <div class="comment_board">댓글 ${comment}</div>
            </div>
            <div class="comment_box">
                <ul class="comment_list">
                    <c:forEach var="comment" items="${comments}" varStatus="status">
                            <div id="comment_area${reply.replyId}" class="comment_area">
                                <c:choose>
                                    <c:when test="${reply.profilePhoto == null}">
                                        <img src="/static/img/null_profile.png" class="view_profile">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="/{userId}/data?&imageFileName=${user.imgPath}"
                                             class="view_profile ">
                                        <%--<img id="profile${status.index}" src="" class="view_profile"/>--%>
                                        <%--<input type="hidden" id="imgValue${status.index}" value="${reply.memberId}:${reply.profilePhoto}"/>--%>
                                    </c:otherwise>
                                </c:choose>
                                <div class="comment_content">
                                    <div class="comment_nick_box">
                                        <span class="comment_nickname member"
                                              id="m${comment.userId}">${user.username}</span>
                                    </div>
                                    <div class="comment_text_box">
                                        <c:choose>
                                            <c:when test="${reply.content == 'NULL'}">
                                                <span class="text_comment delete_comment">삭제된 댓글입니다.</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text_comment">${comment.content}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="comment_info_box">
                                        <div>
                                            <span class="comment_info_date">${comment.createdDate}</span>
                                        <c:if test="${loginUser != null and (loginUser.userId == comment.userId or loginUser.userId == 0)}">
                                            <div class="comment_info_delete">
                                                <span class="comment_delete_button"
                                                      onclick="deletecomment(${comment.commentId})">삭제</span>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                    </c:forEach>
                </ul>
            </div>
            <c:if test="${loginUser != null}">
                <div class="comment_writer">
                    <form name="commentForm" method="post" onsubmit="return false;">
                        <div class="comment_writer_name">${loginUser.username}</div>
                        <textarea class="comment_write_input" placeholder="댓글을 남겨보세요"
                                  onkeydown="resize(this)"></textarea>
                        <div class="comment_writer_button">
                            <button class="button2" onclick="writeReply(0)">등록</button>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
        <div class="article_bottom_bar">
            <div class="article_bottom_left">
                    <button class="button1" onclick="modifyBoard()">수정</button>
                    <button class="button1" onclick="deleteBoard()">삭제</button>
            </div>
        </div>
    </div>
</div>
<script>

</script>


