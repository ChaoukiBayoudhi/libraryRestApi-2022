package tn.esb.bis.libraryRestApi.Domains;

import lombok.*;
import tn.esb.bis.libraryRestApi.Enumerations.MemberType;

import javax.persistence.*;
import java.time.LocalDate;
//@ToString //redefinit la méthode String ToString() de la classe object et retourne toutes les valeurs
//de tous les attributs
//@ToString(exclude = {"photo","phoneNumber"})//tous sera retourné sauf les valeurs de photo et de phoneNumber
@ToString(exclude="photo")
//@EqualsAndHashCode //redefinit le methodes equals et hashCode de la classe object
//dans ce cas là deux membres sont identiques s'ils ont egalité entre tous les attributs respectivement deux à deux
@EqualsAndHashCode(onlyExplicitlyIncluded = true)//la comparaison entre les objets sera effectuant en utilisant
//uniquement les attributs marqués avec l'annotation @EqualsAndHashCode.Include
@Getter //génère tous les getters pour tous les attributs
@Setter //génère tous les setters pour tous les attributs
@NoArgsConstructor //génère un constructeur non parametré
//@AllArgsConstructor //génère un constructeur ayant tous les attributs comme paramètres
@RequiredArgsConstructor //génère un constructeur ayant comme pramètres uniquement les attributs marqués avec @NonNull
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull //l'attribut name est obligatoire
    @EqualsAndHashCode.Include
    @Column(name = "firstName",length = 100)
    private String name;
    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "lastName",length = 100)
    private String familyName;
    @NonNull
    @EqualsAndHashCode.Include
    private String email;
    private LocalDate birthDate;
    private String phoneNumber;
    @NonNull
    private LocalDate inscriptionDate;
    private MemberType type;
    @Lob
    private byte[] photo;

    //Relation entre Member et Address (1-1)
    @OneToOne
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    //"la ligne précedente est equivalente en SQL à "address_id foreign key referenced address(id)"
    private Address memberAddress;

}
