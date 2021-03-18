package org.example.model;

import org.zkoss.bind.annotation.DependsOn;

public class Contributor {
    private String title;
    private String firstName;
    private String lastName;
    private Integer extension;

    public Contributor(String title, String firstName, String lastName, Integer extension) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.extension = extension;
    }

    public Contributor(){
        this.title="Title";
        this.firstName="First Name";
        this.lastName="Last Name";
        this.extension=1245;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getExtension() {
        return extension;
    }

    public void setExtension(Integer extension) {
        this.extension = extension;
    }
    @DependsOn({ "firstName", "lastName" })
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
