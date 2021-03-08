package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        var stud = IntStream.rangeClosed(0, 3).mapToObj(i -> new Student("S" + i)).toArray(Student[]::new);

        List<Student> stdList = new LinkedList<>();
        stdList.addAll(Arrays.asList(stud));

    }


}
