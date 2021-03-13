package com.company;

public class Student {
    private String name;
    private double score; // optional- students have scores
    public Student(String name) {
        this.name=name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
