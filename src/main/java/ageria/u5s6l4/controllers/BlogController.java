package ageria.u5s6l4.controllers;

import ageria.u5s6l3.entities.BlogPost;
import ageria.u5s6l3.entities.BlogPostPayload;
import ageria.u5s6l3.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int pages,
                                @RequestParam(defaultValue = "10") int elements,
                                @RequestParam(defaultValue = "id") String sortBy){
        return this.blogPostService.getAllPosts(pages, elements, sortBy);
    }



    @GetMapping("/{bloPostId}")
    public BlogPost findyById(@PathVariable UUID blogPostId){
        return blogPostService.findPostById(blogPostId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBloPost(@RequestBody BlogPostPayload body){
       return blogPostService.saveBlogPost(body);

    }

    @PutMapping("/{bloPostId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BlogPost editBlogPost(@PathVariable UUID blogPostId, @RequestBody BlogPost body){
        return blogPostService.updateBlogPost(blogPostId,body);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteBlogPost(@PathVariable UUID blogPostId){
        blogPostService.findyByIdAndDelete(blogPostId);
        return "BLOG POST DELETED CORRECTLY";
    }
}
