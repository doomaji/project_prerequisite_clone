package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",  unique = true, nullable = false)
    private Long id;

    @Column(name="Name", length = 50, nullable = false)
    private String name;

    @Column(name="LastName", length = 50, nullable = false)
    private String lastName;

    @Column(name="Age", length = 5, nullable = false)
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return ("id = " + this.id + ", name = " +  this.name + ", age = " + this.age);
    }
}

