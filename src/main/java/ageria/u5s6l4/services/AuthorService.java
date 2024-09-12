package ageria.u5s6l4.services;

import ageria.u5s6l4.DTO.NewAuthorDTO;
import ageria.u5s6l4.entities.Author;
import ageria.u5s6l4.exceptions.BadRequestException;
import ageria.u5s6l4.exceptions.NotFoundExceptionId;
import ageria.u5s6l4.repositories.AuthorRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    Cloudinary cloudinaryUploader;

    public Author findAuthorById(UUID id){return this.authorRepository.findById(id).orElseThrow(() -> new NotFoundExceptionId(id));};

    public List<Author> findAll(){
        return this.authorRepository.findAll();
    }
   public Page<Author> getAllAuthors(int pages, int elements, String sortBy){
       Pageable pageable = PageRequest.of(pages, elements, Sort.by(sortBy));
       return this.authorRepository.findAll(pageable);
   }


    public Author saveAuthor(NewAuthorDTO body) {

        Author newAuthor = new Author(body.name(), body.surname(), body.email(), "https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname(), body.birthDate());
        return this.authorRepository.save(newAuthor);
    }

    public void uploadImage(MultipartFile avatar, UUID id) throws IOException {

            String url = (String) cloudinaryUploader.uploader().upload(avatar.getName(), ObjectUtils.emptyMap()).get("url");
            System.out.println("URL " + url);
            Author authorFromDB = findAuthorById(id);
            authorFromDB.setAvatar(url);
            this.authorRepository.save(authorFromDB);

    }

    public Author findByIdAndUpdate(UUID id, Author body){
            Author found = this.findAuthorById(id);
                found.setName(body.getName());
                found.setSurname(body.getSurname());
                found.setEmail(body.getEmail());
                found.setBirthDate(body.getBirthDate());

        return found;
    }

    public void findByIdAndDelete(UUID id){
        Author found = this.findAuthorById(id);
        this.authorRepository.delete(found);
    }
}
