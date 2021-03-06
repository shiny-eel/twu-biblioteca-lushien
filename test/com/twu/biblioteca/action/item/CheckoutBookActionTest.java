package com.twu.biblioteca.action.item;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.account.User;
import com.twu.biblioteca.item.Book;
import com.twu.biblioteca.io.IO;
import com.twu.biblioteca.io.IOHarness;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class CheckoutBookActionTest {



    @Test
    public void successMessageTest() {
        IOHarness harness = new IOHarness();
        IO io = harness.createTestIO("Art of War");
        BibliotecaApp app = new BibliotecaApp(io);
        CheckoutBookAction action = new CheckoutBookAction(io, app, app);
        action.execute();

        assertThat(harness.getOutput(), (containsString("Thank you! Enjoy the book")));
    }

    @Test
    public void promptMessageTest() {
        IOHarness harness = new IOHarness();
        IO io = harness.createTestIO("");
        BibliotecaApp app = new BibliotecaApp(io);
        CheckoutBookAction action = new CheckoutBookAction(io, app, app);
        try {
            action.execute();
        } catch (NoSuchElementException e) {
        }
        assertThat(harness.getOutput(), (containsString("Enter a book title to checkout:")));
    }

    @Test
    public void makeUnavailableTest() {
        IOHarness harness = new IOHarness();
        IO io = harness.createTestIO("Infinite Jest");
        BibliotecaApp app = new BibliotecaApp(io);
        app.logIn(mock(User.class));
        List<Book> bookList = app.getBookList();
        Book testBook = bookList.get(1);
        assertThat(testBook.isAvailable(), is(true));

        CheckoutBookAction action = new CheckoutBookAction(io, app, app);
        action.execute();

        assertThat(testBook.isAvailable(), is(false));
    }

    @Test
    public void UnavailableTest() {
        IOHarness harness = new IOHarness();
        IO io = harness.createTestIO("Infinite Jest");
        BibliotecaApp app = new BibliotecaApp(io);
        app.getBookList().get(1).borrow(mock(User.class));
        CheckoutBookAction action = new CheckoutBookAction(io, app, app);
        action.execute();

        assertThat(harness.getOutput(), (containsString("Sorry, that book is not available")));

    }

    @Test
    public void NonexistentCheckoutTest() {
        IOHarness harness = new IOHarness();
        IO io = harness.createTestIO("Non-existent book");
        BibliotecaApp app = new BibliotecaApp(io);

        CheckoutBookAction action = new CheckoutBookAction(io, app, app);
        action.execute();

        assertThat(harness.getOutput(), (containsString("Sorry, that book is not available")));

    }



}