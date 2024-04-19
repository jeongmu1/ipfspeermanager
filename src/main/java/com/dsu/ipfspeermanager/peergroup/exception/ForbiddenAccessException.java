package com.dsu.ipfspeermanager.peergroup.exception;

public class ForbiddenAccessException extends RuntimeException {

    public ForbiddenAccessException(final long groupId) {
        super("해당 그룹에 접근할 권한이 없습니다 : " + groupId);
    }
}
