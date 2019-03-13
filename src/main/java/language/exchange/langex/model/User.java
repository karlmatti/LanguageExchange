package language.exchange.langex.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User {

    //@GeneratedValue
    private @Id
    String id;
    private int age;
    private String firstName;
    private String lastName;
    private String cLvlLangs;
    private String bLvlLangs;
    private String aLvlLangs;
    private String hobbies;
    private String photoLocation;
    private String bioGraphy;


    public User() {
    }

    public User(int age, String googleId, String firstName,
                String lastName, String cLvlLangs, String bLvlLangs,
                String aLvlLangs, String hobbies, String photoLocation,
                String bioGraphy) {
        this.id = googleId;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cLvlLangs = cLvlLangs;
        this.bLvlLangs = bLvlLangs;
        this.aLvlLangs = aLvlLangs;
        this.hobbies = hobbies;
        this.photoLocation = photoLocation;
        this.bioGraphy = bioGraphy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getBioGraphy() {
        return bioGraphy;
    }

    public void setBioGraphy(String bioGraphy) {
        this.bioGraphy = bioGraphy;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cLvlLangs='" + cLvlLangs + '\'' +
                ", bLvlLangs='" + bLvlLangs + '\'' +
                ", aLvlLangs='" + aLvlLangs + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", photoLocation='" + photoLocation + '\'' +
                ", bioGraphy='" + bioGraphy + '\'' +
                '}';
    }
}