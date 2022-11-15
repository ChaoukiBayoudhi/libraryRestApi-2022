package tn.esb.bis.libraryRestApi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esb.bis.libraryRestApi.Domains.Book;
import tn.esb.bis.libraryRestApi.Services.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
//Le rôle d'un controleur selon le design ECB : Entity Controller Boundary :
 //-->recupere la requête du client
 //-->La verifier
//-->si la requête est valide alors la transférer au Service
//-->sinon la rejeter
public class BookController {
    @Autowired
    private BookService service;
    //SQL    =>     HTTP                                                                              annotations Spring-boot
    //select => se fait via la méthode GET de Http                                                          @GetMapping("url")
    //insert => se fait via la méthode POST de Http                                                         @PostMapping("url")
    //delete => se fait via la méthode DELETE de Http                                                       @DeleteMapping("url")
    //update => se fait via la méthode UPDATE de Http (si on veut modifier tous les attributs(Totale))      @UpdateMapping("url")
    //update => se fait via la méthode PATCH de Http (si on veut modifier quelques attributs(Partielle))    @PatchMapping("url")
    @GetMapping("/all")
    public ResponseEntity<?> getBooks() {
        return service.getAllBooks();
    }
    @GetMapping("/book/{isbnCode}")
    public ResponseEntity<?> getBook(@PathVariable String isbnCode) {
        return service.getOneBook(isbnCode);
    }
    @PostMapping("/new")
    public ResponseEntity<?> createNewBook(@Valid @RequestBody Book b1){
        return service.addBook(b1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBook(@PathVariable String isbnCode)
    {
        return service.deleteBook(isbnCode);
    }
    @PutMapping("/update/{isbnCode}")
    public ResponseEntity<?> updateBook(@PathVariable String isbnCode,@Valid @RequestBody Book newBook) {
        return service.updateBook(isbnCode,newBook);
    }
}
