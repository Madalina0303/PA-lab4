package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Problem {
    private Map<Student, List<School>> studPref;
    private Map<School, List<Student>> schoolPref; // oare trebuie sa fie TREE ??
    private List<Student> students;
    private List<School> schools;

    /* int nrStudents;
     int nrSchools;
     int[] capacity;
     */
    public Problem(List<Student> students, List<School> schools) {
        this.students = new ArrayList<>(students);
        this.schools = new ArrayList<>(schools);
        studPref = new HashMap<>();
        schoolPref = new HashMap<>();
    }

    public Problem(List<Student> students, List<School> schools, Map<Student, List<School>> studPref, Map<School, List<Student>> schoolPref) {
        this.students = new ArrayList<>(students);
        this.schools = new ArrayList<>(schools);
        this.studPref = new HashMap<>(studPref);
        this.schoolPref = new HashMap<>(schoolPref);
    }

    public void addPrefSchool(Student s, List<School> sch) {
        studPref.put(s, sch);
    }

    public void addPrefStudent(School h, List<Student> std) {
        schoolPref.put(h, std);
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void addSchool(School h) {
        schools.add(h);
    }

    public List<School> getSchools() {
        return schools;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // STREAM !!!!!!!!!!!!!!!!
    public void setSchools(List<School> schools) {
       /* this.schools=schools.stream()
                .collect(Collectors.toCollection(ArrayList::new));

        */
        this.schools = schools;
    }

    public Map<School, List<Student>> getSchoolPref() {
        return schoolPref;
    }

    public Map<Student, List<School>> getStudPref() {
        return studPref;
    }

    public void printStudents(List<School> sch) {
        students.stream()
                .filter(s -> studPref.get(s).containsAll(sch))
                .map(Student::getName)
                .forEach(System.out::println);
    }

    public void printSchool(Student s) {
        schools.stream()
                .filter(h -> schoolPref.get(h).contains(s))
                .filter(h -> schoolPref.get(h).get(0) == s)
                .map(School::getName)
                .forEach(System.out::println);
    }
}
