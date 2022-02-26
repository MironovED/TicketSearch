package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Ticket;

import java.util.Arrays;
import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Repository {
    private Ticket[] items = new Ticket[0];

    public void save(Ticket item) {
        int length = items.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastindex = tmp.length - 1;
        tmp[lastindex] = item;
        items = tmp;
    }

    public Ticket[] findAll() {
        return items;
    }

    public Ticket[] findAll (String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0];
        for (Ticket item : items) {
            if (matches(item, from, to)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = item;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }

    public boolean matches(Ticket ticket, String from, String to) {
        if (ticket.getFrom().contains(from) && ticket.getTo().contains(to)) {
            return true;
        } else {
            return false;
        }
    }

    public void removeById(int id) {
        int length = items.length;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }

}
