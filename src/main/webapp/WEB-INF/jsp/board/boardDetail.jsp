<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="${board.boardId}" class="board_wrap">
    <div class="board_content_box">
        <div class="board_header">
            <div class="board_title">
                <span> ${board.title}</span>
            </div>
            <div class="writer_info">
                <div>
                    <div class="view_profile_box">
<%--                        <c:choose>--%>
<%--                            <c:when test="${user.imgPath == NULL}">--%>
                                <img src="/resources/static/img/null_profile.png" class="view_profile article_profile"/>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                                <img src="${user.imgPath}">--%>
<%--                            </c:otherwise>--%>
<%--                        </c:choose>--%>
                    </div>
                    <div class="profile_area">
                        <div class="writer_nickname">
<%--                            <span class="user">${board.creater}</span>--%>
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
                ${board.contents}
            </div>
            <div class="comment_box">
            </div>
            <div class="comment_board">댓글</div>
        </div>
        <div class="comment_box">
            <ul class="comment_list">
                <c:forEach var="comment" items="${comment}">
                <div id="comment_area${comment.commentId}" class="comment_area">
<%--                    <img src="/{userId}/data?&imageFileName=${user.imgPath}"--%>
<%--                         class="view_profile ">--%>
                    <div class="comment_content">
                        <div class="comment_nick_box">
                                        <span class="comment_nickname member"
<%--                                              id="m${comment.userId}">${comment.creator}</span>--%>
                        </div>
                        <div class="comment_text_box">
                            <c:choose>
                                <c:when test="${comment.contents == 'NULL'}">
                                    <span class="text_comment delete_comment">삭제된 댓글입니다.</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="text_comment">${comment.contents}</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="comment_info_box">
                            <div>
                                <span class="comment_info_date">${comment.createdDate}</span>
                                <div class="comment_info_delete">
                                                <span class="comment_delete_button"
                                                      onclick="deletecomment(${comment.commentId})">삭제</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
            </ul>
        </div>
        <div class="comment_writer">
            <form id="commentForm" method="post" onsubmit="return false;">
                <input name="boardId" value="${board.boardId}" hidden />
<%--                <div class="comment_writer_name">${comment.creator}</div>--%>
                <textarea id="contents" name="contents" class="comment_write_input" placeholder="댓글을 남겨보세요"
                          onkeydown="resize(this)"></textarea>
                <input type="hidden" name="state" class="board-input"  value="S001"/>
                <div class="comment_writer_button">
                    <button class="btn btn-primary" onclick="writecomment();">등록</button>
                </div>
            </form>
        </div>
    </div>
    <div class="article_bottom_bar">
        <div class="article_bottom_left">
            <button class="btn btn-primary" onclick="modifyBoard()">수정</button>
            <button class="btn btn-primary" onclick="deleteBoard()">삭제</button>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function objectifyForm(formArray) {  //serializeArray data function
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }

 function writecomment() {
        let formsubmitSerialArray  = $('#commentForm').serializeArray(); // serialize 사용
        let formsubmit = JSON.stringify(objectifyForm(formsubmitSerialArray));
        console.log(formsubmit);
        $.ajax({
            type: 'post',
            url: '/api/comment/create',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: formsubmit,
        }).done(function(){
            alert('댓글이 등록되었습니다.');
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }

    function resize(obj) {
        obj.style.height = "0px";
        obj.style.height = (10+obj.scrollHeight)+"px";
    }

    $(document).ready(function(){
        console.log(me);
    });
</script>


