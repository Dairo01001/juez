package data;

public class Question5 {    
    public static void main(String[] args) {
        
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        
        if(x > 5) {
            System.out.println(x + y);
        } else {
            System.out.println(x - y);
        }
    }
}
