package java12.service.impl;

import java12.dao.BlogPostDao;
import java12.dao.impl.BlogPostDaoImpl;
import java12.entity.BlogPost;
import java12.service.BlogPostService;

import java.util.List;

public class BlogPostServiceImpl implements BlogPostService {
     private final BlogPostDao blogPostDao=new BlogPostDaoImpl();
    @Override
    public String saveBlogPost(BlogPost newBlogPost) {
        return blogPostDao.saveBlogPost(newBlogPost);
    }

    @Override
    public String removeBlogPostById(long id) {
        return blogPostDao.removeBlogPostById(id);
    }

    @Override
    public List<BlogPost> getAllBlogPost() {
        return blogPostDao.getAllBlogPost();
    }

    @Override
    public BlogPost getBlogPostById(Long blogPostId) {
        return blogPostDao.getBlogPostById(blogPostId);
    }

    @Override
    public String updateBlogPostById(Long id, BlogPost blogPost) {
        return blogPostDao.updateBlogPostById(id,blogPost);
    }

    @Override
    public BlogPost getBlogPostByKeyWord(String KeyWord) {
        return blogPostDao.getBlogPostByKeyWord(KeyWord);
    }
}
