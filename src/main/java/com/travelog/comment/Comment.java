package com.travelog.comment;

import com.travelog.comment.dto.CommentReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private Long boardId;
    @Column(nullable = false)
    private Long memberId;
    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private int report; //default 0
    private boolean status; //default 1

    @Builder
    public Comment(Long boardId, Long memberId, String nickname, String content, boolean status) {
        this.boardId = boardId;
        this.memberId = memberId;
        this.nickname = nickname;
        this.content = content;
        this.status = status;
    }

    public void updateComment(CommentReqDto commentReqDto){
        this.status = commentReqDto.isStatus();
        this.content = commentReqDto.getContent();
        this.nickname = commentReqDto.getNickname();
    }
}
