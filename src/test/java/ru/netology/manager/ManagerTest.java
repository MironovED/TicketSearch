package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.Repository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Repository repo = new Repository();
    Manager manager = new Manager(repo);

    Ticket one = new Ticket(1, 10000, "VKO", "KZN", 90);
    Ticket two = new Ticket(2, 12000, "KZN", "VKO", 100);
    Ticket three = new Ticket(3, 14000, "SVO", "AER", 120);
    Ticket four = new Ticket(4, 9000, "VKO", "KZN", 85);
    Ticket five = new Ticket(5, 16000, "KZN", "VKO", 85);
    Ticket six = new Ticket(6, 5000, "KZN", "VKO", 85);
    Ticket seven = new Ticket(7, 11000, "VKO", "KZN", 85);
    Ticket eight = new Ticket(8, 14000, "SVO", "AER", 90);
    Ticket nine = new Ticket(9, 20000, "DME", "WO", 360);



    @Test
    void shouldSearchAndSort() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);

        Ticket[] actual = manager.searchBy("VKO", "KZN");
        Ticket[] expected = {four, one, seven};
        Arrays.sort(actual);

        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSearchAndSortWhenNotCoincidences() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);
        manager.add(nine);

        Ticket[] actual = manager.searchBy("KZN", "AER");
        Ticket[] expected = {};
        Arrays.sort(actual);

        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSearchAndSortWhenOneCoincidences() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);
        manager.add(nine);

        Ticket[] actual = manager.searchBy("DME", "WO");
        Ticket[] expected = {nine};
        Arrays.sort(actual);

        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSearchAndSortWhenTwoCoincidencesAndSamePrice() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);
        manager.add(nine);

        Ticket[] actual = manager.searchBy("SVO", "AER");
        Ticket[] expected = {three, eight};
        Arrays.sort(actual);

        assertArrayEquals(actual, expected);
    }

}