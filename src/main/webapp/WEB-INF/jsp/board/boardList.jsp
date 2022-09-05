<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="m_70 boardList-main">
    <h1 class="boardsubject-title">${boardSubject.subject}</h1>


    <div class="search-div">
        <input type="text" placeholder="검색할 제목을 입력하세요" class="search-input m_12" id="search-title">
        <button class="btn ok-btn m_12" onclick="getBoardListBySearch()">찾기</button>
        <button class="btn write-btn m_12" onclick="boardInsert()">새글 쓰기</button>

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





    <div class="inner paging-wrap" >
        <div class="paging pagination" id="paging"></div>
    </div>

</div>

<script type="text/javascript">

    let boardSubjectId = `${boardSubjectId}`
    let pageCount = 5;
    let bysorting = "";
    let requestParam = {
        page : 0, //현재페이지
        size : 10, //한페이지당 요소들 수
        title : ""
    }

    let selectedPage = 1;
    let totalElements = 0; //전체요소들 수
    let totalPages = 1; //페이지 전체수

    let boardSubjectWriteAuth = '';

    //게시판TITLE값 리턴
    /*function getBoardSubjectTitle(subjectId){
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/api/boardSubject/select/"+subjectId,
            dataType:"json"
        }).done(function(data){
            return data.subject;
        })
    }*/
    //날짜포맷
    function dateFomrmat(date){
        var dateString = date.replace("T"," ").replace(/\..*/,'');
        return dateString;
    }

    function getBoardListBySearch(){
        requestParam.page =0;
        selectedPage = 1;
        getBoardList(bysorting);
    }

    function boardInsert() {
        console.log(boardSubjectWriteAuth);

            if(me.auth == "R001"){
                //getBoardList("listLatest");
            }else if(me.auth == "R002" && boardSubjectWriteAuth !== "R001"){
                //getBoardList("listLatest");
            } else if(me.auth == "R003" && boardSubjectWriteAuth == "R003"){
                //getBoardList("listLatest");
            } else {
                alert("해당게시판에 글쓰기권한이 없습니다!");
                return false;
            }
        //location.href = "/board/boardWrite";
    }


    function getBoardList(sorting){
        console.log(boardSubjectId);
        bysorting = sorting;
        console.log(`<%=request.getContextPath()%>/api/board/`+bysorting+`/${boardSubjectId}`);
        $(".boardlist-group-container").empty();
        requestParam.title = $("#search-title").val();

        var boardSubject_title = "";
        $.ajax({
            type:"get",
            url:`<%=request.getContextPath()%>/api/board/`+bysorting+`/${boardSubjectId}`,
            data: requestParam,
            dataType : "json",

        }).done(function(data){
            console.log(data);
            $.each(data.content,function(idx,item){
                var indexNum = data.totalElements - (data.pageable.offset) -idx;

                var list_group_item = "<li class='list-group-item boardList_tab'><div class='tab_item mr_12 tab_item_num'>"+indexNum+"</div><div class='tab_item tab_item_title'><a href='#'>"+item.title+"</a></div> <div class='tab_item mr_12 tab_item_recommend'>추천수 : "+item.recommendCount+"</div> <div class='tab_item mr_12 tab_item_viewCount'>조회수 : "+item.viewCount+"</div> <div class='tab_item tab_item_creator_modifier'>작성자 : "+item.creator+"</span> <br> 작성일 : <span> "+dateFomrmat(item.createdDate)+"</span>   수정일 : <span> "+dateFomrmat(item.modifiedDate)+"</span></div> </li>";
                $(".boardlist-group-container").append(list_group_item);
                boardSubject_title=item.boardSubject.subject

            })


            totalElements = data.totalElements;
            totalPages = data.totalPages;

            paging(totalElements, requestParam.size, pageCount, selectedPage);

            //list a링크 active
            var order_alinks = document.getElementsByClassName("order-alink");
            $.each(order_alinks,function(idx,link){
                console.log(link);
                link.classList.remove("a-active");

            })
            if(sorting=="listLatest"){
                order_alinks[0].classList.add("a-active")
            }else if(sorting=="listRecommend"){
                order_alinks[1].classList.add("a-active")
            }else if(sorting=="listView"){
                order_alinks[2].classList.add("a-active")
            }

        })



    }


    /*페이징처리 관련*/

    function paging(totalData, dataPerPage, pageCount, currentPage){
        $("#paging").empty();

        var totalPage = Math.ceil(totalData/dataPerPage);
        if(0==totalPage){
            totalPage = 1;
        }

        var pageGroup = Math.ceil(currentPage/pageCount);

        var last = pageGroup * pageCount;
        if(last>totalPage){
            last = totalPage;
        }


        var first = last - pageCount + 1;

        if(first<=0){
            first = 1;
        }

        var next = last +1;
        var prev = first -1;

        var pagingList = '<a href="javascript:;" id="first" class="first page-link" onclick="javascript:clickPagination(' + '\'first\'' + ');">맨처음</a>';


        if(prev > 0){
            pagingList += '<a href="javascript:;" id="prev" class="prev page-link" onclick="javascript:clickPagination(' + '\'prev\'' + ');">이전</a>';
        }

        for(var i =first; i<=last;i++){
            if(i==currentPage){
                pagingList += '<a id="' + i + '" class="num current page-link active">' + i + '</a>';
            } else{
                pagingList += '<a href="javascript:;" id='+ i +' class="num page-link" onclick="javascript:clickPagination(' + i + ');">'+i+'</a>';
            }

        }

        if(last<totalPage){
            pagingList += '<a href="javascript:;" id="next" class="next page-link" onclick="javascript:clickPagination(' + '\'next\'' + ');">다음</a>';
        }

        pagingList += '<a href="javascript:;" id="last" class="last page-link" onclick="javascript:clickPagination(' + '\'last\'' + ');">맨끝</a>';
        $("#paging").append(pagingList);
    }

    function clickPagination(clickId){
        if(clickId == "first"){
            selectedPage = 1;
            requestParam.page = 0;
            getBoardList(bysorting);
        } else if(clickId == "prev"){
            var pageGroup = Math.ceil(selectedPage/pageCount);
            if(pageGroup == 1){
                selectedPage = 1;
                requestParam.page = 0;
            } else{
                pageGroup = pageGroup - 1;
                selectedPage = selectedPage - pageCount;
                requestParam.page = requestParam.size * (selectedPage-1);
            }
            getBoardList(bysorting);
        } else if(clickId == "next"){
            //var totalPage = Math.ceil(totalElements/requestParam.size);
            var totalPage = totalPages;
            var pageGroup = Math.ceil(selectedPage/pageCount);
            if((selectedPage+pageCount)>=totalPage){
                selectedPage = totalPage;
                requestParam.page = requestParam.size * (totalPage -1);
            } else{
                pageGroup = pageGroup + 1;
                selectedPage = selectedPage + pageCount;
                requestParam.page = requestParam.size * (selectedPage -1);
            }
            getBoardList(bysorting);
        } else if(clickId == "last"){
            //var totalPage = Math.ceil(totalElements/requestParam.size);
            var totalPage = totalPages;
            var pageGroup = Math.ceil(selectedPage/pageCount);
            selectedPage = totalPage;
            //requestParam.page = (totalPage - 1) * requestParam.size -1;
            requestParam.page = selectedPage -1;

            getBoardList(bysorting);
        }else {
            selectedPage = clickId;
            //requestParam.page = requestParam.size * (selectedPage - 1);
            requestParam.page = selectedPage -1;
            getBoardList(bysorting);
        }
    }

    $(document).ready(function(){
        //console.log(me.auth);
        let boardSubjectReadAuth = `${boardSubject.readAuthority}`;
        boardSubjectWriteAuth =`${boardSubject.writeAuthority}`;
        console.log(boardSubjectReadAuth);
        console.log()
        if(me.auth == "R001"){
            getBoardList("listLatest");
        }else if(me.auth == "R002" && boardSubjectReadAuth !== "R001"){
            getBoardList("listLatest");
        } else if(me.auth == "R003" && boardSubjectReadAuth == "R003"){
            getBoardList("listLatest");
        } else {
            alert("해당글에 접근권한이 없습니다!");
            location.replace("/");
        }



        //paging(totalElements, requestParam.size, totalPages, requestParam.page+1);
    })
</script>