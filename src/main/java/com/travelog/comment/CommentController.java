package com.travelog.comment;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travelog.comment.document.CommentDocument;
import com.travelog.comment.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommentController {
    private final CommentService commentService;

    String serverUrl = "https://08ee43bcd13e421fa3ea3f09f4485c5b.us-west-2.aws.found.io:443";
    String apiKey = "c2pSSmJJd0J6MEdTT0o4bmlLQ3c6YlZfQTlMRThUdy1Tc2l4bGlWbERQZw==";

    RestClient restClient = RestClient
            .builder(HttpHost.create(serverUrl))
            .setDefaultHeaders(new Header[]{
                    new BasicHeader("Authorization", "ApiKey " + apiKey)
            })
            .build();

    ElasticsearchTransport transport = new RestClientTransport(
            restClient, new JacksonJsonpMapper());

    ElasticsearchClient client = new ElasticsearchClient(transport);


    //댓글 조회 일단 OK
//    @GetMapping(value = "/{nickname}/{boardId}")
//    public ResponseEntity<?> getComments(@PathVariable String nickname, @PathVariable Long boardId){
//        List<Comment> comments = commentService.getComments(boardId);
//        return new ResponseEntity<>(CMRespDto.builder()
//                .isSuccess(true).msg("댓글 조회").body(comments).build(), HttpStatus.OK);
//    }
//    @Operation(summary = "특정 게시글의 댓글 전체 조회")
//    @GetMapping(value = "/{boardId}")
//    public List<Comment> getComments(@PathVariable Long boardId){
//        List<Comment> comments = commentService.getComments(boardId);
//        return comments;
//    }
    @Operation(summary = "특정 게시글의 댓글 전체 조회")
    @GetMapping(value = "/{boardId}")
    public ResponseEntity<?> getComments(@PathVariable Long boardId) throws IOException{

        SearchResponse<CommentDocument> search = client.search(s -> s
                        .index("sourcedb.comment.comment")
                        .query(q -> q
                                .match(t -> t
                                        .field("board_id")
                                        .query(boardId)
                                )),
                CommentDocument.class
        );
        List<CommentDocumentResDto> comments = new ArrayList<>();
        for (Hit<CommentDocument> hit: search.hits().hits()) {
            comments.add(new CommentDocumentResDto(hit.source().getComment_id(),
                        hit.source().getBoard_id(),
                        hit.source().getNickname(),
                        hit.source().getContent(),
                        hit.source().getCreated_at(),
                        hit.source().getUpdated_at(),
                        hit.source().getReport(),
                        hit.source().isStatus()));
        }
        log.info(comments.get(0).getContent());
        return new ResponseEntity<>(CMRespDto.builder()
                .isSuccess(true).msg("댓글 조회").body(comments).build(), HttpStatus.OK);

//        long comment_id=0;
//        long board_id=0;
//        String nickname="";
//        String content="";
//        //LocalDateTime createdAt=LocalDateTime.of(2017, Month.AUGUST,3,12,30);
//        //LocalDateTime updatedAt=LocalDateTime.of(2017, Month.AUGUST,3,12,30);
//        long createdAt = 0;
//        long updatedAt = 0;
//        int report=0;
//        boolean status=true;
//
//        for (Hit<CommentDocument> hit: search.hits().hits()) {
//            comment_id = hit.source().getComment_id();
//            board_id = hit.source().getBoard_id();
//            nickname = hit.source().getNickname();
//            content = hit.source().getContent();
//            createdAt = hit.source().getCreated_at();
//            updatedAt = hit.source().getUpdated_at();
//            report = hit.source().getReport();
//            status = hit.source().isStatus();
//        }
//        System.out.println(search.hits().hits().get(0));
//        JSONObject msg = new JSONObject();
//        msg.put("comment_id", comment_id);
//        msg.put("board_id", board_id);
//        msg.put("nickname", nickname);
//        msg.put("content", content);
//        msg.put("createdAt", createdAt);
//        msg.put("updatedAt", updatedAt);
//        msg.put("report", report);
//        msg.put("status", status);
//
//        return new ResponseEntity<>(msg.toString(), HttpStatus.OK);
    }

//    @Operation(summary = "댓글 작성")
//    //댓글 작성
//    @PostMapping(value = "/{nickname}/{boardId}")
//    public ResponseEntity<?> createComment(@RequestBody CommentReqDto commentReqDto,
//                                           @PathVariable String nickname, @PathVariable Long boardId){ //boardId, Member 받아와야함
//        CommentResDto comment = commentService.createComment(commentReqDto, boardId);
//        int commentSize = commentService.getComments(boardId).size();
//        BoardReqDto boardReqDto = new BoardReqDto(boardId, commentSize);
//        try{
//            commentSize = boardServiceFeignClient.updateCommentSize(boardReqDto);
//            log.info("info {}", ": " + commentSize);
//        } catch (FeignException e){
//            log.error("error {}", ": " + e.getMessage());
//        }
//        return new ResponseEntity<>(CMRespDto.builder().isSuccess(true).msg("댓글 저장 완료")
//                .body(comment).build(), HttpStatus.OK);
//    }
//
//    @Operation(summary = "댓글 수정")
//    @PutMapping(value = "/{nickname}/{boardId}/{commentId}")
//    public ResponseEntity<?> updateComment(@RequestBody CommentReqDto commentReqDto, @PathVariable String nickname,
//                                           @PathVariable Long boardId, @PathVariable Long commentId){
//        CommentResDto comment = commentService.updateComment(commentReqDto, boardId, commentId);
//        return new ResponseEntity<>(CMRespDto.builder().isSuccess(true).msg("수정 완료")
//                .body(comment).build(), HttpStatus.OK);
//    }
//
//    @Operation(summary = "댓글 삭제")
//    //댓글 삭제 일단 OK
//    @DeleteMapping(value = "/{nickname}/{boardId}/{commentId}")
//    public String deleteComment(HttpServletRequest request, @PathVariable String nickname,
//                                @PathVariable Long boardId, @PathVariable Long commentId){
//        commentService.deleteComment(boardId, commentId);
//        int commentSize = commentService.getComments(boardId).size();
//        BoardReqDto boardReqDto = new BoardReqDto(boardId, commentSize);
//        try{
//            commentSize = boardServiceFeignClient.updateCommentSize(boardReqDto);
//            log.info("info {}", ": " + commentSize);
//        } catch (FeignException e){
//            log.error("error {}", ": " + e.getMessage());
//        }
//        String referer = request.getHeader("Referer");
//        if(referer == null){
//            referer = "/" + nickname;
//        }
//        return "redirect:" + referer;
//    }

}
