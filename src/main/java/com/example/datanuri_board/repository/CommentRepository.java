package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public Comment findById(long id){
        return em.find(Comment.class, id);
    }

    public void save(Comment comment){
        if(comment.getId()== null){
            em.persist(comment);
        }
        else{
            em.merge(comment);
        }
    }

    public void deleteById(long id){
        em.remove(findById(id));
    }

    public List<Comment> getCommentByOwner(String author){
        List<Comment> selectedPosts = em.createQuery("select c from Comment c where c.author =:author",Comment.class)
                .setParameter("author", author)
                .getResultList();
        return selectedPosts;
    }
}
