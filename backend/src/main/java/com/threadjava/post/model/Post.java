package com.threadjava.post.model;

import com.threadjava.comment.model.Comment;
import com.threadjava.image.model.Image;
import com.threadjava.db.BaseEntity;
import com.threadjava.postReactions.model.PostReaction;
import com.threadjava.users.model.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "posts")
@SQLDelete(sql = "UPDATE posts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Post extends BaseEntity {

    @NotBlank
    @Column(name = "body", columnDefinition="TEXT")
    private String body;

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostReaction> reactions = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;
}