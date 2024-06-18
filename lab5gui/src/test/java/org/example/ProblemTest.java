package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProblemTest {

    @Test
    public void testItemGeneration() {
        Problem problem = new Problem(10, 1, 1, 10);
        // Sprawdzamy, czy liczba wygenerowanych przedmiotów jest zgodna z oczekiwaniami
        assertEquals(10, problem.getItems().size());
    }

    @Test
    public void testSolutionWithItems() {
        Problem problem = new Problem(10, 1, 1, 10);
        Problem.Result result = problem.solve(50);
        // Sprawdzamy, czy rozwiązanie zawiera co najmniej jeden przedmiot, gdy pojemność plecaka jest wystarczająca
        assertTrue(result.getItems().size() > 0);
    }

    @Test
    public void testSolutionWithoutItems() {
        Problem problem = new Problem(10, 1, 1, 10);
        Problem.Result result = problem.solve(0);
        // Sprawdzamy, czy rozwiązanie nie zawiera przedmiotów, gdy pojemność plecaka wynosi 0
        assertEquals(0, result.getItems().size());
    }

    @Test
    public void testItemsInRange() {
        Problem problem = new Problem(10, 1, 1, 10);
        for (Problem.Item item : problem.getItems()) {
            // Sprawdzamy, czy wartość każdego przedmiotu jest w zadanym zakresie
            assertTrue(item.getValue() >= 1 && item.getValue() <= 10);
            // Sprawdzamy, czy waga każdego przedmiotu jest w zadanym zakresie
            assertTrue(item.getWeight() >= 1 && item.getWeight() <= 10);
        }
    }

    @Test
    public void testCorrectSolution() {
        Problem problem = new Problem(5, 1, 1, 10);
        Problem.Result result = problem.solve(10);
        int totalWeight = result.getItems().stream().mapToInt(Problem.Item::getWeight).sum();
        int totalValue = result.getItems().stream().mapToInt(Problem.Item::getValue).sum();
        // Sprawdzamy, czy całkowita waga wybranych przedmiotów nie przekracza pojemności plecaka
        assertTrue(totalWeight <= 10);
        // Sprawdzamy, czy całkowita wartość wybranych przedmiotów jest większa od 0
        assertTrue(totalValue > 0);
    }
}
