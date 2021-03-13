package com.company;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // Students -> linked list + sort - comparator
        var stud = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .collect(Collectors.toCollection(LinkedList::new));

        var studSort = stud.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        //Schools : tree set + comparable schools + sort
        var school = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .collect(Collectors.toCollection(TreeSet::new));
        var schoolSort = school.stream()
                .sorted()
                .collect(Collectors.toCollection(TreeSet::new));
        //Map for Student
        List<School> sch = new ArrayList<>(schoolSort);
        Map<Student, List<School>> studPref = new HashMap<>();
        Main app = new Main();
        app.setPrefStud(studPref, studSort, sch);
        app.printPrefStud(studPref);

        // Map for School
        Map<School, List<Student>> schoolPref = new TreeMap<>();
        app.setPrefSchool(schoolPref, studSort, sch);
        app.printPrefSchool(schoolPref);

    }

    public void setPrefStud(Map<Student, List<School>> studPref, LinkedList<Student> studSort, List<School> sch) {
        studPref.put(studSort.get(0), sch);
        studPref.put(studSort.get(1), sch);
        List<School> sch2 = new ArrayList();
        sch2.add(sch.get(0));
        sch2.add(sch.get(1));
        studPref.put(studSort.get(2), sch2);
        List<School> sch3 = new ArrayList();
        sch3.add(sch.get(0));
        sch3.add(sch.get(2));
        studPref.put(studSort.get(3), sch3);

    }

    public void setPrefSchool(Map<School, List<Student>> schoolPref, LinkedList<Student> studSort, List<School> sch) {
        List<Student> std0 = new ArrayList<>();
        std0.add(studSort.get(3));
        std0.add(studSort.get(0));
        std0.add(studSort.get(1));
        std0.add(studSort.get(2));

        schoolPref.put(sch.get(0), std0);

        List<Student> std1 = new ArrayList<>();
        std1.add(studSort.get(0));
        std1.add(studSort.get(2));
        std1.add(studSort.get(1));
        schoolPref.put(sch.get(1), std1);

        List<Student> std2 = new ArrayList<>();
        std2.add(studSort.get(0));
        std2.add(studSort.get(1));
        std2.add(studSort.get(3));
        schoolPref.put(sch.get(2), std2);

    }

    public void printPrefStud(Map<Student, List<School>> studPref) {
        for (Student std : studPref.keySet()) {
            System.out.print(std.getName() + ": ");
            for (School h : studPref.get(std)) {
                System.out.print(h.getName() + " ");
            }
            System.out.println();
        }

    }

    public void printPrefSchool(Map<School, List<Student>> schoolPref) {
        for (School hsch : schoolPref.keySet()) {
            System.out.print(hsch.getName() + ": ");
            for (Student s : schoolPref.get(hsch)) {
                System.out.print(s.getName() + " ");
            }
            System.out.println();
        }

    }
}
/* List<Student> stdList = new LinkedList<>();
        stdList.addAll(Arrays.asList(stud));
        var sch = IntStream.rangeClosed(0, 2).mapToObj(i -> new School("H" + i)).toArray(School[]::new);
        Set<School> schList = new TreeSet<>();
        schList.addAll(Arrays.asList(sch));
 for (Student st : stud) {
            System.out.println(st.getName());
        }
        */