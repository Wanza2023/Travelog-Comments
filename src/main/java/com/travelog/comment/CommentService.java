package com.travelog.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;

//    @Transactional(readOnly = true)
//    public List<Comment> getComments(Long boardId){
//        return commentRepository.findAllByBoardId(boardId);
//    }
//    @Transactional(readOnly = true)
//    public List<CommentDocument> getComments(Long boardId){
//        return commentSearchRepository.findAllByBoardId(boardId);
//}

//    @Transactional(readOnly = true)
//    public CommentResDto createComment(CommentReqDto commentReqDto, Long boardId){
//        Comment comment = Comment.builder()
//                .boardId(boardId)
//                .nickname(commentReqDto.getNickname())
//                .content(commentReqDto.getContent())
//                .status(commentReqDto.isStatus())
//                .build();
//
//        commentRepository.save(comment);
//        CommentResDto commentResDto = new CommentResDto(comment);
//        return commentResDto;
//    }
//    @Transactional
//    public CommentResDto updateComment(CommentReqDto commentReqDto, Long boardId, Long commentId){
//        Comment comment = commentRepository.findById(commentId)
//                .orElseThrow(()->new NoSuchElementException("해당 댓글이 존재하지 않습니다."));
//        comment.updateComment(commentReqDto);
//        return new CommentResDto(comment);
//    }
//
//    @Transactional(readOnly = true)
//    public void deleteComment(Long boardId, Long commentId){
//        commentRepository.deleteByBoardIdAndCommentId(boardId, commentId);
//    }

}
