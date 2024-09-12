package ageria.u5s6l4.services;

import ageria.u5s6l3.entities.Author;
import ageria.u5s6l3.entities.BlogPost;
import ageria.u5s6l3.entities.BlogPostPayload;
import ageria.u5s6l3.exceptions.NotFoundExceptionId;
import ageria.u5s6l3.repositories.BlogPostRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Getter
@Service
public class BlogPostService {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorService authorService;

    public Page<BlogPost> getAllPosts(int pages, int elements, String sortBy){
        Pageable pageable = PageRequest.of(pages, elements, Sort.by(sortBy));
        return this.blogPostRepository.findAll(pageable);
    }

    public List<BlogPost> findAll(){
        return this.blogPostRepository.findAll();
    }

    public BlogPost findPostById(UUID id){
        return this.blogPostRepository.findById(id).orElseThrow(() -> new NotFoundExceptionId(id));
    }

    public BlogPost saveBlogPost(BlogPostPayload body){
//        System.out.println("AAAAAAAAAAAAAAAAAA");
//        System.out.println(this.authorService.findAuthorById(body.getAuthorId()));
        Author authorFromDb = this.authorService.findAuthorById(body.getAuthorId());


        BlogPost newBp = new BlogPost();
        newBp.setTitle(body.getTitle());
        newBp.setContenuto(body.getContenuto());
        newBp.setReadingTime(body.getReadingTime());
        newBp.setAuthor(authorFromDb);
        newBp.setCategory(body.getCategory());  // Ensure category is set
        newBp.setCover("https://localhost:8080/" + body.getTitle());
;

        return this.blogPostRepository.save(newBp);

    }

    public BlogPost updateBlogPost(UUID blogPostId, BlogPost body){
        BlogPost found = this.findPostById(blogPostId);


        found.setCover("https://localhost:8080/" + body.getTitle());
        found.setTitle(body.getTitle());
        found.setCategory(body.getCategory());
        found.setContenuto(found.getContenuto());
        found.setReadingTime(body.getReadingTime());
        found.setAuthor(body.getAuthor());

        return found;
    }

    public void findyByIdAndDelete(UUID blogPostId){
        BlogPost found = this.findPostById(blogPostId);
        this.blogPostRepository.delete(found);


    }


}
