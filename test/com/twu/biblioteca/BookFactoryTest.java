package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BookFactoryTest {

    private BibliotecaApp mockApp;

    @Before
    public void setUp() throws Exception {
        mockApp = mock(BibliotecaApp.class);

    }


    @Test
    public void testReadJSON() throws Exception {
        BookFactory reader = new BookFactory(mockApp);
        reader.createBooks();
        assertThat(reader.getBookList().get(0).title, is("Art of War"));
        assertThat(reader.getBookList().get(1).title, is("Infinite Jest"));
        assertThat(reader.getBookList().get(2).title, is("David and Goliath"));

    }

    @Test
    public void testReadJSONDeep() throws Exception {
        BookFactory reader = new BookFactory(mockApp);
        reader.createBooks();
        assertThat(reader.getBookList().get(0).author, is("Sun Tzu"));
        assertThat(reader.getBookList().get(1).author, is("David Foster Wallace"));
        assertThat(reader.getBookList().get(2).author, is("Malcolm Gladwell"));

    }
}