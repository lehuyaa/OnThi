package com.ldh.onthidaihoc.model;

public class Exam {

    protected String id;
    protected String Name;
    protected String numSubject;


    public Exam() {
    }

    public Exam(String id, String name, String numSubject) {
        this.id = id;
        Name = name;
        this.numSubject = numSubject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumSubject() {
        return numSubject;
    }

    public void setNumSubject(String numSubject) {
        this.numSubject = numSubject;
    }
}
