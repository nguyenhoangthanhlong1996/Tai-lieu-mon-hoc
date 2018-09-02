package baitapcongnghejava;

public class Test {

    static void KeO(int d, int c) {
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print("+---");
            }
            System.out.print("+\n");
            for (int j = 0; j < c; j++) {
                System.out.print("|\u2006\u2006\u2006\u2006\u2006\u2006\u2006\u2006\u2006");
            }
            System.out.print("|\n");
        }
        for (int j = 0; j < c; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    public static void main(String[] args) {

        KeO(5, 5);

    }

}
