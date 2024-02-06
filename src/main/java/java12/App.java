package java12;

import java12.entity.BlogPost;
import java12.entity.Coment;
import java12.service.BlogPostService;
import java12.service.CommentService;
import java12.service.impl.BlogPostServiceImpl;
import java12.service.impl.CommentServiceImpl;

import java.time.LocalDate;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BlogPostService blogPostService=new BlogPostServiceImpl();
        CommentService commentService=new CommentServiceImpl();
        //blogPostService.saveBlogPost(new BlogPost("qqqqqqqq","wwwwwwwwwww", LocalDate.of(1999,9,9)));
        //System.out.println(blogPostService.getAllBlogPost());
        //System.out.println(blogPostService.getBlogPostById(1L));
        //System.out.println(blogPostService.updateBlogPostById(1L,new BlogPost("qqqqqqqqq","eeeeeeeeeeee","1777-02-23")));
        //System.out.println(blogPostService.getBlogPostByKeyWord("eeeeeeeeeee"));
        //System.out.println(blogPostService.removeBlogPostById(1L));

        //System.out.println(commentService.saveComent(new Coment("so hottttttt", LocalDate.of(1999,8,10))));
        //System.out.println(commentService.getComentById(2L));
        //System.out.println(commentService.updateComentById(1L,new Coment("sdd",LocalDate.of(1222,3,9))));
        //System.out.println(commentService.assingCommentToBlogPost(1L,52L));
        //System.out.println(commentService.removeComentById(52L));
        System.out.println(commentService.groupByPost());
    }
}
