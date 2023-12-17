package com.travelog.comment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommentDocumentResDto {
    private Long commentId;
    private Long boardId;
    private String nickname;
    private String content;
    private long createdAt;
    private long updatedAt;
    private int report;
    private boolean status;

}
