package language.exchange.langex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getGoogleId() {
        return googleId;
    }

    public void setGoogleId(Long googleId) {
        this.googleId = googleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getStudyingLangs() {
        return studyingLangs;
    }

    public void setStudyingLangs(String studyingLangs) {
        this.studyingLangs = studyingLangs;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", googleId=" + googleId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", languages='" + languages + '\'' +
                ", studyingLangs='" + studyingLangs + '\'' +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }

    @Id
    @GeneratedValue
    private int id;
    private Long googleId;
    private String firstName;
    private String lastName;
    private String languages;
    private String studyingLangs;
    private String hobbies;
}
