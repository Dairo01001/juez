package main;

import java.util.Arrays;

import utils.Utils;


public class Main {

    public static void main(String[] args) {

        String[]  line =  Utils.listOfFileString(Utils.DIR_JAVA);
        System.out.println(Arrays.toString(line));
    }
}
