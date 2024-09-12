package ageria.u5s6l4.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;


    public Author(String name, String surname, String email, String avatar, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.avatar = avatar;
        this.birthDate = birthDate;
    }
}
