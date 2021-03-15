package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private Problem p;
    private Map<Student, School> match;
    private int[] viz; // studentii nerepartizati
    private Map<Student, List<School>> studPrefEq;
    private Map<School, List<Student>> schoolPrefEq;

    public Solution(Problem p) {
        this.p = p;
        match = new HashMap<>();
        viz = new int[p.getStudents().size()];
        Arrays.fill(viz, 0);
        studPrefEq=new HashMap<>();
        schoolPrefEq=new HashMap<>();
    }

    public List<Student> sortScore() {
        List<Student> sortStud = p.getStudents().stream()
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
                if (h.getNr() < h.getCapacity()) {
                    match.put(s, h);
                    h.setNr((h.getNr() + 1));
                    break;
                }
        }

    }

    public void print() {
        for (Student s : match.keySet()) {
            System.out.println(s.getName() + " " + match.get(s).getName());

        }
    }

    public Map<Student, School> getMatch() {
        return match;
    }

    public void galeShapley() {
        int poz = free(p.getStudents());
        Student ss1 = p.getStudents().get(poz);
        while (poz != -1) {
            Student s = p.getStudents().get(poz); // student nerepartizat
            // parcurg  lista de preferinte
            for (School h : p.getStudPref().get(s)) {
                s.setNrCurrent(s.getNrCurrent() + 1);
                if (h.getNr() < h.getCapacity()) { // nu e full il poate accepta
                    match.put(s, h);
                    System.out.println("match: " + s.getName() + " " + h.getName());
                    h.setNr(h.getNr() + 1);

                    viz[poz] = 1;
                    break;
                } else { // e full dar verif daca stud este mai preferat decat ce are
                    if (replace(getAcceptedStud(h), s, h) != null) {
                        // scot din match
                        Student stdd = replace(getAcceptedStud(h), s, h);
                        match.remove(stdd);
                        viz[p.getStudents().indexOf(stdd)] = 0;
                        // adaug la match noua pereche
                        match.put(s, h);
                        System.out.println("match: " + s.getName() + " " + h.getName());
                        viz[p.getStudents().indexOf(s)] = 1;

                        break;
                    }
                }
            }
            poz = free(p.getStudents());
            if (poz != -1) {
                ss1 = p.getStudents().get(poz);
                System.out.println("urm fara perche este " + ss1.getName());
                if (ss1.getNrOpt() == ss1.getNrCurrent()) {
                    System.out.println(ss1.getName() + "nu are perche");
                    viz[p.getStudents().indexOf(ss1)] = 1;
                }
            }
        }
    }

    public int free(List<Student> std) {
        for (int i = 0; i < std.size(); i++) {
            if (viz[i] == 0)
                return i;
        }
        return -1;

    }

    public List<Student> getAcceptedStud(School h) {
        List<Student> pref = new ArrayList<>();
        for (Map.Entry<Student, School> sch : match.entrySet()) {
            if (sch.getValue().equals(h))
                pref.add(sch.getKey());
        }
        return pref;
    }

    public Student replace(List<Student> std, Student student, School h) {
        int max = -1;
        Student rm = new Student();
        for (Student s : std) {
            if ((!(p.getSchoolPref().get(h).contains(s))) && (p.getSchoolPref().get(h).contains(student)))
                return s; // poate face replace -ce are el nu e prin topuri
            else if ((p.getSchoolPref().get(h).contains(s))) {
                if (p.getSchoolPref().get(h).indexOf(s) > max) {
                    max = p.getSchoolPref().get(h).indexOf(s);
                    rm = s;
                }
            }

        }
        if (p.getSchoolPref().get(h).contains(student) && p.getSchoolPref().get(h).indexOf(student) < max && rm != null)
            return rm;
        return null;
    }

    public void filterStud() { // pentru studenti filtram scolile tratate cu indiferenta
        for (Student s : p.getStudPref().keySet()) {
            int gasit = 0;
            List<School> temp = new ArrayList<>();
            for (School h1 : p.getStudPref().get(s)) {
                if (s.getEquality().contains(h1) && gasit == 0) {
                    gasit = 1;
                    // ar trebui sa aleg random unul din lista de pref
                    int lg = s.getEquality().size();
                    int l = (int) (Math.random() * lg);
                    temp.add(s.getEquality().get(l));

                } else if ((!s.getEquality().contains(h1))) {
                    temp.add(h1);
                }
            }


            studPrefEq.put(s, temp);
        }
    }

    public void filterSchool() { // pentru scoli filtram studentii tratati cu indiferenta
        for (School s : p.getSchoolPref().keySet()) {
            int gasit = 0;
            List<Student> temp = new ArrayList<>();
            for (Student h1 : p.getSchoolPref().get(s)) {
                if (s.getEquality()!=null && s.getEquality().contains(h1) && gasit == 0) {
                    gasit = 1;
                    // ar trebui sa aleg random unul din lista de pref
                    int lg = s.getEquality().size();
                    int l = (int) (Math.random() * lg);
                    temp.add(s.getEquality().get(l));

                } else if ( s.getEquality() !=null && (!s.getEquality().contains(h1))  ) {
                    temp.add(h1);
                }
            }

            schoolPrefEq.put(s, temp);
        }
    }

    public void galeShapleyTie() {
        filterSchool();
        filterStud();
        int poz = free(p.getStudents());
        Student ss1 = p.getStudents().get(poz);
        while (poz != -1) {
            Student s = p.getStudents().get(poz); // student nerepartizat
            if (studPrefEq != null) {
                // parcurg  lista de preferinte
                for (School h : studPrefEq.get(s)) {
                    s.setNrCurrent(s.getNrCurrent() + 1);
                    if (h.getNr() < h.getCapacity()) { // nu e full il poate accepta
                        match.put(s, h);
                        h.setNr(h.getNr() + 1);

                        viz[poz] = 1;
                        break;
                    } else { // e full dar verif daca stud este mai preferat decat ce are
                        if (replaceEq(getAcceptedStud(h), s, h) != null) {
                            // scot din match
                            Student stdd = replaceEq(getAcceptedStud(h), s, h);
                            match.remove(stdd);
                            viz[p.getStudents().indexOf(stdd)] = 0;
                            // adaug la match noua pereche
                            match.put(s, h);
                            viz[p.getStudents().indexOf(s)] = 1;

                            break;
                        }
                    }
                }
                poz = free(p.getStudents());
                if (poz != -1) {
                    ss1 = p.getStudents().get(poz);
                    System.out.println("urm fara perche este " + ss1.getName());
                    if (ss1.getNrOpt() == ss1.getNrCurrent()) {
                        System.out.println(ss1.getName() + "nu are perche");
                        viz[p.getStudents().indexOf(ss1)] = 1;
                    }
                }
            }
        }
    }

    public Student replaceEq(List<Student> std, Student student, School h) {
        int max = -1;
        Student rm = new Student();
        for (Student s : std) {
            if ((!(schoolPrefEq.get(h).contains(s))) && (schoolPrefEq.get(h).contains(student)))
                return s; // poate face replace -ce are el nu e prin topuri
            else if ((schoolPrefEq.get(h).contains(s))) {
                if (schoolPrefEq.get(h).indexOf(s) > max) {
                    max = schoolPrefEq.get(h).indexOf(s);
                    rm = s;
                }
            }

        }
        if (schoolPrefEq.get(h).contains(student) && schoolPrefEq.get(h).indexOf(student) < max && rm != null)
            return rm;
        return null;
    }

    public Map<School, List<Student>> getSchoolPrefEq() {
        return schoolPrefEq;
    }

    public Map<Student, List<School>> getStudPrefEq() {
        return studPrefEq;
    }
}
