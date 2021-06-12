package net.royalmind.library.lightquery.exceptions;

public class EmptyValueException extends RuntimeException {

    public EmptyValueException(final String value, final String place) {
        super(String.format("%s isn't set in %s", value, place));
    }
}
