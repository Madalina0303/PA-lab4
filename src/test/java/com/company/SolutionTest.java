package com.company;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @Test
    void getMatch() {
        List<Student> stud = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .collect(Collectors.toCollection(ArrayList::new));

        List<School> school = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .collect(Collectors.toCollection(ArrayList::new));


        Map<Student, List<School>> studPref = new HashMap<>();

        studPref.put(stud.get(0), school);
        studPref.put(stud.get(1), school);
        studPref.put(stud.get(2), Arrays.asList(school.get(0), school.get(1)));
        studPref.put(stud.get(3), Arrays.asList(school.get(0), school.get(2)));

        Map<School, List<Student>> schoolPref = new HashMap<>();
        schoolPref.put(school.get(0), Arrays.asList(stud.get(3), stud.get(0), stud.get(1), stud.get(2)));
        schoolPref.put(school.get(1), Arrays.asList(stud.get(0), stud.get(2), stud.get(1)));
        schoolPref.put(school.get(2), Arrays.asList(stud.get(0), stud.get(1), stud.get(3)));
        stud.get(0).setScore(9.5);
        stud.get(1).setScore(8.4);
        stud.get(2).setScore(9);
        stud.get(3).setScore(9.8);
        school.get(0).setCapacity(1);
        school.get(1).setCapacity(2);
        school.get(2).setCapacity(2);
        Problem p = new Problem(stud, school, studPref, schoolPref);
        Solution s = new Solution(p);
        assertTrue(s.getMatch().isEmpty());
    }

    @Test
    void sortScore() {
        List<Student> stud = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .collect(Collectors.toCollection(ArrayList::new));

        List<School> school = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .collect(Collectors.toCollection(ArrayList::new));


        Map<Student, List<School>> studPref = new HashMap<>();

        studPref.put(stud.get(0), school);
        studPref.put(stud.get(1), school);
        studPref.put(stud.get(2), Arrays.asList(school.get(0), school.get(1)));
        studPref.put(stud.get(3), Arrays.asList(school.get(0), school.get(2)));

        Map<School, List<Student>> schoolPref = new HashMap<>();
        schoolPref.put(school.get(0), Arrays.asList(stud.get(3), stud.get(0), stud.get(1), stud.get(2)));
        schoolPref.put(school.get(1), Arrays.asList(stud.get(0), stud.get(2), stud.get(1)));
        schoolPref.put(school.get(2), Arrays.asList(stud.get(0), stud.get(1), stud.get(3)));
        stud.get(0).setScore(9.5);
        stud.get(1).setScore(8.4);
        stud.get(2).setScore(9);
        stud.get(3).setScore(9.8);
        school.get(0).setCapacity(1);
        school.get(1).setCapacity(2);
        school.get(2).setCapacity(2);
        Problem p = new Problem(stud, school, studPref, schoolPref);
        Solution s = new Solution(p);
        assertEquals(9.8, s.sortScore().get(0).getScore());

    }

    @Test
    void solve() {
        List<Student> stud = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .collect(Collectors.toCollection(ArrayList::new));

        List<School> school = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .collect(Collectors.toCollection(ArrayList::new));


        Map<Student, List<School>> studPref = new HashMap<>();

        studPref.put(stud.get(0), school);
        studPref.put(stud.get(1), school);
        studPref.put(stud.get(2), Arrays.asList(school.get(0), school.get(1)));
        studPref.put(stud.get(3), Arrays.asList(school.get(0), school.get(2)));

        Map<School, List<Student>> schoolPref = new HashMap<>();
        schoolPref.put(school.get(0), Arrays.asList(stud.get(3), stud.get(0), stud.get(1), stud.get(2)));
        schoolPref.put(school.get(1), Arrays.asList(stud.get(0), stud.get(2), stud.get(1)));
        schoolPref.put(school.get(2), Arrays.asList(stud.get(0), stud.get(1), stud.get(3)));
        stud.get(0).setScore(9.5);
        stud.get(1).setScore(8.4);
        stud.get(2).setScore(9);
        stud.get(3).setScore(9.8);
        school.get(0).setCapacity(1);
        school.get(1).setCapacity(2);
        school.get(2).setCapacity(2);
        Problem p = new Problem(stud, school, studPref, schoolPref);
        Solution s = new Solution(p);
        s.solve();
        assertTrue(s.getMatch().containsKey(stud.get(3)));
    }
/*
    @Test
    void print() {
    }

 */
}