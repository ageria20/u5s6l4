package ageria.u5s6l4.DTO;


import lombok.Data;

import java.util.UUID;


@Data
public class NewBlogPostDTO {

    private String category;
    private String title;
    private String cover;
    private String contenuto;
    private int readingTime;
    private UUID authorId;


}
