package org.example;


public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem(10, 1, 1, 10);
        System.out.println("Generated items:");
        System.out.println(problem);

        Problem.Result result = problem.solve(50);
        System.out.println("Solution:");
        System.out.println(result);
    }
}
