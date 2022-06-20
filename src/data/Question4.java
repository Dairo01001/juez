package data;

public class Question4 {
    public static void main(String[] args) {

        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);

        for (int i = 0; i < x; i++) {
            y += y;
        }
        System.out.println(y);
    }
}
