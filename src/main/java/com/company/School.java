package com.company;

import java.util.List;

public class School implements Comparable {
    private String name;
    private int capacity; // optional
    private int nr;
    private List<Student>equality;

    public School(String name) {
        this.name = name;
        nr = 0;
    }

    public School(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        nr = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null)
            throw new NullPointerException();
        if (!(o instanceof School))
            throw new ClassCastException("Uncomparable objects");
        School school = (School) o;
        return this.getName().compareTo(school.getName());
    }
    public List<Student> getEquality() {
        return equality;
    }

    public void setEquality(List<Student> equality) {
        this.equality = equality;
    }
}
