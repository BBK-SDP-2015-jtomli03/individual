package sml;

/**
 * Thrown when a duplicate is found when there shouldn't be one, and as a result the program should terminate.
 *
 * Created by Jo on 09/02/2015.
 */
public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException(String message){
        System.out.println(message);
    }
}
