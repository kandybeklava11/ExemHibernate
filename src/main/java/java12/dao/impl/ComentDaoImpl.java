package java12.dao.impl;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.config.Config;
import java12.dao.ComentDao;
import java12.entity.BlogPost;
import java12.entity.Coment;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ComentDaoImpl implements ComentDao {
    private final EntityManagerFactory entityManagerFactory= Config.getEntity();
    @Override
    public String saveComent(Coment newComent) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(newComent);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "successfully saved !";
    }

    @Override
    public String removeComentById(long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Coment coment=entityManager.find(Coment.class,id);
            entityManager.remove(coment);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "successfully deleted !";
    }

    @Override
    public Coment getComentById(Long comentId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Coment coment=entityManager.find(Coment.class,comentId);
            entityManager.getTransaction().commit();
            return coment;
        }catch (Exception e){
           if( entityManager.getTransaction().isActive()){
               entityManager.getTransaction().rollback();
           }return null;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public String updateComentById(Long id, Coment coment) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Coment coment1=entityManager.find(Coment.class,id);
            coment1.setPublishDate(coment.getPublishDate());
            coment1.setCommentText(coment.getCommentText());
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "updated successfully !";
    }

    @Override
    public List<Coment> sortByPublishDate(String ascOrDest) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sortOrder = "asc".equalsIgnoreCase(ascOrDest) ? "asc" : "desc";
            String jpql = "SELECT c FROM Comment c ORDER BY c.publishDate " + sortOrder;
            List<Coment> query = entityManager.createQuery(jpql, Coment.class).getResultList();
            entityManager.getTransaction().commit();
            return query;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public Map<BlogPost, List<Coment>> groupByPost() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            List<Coment> comments = entityManager.createQuery(
                            "SELECT c FROM Coment c JOIN FETCH c.blogPost", Coment.class)
                    .getResultList();

            Map<BlogPost, List<Coment>> commentsByPost = comments.stream()
                    .collect(Collectors.groupingBy(Coment::getBlogPost));

            entityManager.getTransaction().commit();
            return commentsByPost;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return Collections.emptyMap();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public BlogPost assingCommentToBlogPost(Long blogPostId, Long commentId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();

            BlogPost blogPost = entityManager.find(BlogPost.class, blogPostId);
            Coment comment = entityManager.find(Coment.class, commentId);

            if (blogPost != null && comment != null) {
                blogPost.getComents().add(comment);
                comment.setBlogPost(blogPost);
            } else {
                throw new IllegalArgumentException("Blog post or comment not found");
            }

            entityManager.getTransaction().commit();
            return blogPost;
        } catch (Exception e) {
            throw new RuntimeException("Error assigning comment to blog post: " + e.getMessage(), e);
        }
    }
}
