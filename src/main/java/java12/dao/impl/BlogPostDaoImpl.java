package java12.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java12.config.Config;
import java12.dao.BlogPostDao;
import java12.entity.BlogPost;

import java.util.Collections;
import java.util.List;

public class BlogPostDaoImpl implements BlogPostDao {
    private final EntityManagerFactory entityManagerFactory= Config.getEntity();
    @Override
    public String saveBlogPost(BlogPost newBlogPost) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(newBlogPost);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                return e.getMessage();
            } finally {
                entityManager.close();

            }
            return "BlogPost saved successfully !";

        }


    @Override
    public String removeBlogPostById(long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost blogPost = entityManager.find(BlogPost.class, id);
            entityManager.remove(blogPost);
            entityManager.getTransaction().commit();
            return "BlogPost deleted successfully!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            entityManager.close();

        }
    }

    @Override
    public List<BlogPost> getAllBlogPost() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<BlogPost> blogPosts = entityManager.createQuery("select b from BlogPost b", BlogPost.class).getResultList();
            entityManager.getTransaction().commit();
            return blogPosts;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error retrieving all blog posts: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public BlogPost getBlogPostById(Long blogPostId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost blogPost = entityManager.find(BlogPost.class,blogPostId);
            entityManager.getTransaction().commit();
            return blogPost;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String updateBlogPostById(Long id, BlogPost blogPost) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost blogPost1 = entityManager.find(BlogPost.class, id);
            blogPost1.setPublichDate(blogPost.getPublichDate());
            blogPost1.setTitle(blogPost.getTitle());
            blogPost1.setContent(blogPost.getContent());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return e.getMessage();
        } finally {
            if(entityManager.isOpen()){
                entityManager.close();
            }

        }
        return "BlogPost updated successfully !";
    }

    @Override
    public BlogPost getBlogPostByKeyWord(String KeyWord) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("FROM BlogPost WHERE content LIKE :keyword", BlogPost.class)
                    .setParameter("keyword", "%" + KeyWord + "%")
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving blog post by keyword: " + e.getMessage(), e);
        }

    }
}
