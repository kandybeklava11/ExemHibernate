package java12.service;

import java12.entity.BlogPost;

import java.util.List;

public interface BlogPostService {
    String saveBlogPost(BlogPost newBlogPost);
    String removeBlogPostById(long id);
    List<BlogPost> getAllBlogPost();
    BlogPost getBlogPostById(Long blogPostId);
    String updateBlogPostById(Long id, BlogPost blogPost);
    BlogPost getBlogPostByKeyWord(String KeyWord);
}
