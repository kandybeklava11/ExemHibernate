package java12.service;

import java12.entity.BlogPost;
import java12.entity.Coment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    String saveComent(Coment newComent);
    String removeComentById(long id);
    Coment getComentById(Long comentId);
    String updateComentById(Long id, Coment coment);
    List<Coment> sortByPublishDate(String ascOrDest);
    Map<BlogPost, List<Coment>> groupByPost();
    BlogPost assingCommentToBlogPost(Long blogPostId,Long commentId);
}
