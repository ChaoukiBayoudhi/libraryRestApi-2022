package tn.esb.bis.libraryRestApi.Domains;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @EqualsAndHashCode.Include
    private int streetNumber;
    @NonNull
    @EqualsAndHashCode.Include
    private String streetName;
    @NonNull
    private String city;
    @NonNull
    @EqualsAndHashCode.Include
    private int postalCode;

    //Relation entre Address et Member (1-1)
    @OneToOne(mappedBy = "memberAddress")
    //mappedBy : mentionne que les deux attributs member et memberAddress proviennent de la meme relation (1-1)
    private Member member;
}
