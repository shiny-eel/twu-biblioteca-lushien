package com.twu.biblioteca.action;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Book;
import com.twu.biblioteca.io.IO;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ActionManagerTest {
    private ActionManager am;
    private BibliotecaApp mockApp;
    private ByteArrayInputStream is;
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        mockApp = mock(BibliotecaApp.class);
        when(mockApp.getBookList()).thenReturn(createFakeList());
    }

    public void start(String input) {
        is = new ByteArrayInputStream(input.getBytes());
        IO mockIO = new IO(is, new PrintStream(out));
        am = new ActionManager(mockApp, mockIO);
        try {
            am.start();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void testMenuDisplayOptions() {
        start("");
        String expected = "1. List of books\n" + "2. Quit";
        assertThat(out.toString(), containsString(expected));
    }


    @Test
    public void testSelectListBooks() {
        start("1");
        String expected = "Test Book | Foo Bar | 999\n" + "Another One | Rubber Ducky | 1";
        assertThat(out.toString(), containsString(expected));
    }

    @Test
    public void testInvalidOptionSelect() {
        start("0");
        String invalid = "Please select a valid option!";
        assertThat(out.toString(), containsString(invalid));

    }

    @Test
    public void testQuitOptionSelect() {
        start("2"); // Assuming 2 is the quit option
        verify(mockApp).quit();
    }

    /**
     * May be needed in future
     * assertThat(mockIO.getLast(), is("Art of War | Sun Tzu | 500\n" +
     * "Infinite Jest | David Foster Wallace | 1996\n" +
     * "David and Goliath | Malcolm Gladwell | 2013"));
     */

    private List<Book> createFakeList() {
        List<Book> li = new LinkedList<>();
        Book b = new Book("Test Book", "Foo Bar", 999);
        li.add(b);
        b = new Book("Another One", "Rubber Ducky", 1);
        li.add(b);
        return li;
    }

}