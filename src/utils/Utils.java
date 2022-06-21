package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Utils {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String DIR_PROJECT = System.getProperty("user.dir");
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String DIR_JAVA = DIR_PROJECT + SEPARATOR + "src" + SEPARATOR + "data";
    public static final String DIR_IMG = DIR_PROJECT + SEPARATOR + "src" + SEPARATOR + "data";
    public static final String DIR_SCORE = DIR_PROJECT + SEPARATOR + "src" + SEPARATOR + "score" + SEPARATOR;
    public static final String PASSWORD = "1234";

    public static void copyFile(File source, File dest) {
        try {
            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al copiar el archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String execJavaFile(String[] parametros, String javaFile) {
        try {
            Process process = Runtime.getRuntime().exec("java " + "src" + SEPARATOR + "data" + SEPARATOR + javaFile + " " + String.join(" ", parametros));
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            return new BufferedReader(inputStreamReader).lines().collect(Collectors.joining());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar el archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }

    public static String execJavaFile(String javaFile) {
        return execJavaFile(new String[]{}, javaFile);
    }

    public static String execJavaFile(String javaFile, String parametro) {
        return execJavaFile(new String[]{parametro}, javaFile);
    }

    public static String[] listOfFileString(String dir) {
        File[] files = new File(dir).listFiles();
        String[] list = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            list[i] = files[i].getName();
        }

        return list;
    }

    public static ArrayList<String> listOfFileString(String dir, String extension) {
        File[] files = new File(dir).listFiles();
        ArrayList<String> list = new ArrayList<>();  

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(extension)) {
                list.add(files[i].getName());
            }
        }

        return list;
    }

    public static int getNumberQuestion() {
        return new File(DIR_IMG).listFiles().length / 2;
    }

    public static String getAnswer( String[] param, int number) {
        return execJavaFile(param, "Question" + number + ".java");
    }
    
    public static String getPuntaje() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%10s %5s\n", "Nombre", "Putaje"));
        try {
            File doc = new File(DIR_SCORE + "score.txt");
            try (Scanner sc = new Scanner(doc)) {
                while(sc.hasNextLine()) {
                    sb.append(sc.nextLine() + "\n");
                }
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }
    
    public static void setPuntaje(String line) {
        try {
            FileWriter fileWriter = new FileWriter(DIR_SCORE + "score.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line);
        } catch (IOException e) {
        }
    }
}
