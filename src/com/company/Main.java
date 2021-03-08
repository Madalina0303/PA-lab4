package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        var stud = IntStream.rangeClosed(0, 3).mapToObj(i -> new Student("S" + i)).toArray(Student[]::new);

        List<Student> stdList = new LinkedList<>();
        stdList.addAll(Arrays.asList(stud));
        var sch = IntStream.rangeClosed(0, 2).mapToObj(i -> new School("H" + i)).toArray(School[]::new);
        Set<School> schList = new TreeSet<>();
        schList.addAll(Arrays.asList(sch));
    }


}
