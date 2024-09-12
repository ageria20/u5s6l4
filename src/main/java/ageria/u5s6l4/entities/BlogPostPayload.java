package ageria.u5s6l4.entities;


import lombok.Data;

import java.util.UUID;


@Data
public class BlogPostPayload {

    private String category;
    private String title;
    private String cover;
    private String contenuto;
    private int readingTime;
    private UUID authorId;


}
