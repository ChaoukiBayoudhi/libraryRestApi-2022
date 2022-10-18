package tn.esb.bis.libraryRestApi.Domains;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
