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
    Ticket three = new Ticket(3, 9000, "VKO", "KZN", 120);
    Ticket four = new Ticket(4, 7000, "VKO", "KZN", 85);
    Ticket five = new Ticket(5, 16000, "KZN", "WO", 90);
    Ticket six = new Ticket(6, 5000, "KZN", "VKO", 95);
    Ticket seven = new Ticket(7, 11000, "VKO", "KZN", 85);
    Ticket eight = new Ticket(8, 14000, "SVO", "AER", 90);
    Ticket nine = new Ticket(9, 20000, "VKO", "KZN", 360);
    Ticket ten = new Ticket(10, 5000, "VKO", "KZN", 260);



    @Test
    void shouldSearchAndSort() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);
        manager.add(nine);
        manager.add(ten);

        Ticket[] actual = manager.searchBy("VKO", "KZN");
        Ticket[] expected = {ten, four, three, one, seven, nine};


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
        manager.add(ten);

        Ticket[] actual = manager.searchBy("KZN", "AER");
        Ticket[] expected = {};

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
        manager.add(ten);

        Ticket[] actual = manager.searchBy("SVO", "AER");
        Ticket[] expected = {eight};
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
        manager.add(ten);

        Ticket[] actual = manager.searchBy("KZN", "VKO");
        Ticket[] expected = {six, two};
        Arrays.sort(actual);

        assertArrayEquals(actual, expected);
    }

}