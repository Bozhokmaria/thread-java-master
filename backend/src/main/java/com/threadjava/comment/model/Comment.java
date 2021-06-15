package com.threadjava.comment.model;

import com.threadjava.db.BaseEntity;
import com.threadjava.post.model.Post;
import com.threadjava.users.model.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "comments")
@SQLDelete(sql = "UPDATE comments SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Comment extends BaseEntity {
    @NotBlank
    @Column(name = "body", columnDefinition="TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "post_id")
    private Post post;

    private boolean deleted = Boolean.FALSE;
}
