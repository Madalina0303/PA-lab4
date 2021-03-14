package com.company;

public class Student {
    private String name;
    private double score; // optional- students have scores
    private int nrCurrent;
    private int nrOpt;
    public Student(){

    }
    public Student(String name) {
        this.name=name;
        nrCurrent=0;
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
}
