package language.exchange.langex.model;

import javax.persistence.*;

@Entity
public class Friends {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private @Id
    int id;
    private String userOne;
    private String userTwo;
    private Boolean friends = false;
    private String chatNumber;

    public Friends() {

    }

    public Friends(String userOne, String userTwo, Boolean friends, String chatNumber) {
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.friends = friends;
        this.chatNumber = chatNumber;
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

    public String getChatNumber() {
        return chatNumber;
    }

    public void setChatNumber(String chatNumber) {
        this.chatNumber = chatNumber;
    }

    public Boolean getFriends() {
        return friends;
    }

    public void setFriends(Boolean friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", userOne='" + userOne + '\'' +
                ", userTwo='" + userTwo + '\'' +
                ", friends=" + friends +
                ", chatNumber='" + chatNumber + '\'' +
                '}';
    }
}
