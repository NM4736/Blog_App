package com.blog_Application.DAO;

import com.blog_Application.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>
{

    @Query("select p from Post p where p.user.id= :id")
    List<Post> getPostByUser(@Param(value="id") Integer id);

    @Query("select p from Post p where p.category.category_id= :id")
    List<Post> getPostByCategory(@Param(value="id") Integer id);
}
