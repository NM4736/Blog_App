package com.blog_Application.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int category_id;
    String categoryTitle;
    String categoryDescription;
    @OneToMany(mappedBy="category",cascade = CascadeType.ALL)
    Set<Post> posts;

    public String toString()
    {
        return "Category"+ category_id + "Category Name"+ categoryTitle;

    }}
