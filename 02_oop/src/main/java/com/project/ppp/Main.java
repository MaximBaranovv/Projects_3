package com.project.ppp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        Writable [ ] writables = {new Pencil(), new Pencil(), new Pencil(), new Marker(), new Marker(), new Marker(), new Marker(), new Pen(), new Pen(), new Pen() };
        Random rand = new Random();
        for (int i = 0; i < writables.length; i++) {
            int randomIndexToSwap = rand.nextInt(writables.length);
            Writable temp = writables[randomIndexToSwap];
            writables[randomIndexToSwap] = writables[i];
            writables[i] = temp;
        }

        int min1 = 3;
        int max1 = 5;
        Random random = new Random();

        for (int u = 0; u <writables.length ; u++) {
            if(writables[u].getName().equals("Pen") || writables[u].getName().equals("Marker")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("|Sorry, Pen and Marker can`t erase|");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
            }
            for (int i = 0; i < 10; i++) {
                int randomNum = random.nextInt((max1 - min1) + 1) + min1;
                char[] options = {'a','b','c','d','e','f','g','h','i','j'};
                char[] result = new char[randomNum];
                Random random1 = new Random();
                    for(int j=0;j<result.length;j++){
                        result[j]=options[random1.nextInt(options.length)];
                    }
                writables[u].write(stringBuilder, result);
            }
            System.out.println("--------------------------------------------------------------------");
        }
        System.out.println("Sorted array:");
        Arrays.stream(writables).sorted(Comparator.comparing(Writable::getCore)).forEach(System.out::println);

    }
}
