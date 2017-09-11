
package com.networknt.eventuate.reference.common.exception;


/**
 * Thrown when the given reference table has been used in the HOST.
 */
public class ReferenceDuplicatedException extends Exception {
    public ReferenceDuplicatedException(String message){
        super(message);
    }
}
