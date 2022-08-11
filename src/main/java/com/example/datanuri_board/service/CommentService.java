package com.example.datanuri_board.service;

import com.example.datanuri_board.dto.CommentDto;
import com.example.datanuri_board.dto.response.BoardSubjectResponseDto;
import com.example.datanuri_board.entity.Comment;
import com.example.datanuri_board.entity.Form.ActivityForm;
import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(CommentDto commentDto, BoardSubjectResponseDto board, User author){
        Comment newComment = new Comment();

        if(commentDto.getCommentId()== null){
            newComment.setLevel(1);
        }

        else{
            long supCommentId = Long.parseLong(commentDto.getCommentId());
            Comment supComment = commentRepository.findById(supCommentId);

            if(!supComment.getLive()){
                throw new RuntimeException("SuperComment is already dead");
            }
            newComment.setLevel(supComment.getLevel()+1);
            newComment.setSuperComment(supComment);
            supComment.getSubComment().add(newComment);
        }

        newComment.setContent(commentDto.getContent().replace("\r\n", "<br>"));
        newComment.setBoard(board);

        newComment.setAuthor(author.getName());
        newComment.setAuthor(author.getCreator());

        newComment.setLive(true);
        commentRepository.save(newComment);
    }

    @Transactional
    public void deleteComment(long commentId){

        Comment commentToDelete = commentRepository.findById(commentId);

        if(commentToDelete.getSubComment().size()==0){
            while(commentToDelete!=null){
                Comment superComment = commentToDelete.getSuperComment();
                if(superComment ==null){
                    commentRepository.deleteById(commentToDelete.getId());
                    break;
                }
                superComment.getSubComment().remove(commentToDelete);
                commentRepository.deleteById(commentToDelete.getId());
                if(superComment.getSubComment().size()==0 && !superComment.getLive()){
                    commentToDelete = superComment;
                }
                else{ break; }
            }
        }
        else if(commentToDelete!=null){
            commentToDelete.setContent("삭제된 댓글입니다.");
            commentToDelete.setLive(false);
        }
    }

    public Comment findById(long id){
        return commentRepository.findById(id);
    }

    @Transactional
    public void editComment(long id, String content){
        Comment comment = commentRepository.findById(id);
        comment.setContent(content);
    }

    public ActivityForm getActivityComment(String owner, int page, int postForPage){
        List<Comment> commentList = commentRepository.getCommentByOwner(owner);

        int totalCnt = commentList.size();
        int from;
        int max;

        if((totalCnt-(page * postForPage))>=0){
            from = totalCnt-(page * postForPage);
            max = postForPage;
        }
        else{
            from = 0;
            max = totalCnt % postForPage;
        }

        List resultList = new ArrayList();

        for(int i=0; i<max; i++){
            resultList.add(commentList.get(from+max-i-1));
        }

        ActivityForm resultTuple = new ActivityForm(resultList, totalCnt);

        return resultTuple;
    }
}
