package ageria.u5s6l4.controllers;


import ageria.u5s6l3.entities.Author;
import ageria.u5s6l3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;



    @GetMapping
    public Page<Author> findAll(@RequestParam(defaultValue = "0") int pages,
                                @RequestParam(defaultValue = "10") int elements,
                                @RequestParam(defaultValue = "id") String sortBy){
        return this.authorService.getAllAuthors(pages, elements, sortBy);
    }

    @GetMapping("/{authorId}")
    public Author findyById(@PathVariable UUID authorId){
        return authorService.findAuthorById(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author body){
        authorService.saveAuthor(body);
        return body;
    }



    @PutMapping("/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@PathVariable UUID authorId, @RequestBody Author body){
        authorService.findByIdAndUpdate(authorId, body);
        return body;
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAuthor(@PathVariable UUID authorId){
        authorService.findByIdAndDelete(authorId);
        return "AUTHOR DELETED";
    }

}
