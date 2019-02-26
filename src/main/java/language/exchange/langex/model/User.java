package language.exchange.langex.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User {


    @GeneratedValue
    private @Id
    int id;
    private int googleId;
    private String firstName;
    private String lastName;
    private String cLvlLangs;
    private String bLvlLangs;
    private String aLvlLangs;
    private String hobbies;
    private String photoLocation;


    public User() {
    }

    public User(int googleId, String firstName, String lastName, String cLvlLangs, String bLvlLangs, String aLvlLangs, String hobbies, String photoLocation) {
        this.googleId = googleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cLvlLangs = cLvlLangs;
        this.bLvlLangs = bLvlLangs;
        this.aLvlLangs = aLvlLangs;
        this.hobbies = hobbies;
        this.photoLocation = photoLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoogleId() {
        return googleId;
    }

    public void setGoogleId(int googleId) {
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

    public String getcLvlLangs() {
        return cLvlLangs;
    }

    public void setcLvlLangs(String cLvlLangs) {
        this.cLvlLangs = cLvlLangs;
    }

    public String getbLvlLangs() {
        return bLvlLangs;
    }

    public void setbLvlLangs(String bLvlLangs) {
        this.bLvlLangs = bLvlLangs;
    }

    public String getaLvlLangs() {
        return aLvlLangs;
    }

    public void setaLvlLangs(String aLvlLangs) {
        this.aLvlLangs = aLvlLangs;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", googleId=" + googleId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cLvlLangs='" + cLvlLangs + '\'' +
                ", bLvlLangs='" + bLvlLangs + '\'' +
                ", aLvlLangs='" + aLvlLangs + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", photoLocation='" + photoLocation + '\'' +
                '}';
    }
}
