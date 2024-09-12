package ageria.u5s6l4.services;

import ageria.u5s6l4.entities.Author;
import ageria.u5s6l4.entities.BlogPost;
import ageria.u5s6l4.DTO.NewBlogPostDTO;
import ageria.u5s6l4.exceptions.NotFoundExceptionId;
import ageria.u5s6l4.repositories.BlogPostRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Getter
@Service
public class BlogPostService {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    Cloudinary cloudinaryUploader;

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

    public BlogPost saveBlogPost(NewBlogPostDTO body){
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

    public void uploadImage(MultipartFile file, UUID id) throws IOException {
        BlogPost bp = this.findPostById(id);
        String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        bp.setCover(url);
        this.blogPostRepository.save(bp);
    }

    public void findyByIdAndDelete(UUID blogPostId){
        BlogPost found = this.findPostById(blogPostId);
        this.blogPostRepository.delete(found);

    }


}
