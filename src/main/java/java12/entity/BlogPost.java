package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;

import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="BlogPost")
public class BlogPost extends BaseEntity {
    private String title;
    private String content;
    @Column(name="publish_date")
    private LocalDate publichDate;

    @OneToMany(mappedBy = "blogPost",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Coment> coments;

    public BlogPost(String title, String content, LocalDate publichDate) {
        this.title = title;
        this.content = content;
        this.publichDate = publichDate;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publichDate=" + publichDate +
                '}';
    }
}
