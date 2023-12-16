package com.travelog.comment.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "sourcedb.comment.comment")
@Mapping(mappingPath = "elastic/comment-mapping.json")
@Setting(settingPath = "elastic/comment-settings.json")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommentDocument {
    @Id
    private Long comment_id;
    @Field(type = FieldType.Long)
    private Long board_id;
    @Field(type = FieldType.Text)
    private String nickname;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Date)
    private LocalDateTime created_at;
    @Field(type = FieldType.Date)
    private LocalDateTime updated_at;
    @Field(type = FieldType.Integer)
    private int report; //default 0
    @Field(type = FieldType.Boolean)
    private boolean status; //default 1
}

//, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis}
