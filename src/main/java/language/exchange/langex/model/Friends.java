package language.exchange.langex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Friends {

    @GeneratedValue
    private @Id
    int id;
    private String userOne;
    private String userTwo;

    public Friends() {
    }

    public Friends(String userOne, String userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserOne() {
        return userOne;
    }

    public void setUserOne(String userOne) {
        this.userOne = userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(String userTwo) {
        this.userTwo = userTwo;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id='" + id + '\'' +
                ", userOne='" + userOne + '\'' +
                ", userTwo='" + userTwo + '\'' +
                '}';
    }
}
