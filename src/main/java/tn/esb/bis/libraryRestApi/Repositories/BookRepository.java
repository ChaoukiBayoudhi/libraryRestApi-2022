package tn.esb.bis.libraryRestApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esb.bis.libraryRestApi.Domains.Book;
@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    //JpaRepository est une interface  fournie par spring data
    //cette interface dispose d'un ensemble des méthodes permettant d'interroger la BD.
    //findAll() rettourne une liste
    //BookRepository repository;
    //List<Book> lst=repository.findAll();
    //l'intsruction pécedente est equivalente à l'execution de la requete SQL "select * from Book"
    //Optional<Book> res=repository.findById(5);
    //Optional c'est une collection qui peut contenir au maximum un seul objet.
    //res.isPresent() retourne true si Optional contient un objet.
    //res.isEmpty() retourne true si Optional ne contient aucun objet
    // pour recuperer l'objet dans Optional
    // if(res.isPresent())
    //    Book b1=res.get();
    //Book b=new Book();
    //b.setIsbnCode("123-ESB-23");
    //b.setTitle("java programming");
    //...
    // Book b2=repository.save(b)
    //La ligne precesente est equivalente à l'execution de la requete SQL "insert into Book values('123-ESB-23','prog...',...)"
    //save() joue double rôle : insert or update
    //exemple de mise à jour en utilisant la méthode save() :
    //Optional<Book> res=repository.findById("123-ESB-23");
    //if(res.isPresent())
    //    Book b1=res.get();
    //b1.setTitle("advanced java programming");
    //b1.price(55.7);
    //...
    //repository.save(b1);

    //Suupression  se fait via :
    //repository.deleteById("123-ESB-23")
    //l'instruction precesente est equvalente à l'execution de la requete SQL suivante :
    //delete from Book where isbnCode="123-ESB-23";

}
