package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem {
    private int numberOfItems; // Liczba przedmiotów
    private int seed; // Ziarno dla generatora losowego
    private int lowerBound; // Dolna granica wartości i wagi przedmiotu
    private int upperBound; // Górna granica wartości i wagi przedmiotu
    private List<Item> items; // Lista przechowująca wygenerowane przedmioty

    public Problem(int numberOfItems, int seed, int lowerBound, int upperBound) {
        this.numberOfItems = numberOfItems;
        this.seed = seed;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.items = new ArrayList<>(); // Inicjalizacja listy przedmiotów
        generateItems(); // Generowanie przedmiotów
    }

    private void generateItems() {
        Random random = new Random(seed); // Ustawienie ziarna dla generatora losowego
        for (int i = 0; i < numberOfItems; i++) {
            int value = lowerBound + random.nextInt(upperBound - lowerBound + 1); // Losowanie wartości przedmiotu
            int weight = lowerBound + random.nextInt(upperBound - lowerBound + 1); // Losowanie wagi przedmiotu
            items.add(new Item(i + 1, value, weight)); // Dodanie przedmiotu do listy
        }
    }

    public List<Item> getItems() {
        return items; // Zwracanie listy przedmiotów
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Item item : items) {
            result.append(item.toString()).append("\n"); // Dodanie opisu każdego przedmiotu do wyniku
        }
        return result.toString();
    }

    // Definicja klasy Item
    public static class Item {
        int number; // Numer przedmiotu
        int value; // Wartość przedmiotu
        int weight; // Waga przedmiotu

        public Item(int number, int value, int weight) {
            this.number = number;
            this.value = value;
            this.weight = weight;
        }

        public int getNumber() {
            return number;
        }

        public int getValue() {
            return value;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Item{" + "number=" + number + ", value=" + value + ", weight=" + weight + '}';
        }
    }

    public Result solve(int capacity) {
        // Sortowanie przedmiotów malejąco według stosunku wartość/waga
        items.sort((a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));
        int remainingCapacity = capacity; // Pozostała pojemność plecaka
        List<Item> selectedItems = new ArrayList<>(); // Lista wybranych przedmiotów

        for (Item item : items) {
            if (remainingCapacity >= item.weight) {
                selectedItems.add(item); // Dodanie przedmiotu do listy wybranych przedmiotów
                remainingCapacity -= item.weight; // Zmniejszenie pozostałej pojemności plecaka
            }
        }

        return new Result(selectedItems); // Zwracanie wyniku jako obiektu Result
    }

    // Definicja klasy Result
    public static class Result {
        List<Item> items; // Lista wybranych przedmiotów

        public Result(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items; // Zwracanie listy wybranych przedmiotów
        }

        @Override
        public String toString() {
            int totalValue = items.stream().mapToInt(Item::getValue).sum(); // Sumowanie wartości wybranych przedmiotów
            int totalWeight = items.stream().mapToInt(Item::getWeight).sum(); // Sumowanie wagi wybranych przedmiotów
            StringBuilder result = new StringBuilder();
            result.append("Total value: ").append(totalValue).append("\n"); // Dodanie sumarycznej wartości do wyniku
            result.append("Total weight: ").append(totalWeight).append("\n"); // Dodanie sumarycznej wagi do wyniku
            result.append("Items in knapsack: ");
            for (Item item : items) {
                result.append(item.getNumber()).append(" "); // Dodanie numerów wybranych przedmiotów do wyniku
            }
            result.append("\n");
            return result.toString();
        }
    }
}
