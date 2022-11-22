package tn.esb.bis.libraryRestApi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esb.bis.libraryRestApi.Domains.Book;
import tn.esb.bis.libraryRestApi.Domains.Writer;
import tn.esb.bis.libraryRestApi.Repositories.BookRepository;
import tn.esb.bis.libraryRestApi.Repositories.WriterRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
//Dans un service, on implemente la logique metier
public class BookService {
    @Autowired
    private BookRepository repository;
    @Autowired
    private WriterRepository writerRepository;

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
    //1ère méthode:
    public ResponseEntity<?> addBook(Book b1) {
        //verifier s'il exite un livre ayant le même isbnCode qui a été dejà ajouté
        //sinon existe-il un livre ayant le même titre et le même auteur.
        List<Book> lstBooks=repository.findAll();
        int i=0;
        boolean find=false;
        while (i<lstBooks.size()&&!find) {
            if(lstBooks.get(i).getIsbnCode().equalsIgnoreCase(b1.getIsbnCode()))
                find=true;
            i++;
        }
        if(find)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A book with same isbnCode exists.");
        //chercher par titre et auteur

       return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(b1));
    }
    public ResponseEntity<?> deleteBook(String isbnCode)
    {
        Optional<Book> res=repository.findById(isbnCode);
        if(res.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no book with code = " +isbnCode);
        repository.deleteById(isbnCode);
        return ResponseEntity.accepted().build();
        //or
        //return ResponseEntity.status(HttpStatus.ACCEPTED).body("The book witg code = "+isbnCode +"has been successfully deleted");

    }
    public ResponseEntity<?> updateBook(String isbnCode,Book newBook)
    {
        Optional<Book> res=repository.findById(isbnCode);
        if(res.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no book with code = " +isbnCode);
        //recupere le livre from th Optional collection
        Book b1=res.get();
        //mettre à jour les attributs du livre
        b1.setIsbnCode(isbnCode);
        b1.setTitle(newBook.getTitle());
        b1.setPrice(newBook.getPrice());
        b1.setSummary(newBook.getSummary());
        b1.setReleaseDate(newBook.getReleaseDate());
        b1.setNbCopies(newBook.getNbCopies());
        //sauvegarder les modifications
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(b1));

    }
    //si l'ecrivain du livre exists alors juste on le lie au livre
    //sinon on ajoute l'ecrivain à la liste des ecrivains et puis on effectue le lien entre les deux entités.
    public ResponseEntity<?> addWriter(String isbnCode, Writer writer) {
        Optional<Book> res=repository.findById(isbnCode);
        if(res.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no book with code = " +isbnCode);
        //Le livre existe
        //verifier si l'ecrivain existe déjà dans la BD.
        List<Writer> lstWriters=writerRepository.findAll();
        Set<Writer> writerSet=lstWriters.stream()  //conversion de List vers Stream
                .filter(w->w.getName().equalsIgnoreCase(writer.getName())&&w.getFamilyName().equalsIgnoreCase(writer.getFamilyName()))
                .filter(w->w.getBirthDate().equals(writer.getBirthDate()))
                .collect(Collectors.toSet()); //conversion de Stream vers List
        if (writerSet.isEmpty()) { //ajouter l'ecrivain à la BD pour garder une BD coherente
            writerRepository.save(writer);
        }
        Book book=res.get();
        book.setBookWriter(writer);
        repository.save(book);
        return ResponseEntity.accepted().build();

    }
}
