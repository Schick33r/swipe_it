package zandb.software.swipeit.data.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import zandb.software.swipeit.data.chat.Chat;
import zandb.software.swipeit.data.chat.ChatLine;
import zandb.software.swipeit.data.property.Property;
import zandb.software.swipeit.data.user.type.UserType;

@Entity
public class SwipeItUser {

  @Id
  @GeneratedValue
  private long userId;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String password;

  private String firstName;

  private String lastName;

  private String telephoneNumber;

  private String country;

  private String city;

  private String biography;

  private int height;

  private int age;

  @Column(nullable = false)
  private String userType = UserType.CLIENT.getType();

  @OneToMany(mappedBy = "user1")
  private Set<Chat> chatsAsUser1;

  @OneToMany(mappedBy = "user2")
  private Set<Chat> chatsAsUser2;

  @OneToMany(mappedBy = "chatUser")
  private Set<ChatLine> chatLines;

  @OneToMany(mappedBy = "owner")
  private Set<Property> properties;

  public SwipeItUser() {

  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public Set<Chat> getChatsAsUser1() {
    return chatsAsUser1;
  }

  public void setChatsAsUser1(Set<Chat> chatsAsUser1) {
    this.chatsAsUser1 = chatsAsUser1;
  }

  public Set<Chat> getChatsAsUser2() {
    return chatsAsUser2;
  }

  public void setChatsAsUser2(Set<Chat> chatsAsUser2) {
    this.chatsAsUser2 = chatsAsUser2;
  }

  public Set<ChatLine> getChatLines() {
    return chatLines;
  }

  public void setChatLines(Set<ChatLine> chatLines) {
    this.chatLines = chatLines;
  }

  public Set<Property> getProperties() {
    return properties;
  }

  public void setProperties(Set<Property> properties) {
    this.properties = properties;
  }


}
