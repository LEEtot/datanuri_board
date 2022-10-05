<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="m_70 boardList-main">
    <div class="container mb-150 mt-3 myPage-userInfo">
        <div class="myprofile-img">프로필</div>
        <div class="myInfo">
            <h4 id="user-myname" class="ml_12"></h4>
            <h5 id="user-myrole" class="ml_12"></h5>

            <div class="myInfo-Cnt">
                <h5>내가 쓴 게시글 수 : <span id="myBoardsCnt"></span></h5>
            </div>

        </div>


    </div>


    <div class="user-myList">
        <div class="container mt-3">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-bs-toggle="tab" href="#home">내가 쓴 게시글</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="tab" href="#menu1">Menu 1</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="tab" href="#menu2">Menu 2</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div id="home" class="container tab-pane active"><br>
                    <div class="boardList_TB mt-12">
                        <ul class="boardlist-group-container">

                        </ul>

                    </div>





                    <div class="inner paging-wrap" >
                        <div class="paging pagination" id="paging"></div>
                    </div>





                </div>
                <div id="menu1" class="container tab-pane fade"><br>
                    <h3>Menu 1</h3>
                    <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                </div>
                <div id="menu2" class="container tab-pane fade"><br>
                    <h3>Menu 2</h3>
                    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    let creator = '';

    let pageCount = 5;
    let bysorting = "";
    let requestParam = {
        page : 0, //현재페이지
        size : 10, //한페이지당 요소들 수

    }

    let selectedPage = 1;
    let totalElements = 0; //전체요소들 수
    let totalPages = 1; //페이지 전체수

    let boardSubjectWriteAuth = '';


    function getBoardList(sorting){

        bysorting = sorting;
        $(".boardlist-group-container").empty();
        //requestParam.title = $("#search-title").val();

        console.log(creator);
        console.log(`<%=request.getContextPath()%>/api/board/listByCreator`);
        $.ajax({
            type:"get",
            url:`<%=request.getContextPath()%>/api/board/listByCreator`,
            data: requestParam,
            dataType : "json",

        }).done(function(data){
            console.log(data);
            $("#myBoardsCnt").html(data.totalElements);

            $.each(data.content,function(idx,item){
                var indexNum = data.totalElements - (data.pageable.offset) -idx;
                var list_group_item = "<li class='list-group-item boardList_tab'><div class='tab_item mr_12 tab_item_num'>"+indexNum+"</div><div class='tab_item tab_item_title'><a href='<%=request.getContextPath()%>/board/boardDetail/"+item.boardId+"'>"+item.title+"</a></div> <div class='tab_item mr_12 tab_item_recommend'>추천수 : "+item.recommendCount+"</div> <div class='tab_item mr_12 tab_item_viewCount'>조회수 : "+item.viewCount+"</div> <div class='tab_item tab_item_creator_modifier'>작성자 : "+item.creator+"</span> <br> 작성일 : <span> "+dateFomrmat(item.createdDate)+"</span>   수정일 : <span> "+dateFomrmat(item.modifiedDate)+"</span></div> </li>";
                $(".boardlist-group-container").append(list_group_item);
                //boardSubject_title=item.boardSubject.subject

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
            //requestPara
            // m.page = requestParam.size * (selectedPage - 1);
            requestParam.page = selectedPage -1;
            getBoardList(bysorting);
        }
    }

    $(document).ready(function(){
        requestParam.creator = me.sub;
        getBoardList("");

        $("#user-myname").html(me.name);
        if(me.auth == "R001"){
            role = "운영자";
        } else if(me.auth == "R002"){
            role = "관리자";
        } else if(me.auth =="R003"){
            role = "사용자";
        }
        $("#user-myrole").html(role);
    })
</script>