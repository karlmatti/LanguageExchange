package language.exchange.langex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
public class User {


    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String languages;
    private String studyingLangs;
    private String hobbies;

    public User(
            Long id, String firstName, String lastName,
            String languages, String studyingLangs, String hobbies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languages = languages;
        this.studyingLangs = studyingLangs;
        this.hobbies = hobbies;
    }
}
