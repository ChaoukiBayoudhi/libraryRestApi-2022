package tn.esb.bis.libraryRestApi.Domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate birthDate;
    private String nationality;
    private String biography;
    @Lob
    private byte[] photo;

}
