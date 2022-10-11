package tn.esb.bis.libraryRestApi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esb.bis.libraryRestApi.Repositories.BookRepository;

@Service
//Dans un service, on implemente la logique metier
public class BookService {
    @Autowired
    private BookRepository repository;
}
