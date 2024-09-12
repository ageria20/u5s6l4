package ageria.u5s6l4.controllers;


import ageria.u5s6l4.DTO.NewAuthorDTO;
import ageria.u5s6l4.entities.Author;
import ageria.u5s6l4.exceptions.BadRequestException;
import ageria.u5s6l4.exceptions.ValidationException;
import ageria.u5s6l4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Author createAuthor(@RequestBody @Validated NewAuthorDTO body, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            String message = bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining());
            throw new BadRequestException(message);
        }
        else {
            return this.authorService.saveAuthor(body);
        }
    }

    @PostMapping("/avatar/{authorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadAvatar(@RequestParam("avatar")MultipartFile file, @PathVariable UUID authorId ) throws IOException {
        // "avatar" deve corrispondere ESATTAMENTE come il campo del FormData che ci invia il Frontend
        // Se non corrisponde non troverò il file
        this.authorService.uploadImage(file, authorId);
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
