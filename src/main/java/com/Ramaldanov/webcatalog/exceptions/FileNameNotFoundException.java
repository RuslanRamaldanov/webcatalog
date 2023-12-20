package com.Ramaldanov.webcatalog.exceptions;

public class FileNameNotFoundException extends Exception {
    public FileNameNotFoundException() {
        super("No such file with this name");
    }
}
