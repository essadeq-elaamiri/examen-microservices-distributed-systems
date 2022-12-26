package me.elaamiri.exceptions;

public class EntryNotFoundException extends RuntimeException{
    public EntryNotFoundException(String message) {
        super(message);
    }

    public EntryNotFoundException(String entryName, Object providedId) {
        super(String.format("Entry %s with ID [%s] Not Found!", entryName, providedId.toString()));
    }

//
//    public EntryNotFoundException(String entryName) {
//        super(String.format("Entry %s Not Found!", entryName));
//    }
}
