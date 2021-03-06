package com.twu.biblioteca.action.item;

import com.twu.biblioteca.Application;
import com.twu.biblioteca.Library;
import com.twu.biblioteca.io.IO;
import com.twu.biblioteca.item.Item;

import java.util.List;

public class CheckoutMovieAction extends CheckoutItemAction {

    public CheckoutMovieAction(IO io, Library lib, Application app) {
        super(io, lib, app);
    }

    @Override
    List<? extends Item> getItems() {
        return lib.getMovieList();
    }

    @Override
    String getItemType() {
        return "movie";
    }

    @Override
    protected String getTitle() {
        return "Checkout a movie";
    }
}
