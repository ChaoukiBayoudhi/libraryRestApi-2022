package tn.esb.bis.libraryRestApi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esb.bis.libraryRestApi.Domains.Book;
import tn.esb.bis.libraryRestApi.Repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
//Dans un service, on implemente la logique metier
public class BookService {
    @Autowired
    private BookRepository repository;

    //ResponseEntity c'est un objet contenant une entête (header) et un corps (body)
    //header contient des informations comme contentType (json/xml/text,...), status( un code HTTP)
    //le status est implementé avec l'enumeration HttpStatus
    //le corps contient la donnée à transferer au client
    //ResponseEntity<?> pour dire qu'on retourne une Entity avec un corps de type variale
    public ResponseEntity<?> getAllBooks()
    {
        List<Book> res=repository.findAll();
        if(res.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There is no books available for now");//retourne une chaine dans le corps
        return ResponseEntity.status(HttpStatus.OK).body(res); //retourne une List<Book>
    }
    public ResponseEntity<?> getOneBook(String isbnCode)
    {
        Optional<Book> res=repository.findById(isbnCode);
        if(res.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no book with code = " +isbnCode);
        return ResponseEntity.status(HttpStatus.OK).body(res.get());
    }
}
