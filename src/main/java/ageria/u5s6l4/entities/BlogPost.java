package ageria.u5s6l4.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class BlogPost {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID id;
    private String category;
    private String title;
    private String cover;
    private String contenuto;
    private int readingTime;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
