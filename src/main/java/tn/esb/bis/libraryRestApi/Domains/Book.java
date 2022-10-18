package tn.esb.bis.libraryRestApi.Domains;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@ToString(exclude = "coverImage")
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity //Book sera transformée en une table relationnelle dans la BD par l'ORM

//ORM : Object Relational Mapping
public class Book {
    @Id // isbnCode sera la clé primaire de la table "Book" crée par l'ORM
    //@GeneratedValue // Attribue une valeur automatique à la clé primaire de la table "Book"
    //lors de chaque ajout
   // @GeneratedValue(strategy = GenerationType.IDENTITY)//l'id du premier livre ajouté est 1, le 2ème est 2, ...
    @EqualsAndHashCode.Include
    private String isbnCode;
    @NonNull
    private String title;
    @Column(scale = 7,precision=3)//le prix est sur 7 chiffres et 3 chiffres decimals
    @NonNull
    private BigDecimal price;
    @NonNull
    private LocalDate releaseDate;
    @NonNull
    private int nbCopies;
    @Lob
    private byte[] coverImage;
    private String summary;

    //Relation entre Book et Writer (*-1)
    @ManyToOne
    @JoinColumn(name = "writer_id",referencedColumnName = "id")
    private Writer bookWriter;


}
