package com.travelog.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchCondition {
    private Long id;
    private Long boardId;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int report;
    private boolean status;
}

//@Getter
//@Setter
//public class SearchCondition {
//
//    private Long id;
//
//    private String name;
//
//    private String nickname;
//
//    private int age;
//
//    private Status status;
//
//    private Long zoneId;
//
//}