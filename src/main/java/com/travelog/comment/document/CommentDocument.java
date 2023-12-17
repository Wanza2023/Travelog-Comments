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
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "sourcedb.comment.comment")
@Mapping(mappingPath = "elastic/comment-mapping.json")
@Setting(settingPath = "elastic/comment-settings.json")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommentDocument {
    @Id
    @Field(name = "comment_id", type = FieldType.Long)
    private Long comment_id;
    @Field(name = "board_id",type = FieldType.Long)
    private Long board_id;
    @Field(name = "nickname",type = FieldType.Text)
    private String nickname;
    @Field(name = "content",type = FieldType.Text)
    private String content;
    @Field(name = "created_at", type = FieldType.Date)
    private long created_at;
    @Field(name = "updated_at", type = FieldType.Date)
    private long updated_at;
    @Field(name = "report",type = FieldType.Integer)
    private int report; //default 0
    @Field(name = "status",type = FieldType.Boolean)
    private boolean status; //default 1
}

//, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis}
