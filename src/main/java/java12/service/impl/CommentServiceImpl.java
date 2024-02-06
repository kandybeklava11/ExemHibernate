package java12.service.impl;

import java12.dao.ComentDao;
import java12.dao.impl.ComentDaoImpl;
import java12.entity.BlogPost;
import java12.entity.Coment;
import java12.service.CommentService;

import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService {
    private final ComentDao comentDao=new ComentDaoImpl();
    @Override
    public String saveComent(Coment newComent) {
        return comentDao.saveComent(newComent);
    }

    @Override
    public String removeComentById(long id) {
        return comentDao.removeComentById(id);
    }

    @Override
    public Coment getComentById(Long comentId) {
        return comentDao.getComentById(comentId);
    }

    @Override
    public String updateComentById(Long id, Coment coment) {
        return comentDao.updateComentById(id,coment);
    }

    @Override
    public List<Coment> sortByPublishDate(String ascOrDest) {
        return comentDao.sortByPublishDate(ascOrDest);
    }

    @Override
    public Map<BlogPost, List<Coment>> groupByPost() {
        return comentDao.groupByPost();
    }

    @Override
    public BlogPost assingCommentToBlogPost(Long blogPostId, Long commentId) {
        return comentDao.assingCommentToBlogPost(blogPostId, commentId);
    }
}
