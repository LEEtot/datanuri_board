<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<main class="main m_70">
    <div class="board_tb" id="board_tb1">
        <h3 class="boardsubject-title" id="boardsubject-title1"></h3>
        <ul class="list-group-container" id="list-container1">

        </ul>
    </div>

    <div class="board_tb" id="board_tb2">
        <h3  class="boardsubject-title" id="boardsubject-title2"></h3>
        <ul class="list-group-container"  id="list-container2">

        </ul>
    </div>

    <div class="board_tb" id="board_tb3">
        <h3 class="boardsubject-title" id="boardsubject-title3"></h3>
        <ul class="list-group-container"  id="list-container3">
        </ul>
    </div>


    <div class="board_tb" id="board_tb4">
        <h3  class="boardsubject-title" id="boardsubject-title4"></h3>
        <ul class="list-group-container"  id="list-container4">

        </ul>
    </div>
</main>
</body>

<script type="text/javascript">


    function getBoardSubjectTitle(subjectId){
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/api/boardSubject/select/"+subjectId,
            dataType:"json"
        }).done(function(data){
            return data.subject;
        })
    }


    function getBoardMain(){
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/api/board/mainList",
            dataType:"json"
        }).done(function(data){
            console.log(data);
            let value_index = 0;
            //let list_ul = document.getElementsByClassName("list-group-container");

            $(".list-group-container").empty();
            var li_index = 0;

            $.each(data,function(key1,value){
                //var listgroup = $(".list-group-container")[value_index];
                let item_index = 0;
                var list_container = "#list-container"+(li_index+1);
                var board_subject_title_id = "#boardsubject-title";
                var board_subject_title = "";
                $.each(value,function(key2,item){
                    //console.log(item);

                    var list_group_item = "<li class='list-group-item board_tab'><div class='tab_item tab_item_title'><a href='#'>"+item.title+"</a></div> <div class='tab_item mr_12 tab_item_recommend'>추천수 : "+item.recommendCount+"</div> <div class='tab_item mr_12 tab_item_viewCount'>조회수 : "+item.viewCount+"</div> <div class='tab_item tab_item_creator'>작성자 : "+item.creator+"</span> <br> 작성일 : <span>"+dateFomrmat(item.createdDate)+"</span></div> </li>";


                    //$(".list-group-container")[item_index].append(list_group_item);

                    $(list_container).append(list_group_item);
                    //console.log(item_index);
                    console.log(item.boardSubject.subject);
                    board_subject_title = item.boardSubject.subject;


                })
                board_subject_title_id = board_subject_title_id + (li_index + 1);
                //console.log(board_subject_title_id);
                //$(board_subject_title_id).append(board_subject_title);
                item_index+=1;
                li_index = li_index + 1;
                //console.log(item_index);
            })
        })
    }

    function getBoardSubjectTitles(){
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/api/boardSubject/list/S004",
            dataType:"json"
        }).done(function(data){
            var board_subject_title_id = "#boardsubject-title";
            $.each(data,function(idx,value){
                $(board_subject_title_id+(idx+1)).append(value.subject);
            })
        })
    }
    $(document).ready(function(){
        getBoardSubjectTitles();
        getBoardMain();
    });
</script>