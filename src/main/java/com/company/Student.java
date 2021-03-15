package com.company;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private double score; // optional- students have scores
    private int nrCurrent; // Bonus - Gale Shapley
    private int nrOpt;// Bonus - Gale Shapley
    private List<School> equality;

    public Student() {
        nrCurrent = 0;
        equality = new ArrayList<>();
    }

    public Student(String name) {
        this.name = name;
        nrCurrent = 0;
        equality = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public int getNrCurrent() {
        return nrCurrent;
    }

    public int getNrOpt() {
        return nrOpt;
    }

    public void setNrCurrent(int nrCurrent) {
        this.nrCurrent = nrCurrent;
    }

    public void setNrOpt(int nrOpt) {
        this.nrOpt = nrOpt;
    }

    public List<School> getEquality() {
        return equality;
    }

    public void setEquality(List<School> equality) {
        this.equality = equality;
    }
}
