package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private Problem p;
    private Map<Student, School> match;

    public Solution(Problem p) {
        this.p = p;
        match = new HashMap<>();
    }

    public List<Student> sortScore() {
        List<Student> sortStud = (List<Student>) p.getStudents().stream()
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        //  .map(Student::getName)
        //  .forEach(System.out::println);
        return sortStud;
    }

    public void solve() {
        List<Student> std = new ArrayList<>(sortScore());
        for (Student s : std) {
            for (School h : p.getStudPref().get(s))
                if (h.getNr() <h.getCapacity()) {
                    match.put(s, h);
                    h.setNr((h.getNr() + 1));
                    break;
                }
        }

    }
    public void print(){
        for(Student s: match.keySet()){
            System.out.println(s.getName() + " "+match.get(s).getName());

        }
    }
    public Map<Student,School> getMatch(){
        return  match;
    }
}
