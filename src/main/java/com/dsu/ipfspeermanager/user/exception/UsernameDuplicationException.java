package com.dsu.ipfspeermanager.user.exception;

public class UsernameDuplicationException extends IllegalArgumentException {

    public UsernameDuplicationException(final String s) {
        super(s);
    }
}
