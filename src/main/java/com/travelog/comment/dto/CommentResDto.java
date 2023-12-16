package com.travelog.comment.dto;


import com.travelog.comment.Comment;
import com.travelog.comment.document.CommentDocument;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CommentResDto {
    private Long id;
    private Long boardId;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int report;
    private boolean status;

    public CommentResDto(Comment comment){
        this.id = comment.getId();
        this.boardId = comment.getBoardId();
        this.nickname = comment.getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.report = comment.getReport();
        this.status = comment.isStatus();
    }

//    public CommentResDto(long id, long boardId, String nickname, String content, LocalDateTime createdAt,
//                         LocalDateTime updatedAt, int report, boolean status){
//        this.id = id;
//        this.boardId = boardId
//        this.nickname = nickname;
//        this.content = content;
//        this.createdAt =
//        this.updatedAt = comment.getUpdatedAt();
//        this.report = comment.getReport();
//        this.status = comment.isStatus();
//    }

}
