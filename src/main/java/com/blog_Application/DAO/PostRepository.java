package com.blog_Application.DAO;

import com.blog_Application.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>
{

    @Query("select p from Post p where p.user.id= :id")
    Page<Post> getPostByUser(@Param(value="id") Integer id, Pageable page);

    @Query("select p from Post p where p.category.category_id= :id")
    Page<Post> getPostByCategory(@Param(value="id") Integer id,Pageable page);

    @Query("select p from Post p where p.postTitle LIKE %:word%")
    List<Post> findPostByTitleKeyword(@Param(value="word") String word);
}
