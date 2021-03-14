package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bonus {
    public static void main(String[] args) {
        List<Student> stud = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .collect(Collectors.toCollection(ArrayList::new));

        List<School> school = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .collect(Collectors.toCollection(ArrayList::new));

        school.get(0).setCapacity(1);
        school.get(1).setCapacity(2);
        school.get(2).setCapacity(2);
        Map<Student, List<School>> studPref = new HashMap<>();

        studPref.put(stud.get(0), school);
        studPref.put(stud.get(1), school);
        studPref.put(stud.get(2), Arrays.asList(school.get(0), school.get(1)));
        studPref.put(stud.get(3), Arrays.asList(school.get(0), school.get(2)));

        Map<School, List<Student>> schoolPref = new HashMap<>();
        schoolPref.put(school.get(0), Arrays.asList(stud.get(3), stud.get(0), stud.get(1), stud.get(2)));
        schoolPref.put(school.get(1), Arrays.asList(stud.get(0), stud.get(2), stud.get(1)));
        schoolPref.put(school.get(2), Arrays.asList(stud.get(0), stud.get(1), stud.get(3)));
        Problem p = new Problem(stud, school, studPref, schoolPref);
        Solution s=new Solution(p);
        System.out.println("SUcccesss");
        s.galeShapley();
        for(Student stt:s.getMatch().keySet()){
            System.out.println(stt.getName() + " " + s.getMatch().get(stt).getName());
        }
    }
}