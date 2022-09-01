<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="m_70 boardList-main">
    <h1 class="boardsubject-title">게시판 관리</h1>

    <div class="boardsubject_btn"><button class="btn write-btn m_12" type="button" data-bs-toggle="modal" data-bs-target="#myModal">게시판 추가</button></div>



    <h3>게시판 목록(기본)</h3>
    <div class="boardList_tb" id="boardSubjectList_basictb">
        <ul class="boardsubjectlist-group-container">
            <li class="list-group-item boardsubjectList-head">
                <div class="tab_item mr_12 tab_item_subjectnum">게시판번호</div>
                <div class="tab_item mr_12 tab_item_subjecttitle">이름</div>
                <div class="tab_item mr_12 tab_item_readAuth">쓰기권한</div>
                <div class="tab_item mr_12 tab_item_writeAuth">읽기권한</div>
                <div class="tab_item mr_12 tab_item_boardsCount">게시글 수</div>
                <div class='tab_item mr_12 tab_item_subjectcreator'>생성자</div>
                <div class='tab_item mr_12 tab_item_createdDate'>생성일</div>
                <div class="tab_item mr_12 tab_item_modibtn"></div>
            </li>

            <c:forEach var="boardSubjectBasic" items="${boardSubjectBasic}">
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
            </c:forEach>

        </ul>
    </div>


    <h3>게시판 목록(수정가능)</h3>
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

            <c:forEach var="boardSubject" items="${boardSubjectModi}">
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
            </c:forEach>
        </ul>
    </div>


    <!-- The Modal -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
    <form action="<%=request.getContextPath()%>/boardsubject/save" method="post">


                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-head">게시판 추가</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modalModi"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="modal-item">
                        <label><span class="modal-title">게시판 이름</span> <br>
                        <input class="input-subjecttitle" type="text" name="subject" placeholder="게시판 이름 입력"></label>
                    </div>

                    <div class="modal-item">
                        <fieldset>
                            <legend class="modal-title">읽기 권한</legend>

                            <input type="radio" id="readAuth_R001" name="readAuthority" value="R001" checked>
                            <label for="readAuth_R001">일반유저</label>
                            <input type="radio" id="readAuth_R002" name="readAuthority" value="R002">
                            <label for="readAuth_R002">운영자</label>
                            <input type="radio" id="readAuth_R003" name="readAuthority" value="R003">
                            <label for="readAuth_R003">관리자</label>
                        </fieldset>
                    </div>

                    <div class="modal-item">
                        <fieldset>
                            <legend class="modal-title">쓰기 권한</legend>

                            <input type="radio" id="writeAuth_R001" name="writeAuthority" value="R001" checked>
                            <label for="writeAuth_R001">일반유저</label>
                            <input type="radio" id="writeAuth_R002" name="writeAuthority" value="R002">
                            <label for="writeAuth_R002">운영자</label>
                            <input type="radio" id="writeAuth_R003" name="writeAuthority" value="R003">
                            <label for="writeAuth_R003">관리자</label>
                        </fieldset>
                    </div>
                    <input name="state" value="S001" hidden>

                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn write-btn m_12">적용하기</button>
                </div>
    </form>
            </div>
        </div>
    </div>
    <!--Modal 끝-->


    <!-- The Modal -->
    <div class="modal" id="myModalModi">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="<%=request.getContextPath()%>/boardsubject/update" method="post">


                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-head">게시판 수정</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="modal-item">
                            <label><span class="modal-title">게시판 이름</span> <br>
                                <input class="input-subjecttitle" type="text" name="subject" id="modal-modititle"></label>
                        </div>

                        <div class="modal-item">
                            <fieldset>
                                <legend class="modal-title">읽기 권한</legend>

                                <input type="radio" id="readAuth2_R001" name="readAuthority" value="R001">
                                <label for="readAuth2_R001">일반유저</label>
                                <input type="radio" id="readAuth2_R002" name="readAuthority" value="R002">
                                <label for="readAuth2_R002">운영자</label>
                                <input type="radio" id="readAuth2_R003" name="readAuthority" value="R003">
                                <label for="readAuth2_R003">관리자</label>
                            </fieldset>
                        </div>

                        <div class="modal-item">
                            <fieldset>
                                <legend class="modal-title">쓰기 권한</legend>

                                <input type="radio" id="writeAuth2_R001" name="writeAuthority" value="R001">
                                <label for="writeAuth2_R001">일반유저</label>
                                <input type="radio" id="writeAuth2_R002" name="writeAuthority" value="R002">
                                <label for="writeAuth2_R002">운영자</label>
                                <input type="radio" id="writeAuth2_R003" name="writeAuthority" value="R003">
                                <label for="writeAuth2_R003">관리자</label>
                            </fieldset>
                        </div>
                        <div class="modal-item">
                            <fieldset>
                                <legend class="modal-title">상태</legend>

                                <input type="radio" id="state_S001" name="state" value="S001">
                                <label for="state_S001">visible</label>
                                <input type="radio" id="state_S002" name="state" value="S002">
                                <label for="state_S002">nonvisible</label>
                            </fieldset>
                        </div>

                            <input name="id" hidden>


                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn write-btn m_12">적용하기</button>
                        <button id="btn-delete" class="btn write-btn m_12" type="button">삭제하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--Modal 끝-->

</div>

<script>

    function modalModiOpen(subject, state, readAuth, writeAuth, id){
        console.log(id);

        $("input[id='readAuth2_"+readAuth+"']").attr("checked","checked");
        $("input[id='writeAuth2_"+writeAuth+"']").attr("checked","checked");
        $("input[id ='modal-modititle']").val(subject);
        $("input[id='state_"+state+"']").attr("checked","checked");
        $("input[name='id']").val(id);
        $("#btn-delete").on('click',function(){
            location.href='<%=request.getContextPath()%>/boardsubject/delete?boardsubjectId='+id;
        })
    }
    $(document).ready(function(){
        console.log(`${boardSubjectModi}`);


    })


</script>