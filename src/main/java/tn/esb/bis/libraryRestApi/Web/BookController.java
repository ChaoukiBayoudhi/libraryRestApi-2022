package tn.esb.bis.libraryRestApi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esb.bis.libraryRestApi.Services.BookService;

@RestController
//Le rôle d'un controleur selon le design ECB : Entity Controller Boundary :
 //-->recupere la requête du client
 //-->La verifier
//-->si la requête est valide alors la transférer au Service
//-->sinon la rejeter
public class BookController {
    @Autowired
    private BookService service;
}
