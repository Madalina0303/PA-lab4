package com.company;

public class School implements Comparable {
    private String name;

    public School(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
}
