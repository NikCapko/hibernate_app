package code;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "yearBirth")
  private String yearBirth;

  public Student() {}

  public Student(String firstName, String lastName, String yearBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearBirth = yearBirth;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getYearBirth() {
    return yearBirth;
  }

  public void setYearBirth(String yearBirth) {
    this.yearBirth = yearBirth;
  }

  @Override
  public String toString() {
    return String.format(
        "{\"id\":\"%s\", \"firstName\":\"%s\", \"lastName\":\"%s\", \"yearBirth\":\"%s\"}",
        id, firstName, lastName, yearBirth);
  }

  /*@Override
  public String toString() {
    return "models.User{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", firstName='"
        + lastName
        + '\''
        + ", firstName='"
        + yearBirth
        + '\''
        + '}';
  }*/
}
