package com.twu.biblioteca.action;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.io.IO;

public abstract class Action {

    protected BibliotecaApp app;
    protected IO io;
    protected int id;

    public Action(BibliotecaApp app, IO io) {
        this.app = app;
        this.io = io;

    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean matches(int id) {
        return (this.id == id);
    }

    abstract String getTitle();

    abstract void execute();
}
