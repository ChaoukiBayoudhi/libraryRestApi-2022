package tn.esb.bis.libraryRestApi.Domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "photo")
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String familyName;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String nationality;
    private String biography;
    @Lob
    private byte[] photo;

    //Relation entre Writer et Book (1-*)
    @OneToMany(mappedBy ="bookWriter")
    private Set<Book> books=new HashSet<Book>();

}
