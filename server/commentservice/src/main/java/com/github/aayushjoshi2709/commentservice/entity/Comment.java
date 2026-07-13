package com.github.aayushjoshi2709.commentservice.entity;


import com.github.aayushjoshi2709.commentservice.entity.common.Common;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Entity
@Table(name="comment")
public class Comment extends Common {
    @Column(name="comment", length=500, nullable = false)
    private String comment;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private User user;

    @Column(name= "likeCount", nullable = false)
    private Integer LikeCount = 0;

    @Column(name= "likeCount", nullable = false)
    private Integer dislikeCount = 0;

}
