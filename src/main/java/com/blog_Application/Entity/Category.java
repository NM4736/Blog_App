package com.blog_Application.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int category_id;
    String categoryTitle;
    String categoryDescription;

}
