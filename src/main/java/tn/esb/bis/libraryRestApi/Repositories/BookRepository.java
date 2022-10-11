package tn.esb.bis.libraryRestApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esb.bis.libraryRestApi.Domains.Book;
@Repository
public interface BookRepository extends JpaRepository<Book,String> {
}
