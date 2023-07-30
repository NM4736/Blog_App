package com.blog_Application.Entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String email;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    Set<Post> posts;

    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    Set<Comment> comments;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
