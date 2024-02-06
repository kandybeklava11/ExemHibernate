package java12.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java12.BaseEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

@Table(name="Coment")

public class Coment extends BaseEntity {
    @Column(name="comment_text")
    private String commentText;
    @Column(name = "publishf_date")
    private LocalDate publishDate;

    @ManyToOne
    private BlogPost blogPost;

    public Coment(String commentText, LocalDate publishDate) {
        this.commentText = commentText;
        this.publishDate = publishDate;
    }


}
