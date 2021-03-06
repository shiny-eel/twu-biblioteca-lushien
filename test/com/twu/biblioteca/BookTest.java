package com.twu.biblioteca;

import com.twu.biblioteca.item.Book;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookTest {

    @Test
    public void testToString() {
        Book book = new Book("A", "B", 2000);
        assertThat(book.toString(), is("A                     | B                     | 2000"));

    }

}