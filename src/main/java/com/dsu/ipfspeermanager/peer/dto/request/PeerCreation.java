package com.dsu.ipfspeermanager.peer.dto.request;

import com.dsu.ipfspeermanager.peer.domain.Peer;
import com.dsu.ipfspeermanager.peergroup.domain.PeerGroup;
import com.dsu.ipfspeermanager.user.domain.User;
import jakarta.validation.constraints.NotBlank;

public record PeerCreation(
    @NotBlank
    String peerId,
    @NotBlank
    String publicKey,
    @NotBlank
    String address
) {

    public Peer toEntity(
        final User user,
        final PeerGroup peerGroup
    ) {
        return Peer.builder()
            .peerId(peerId)
            .publicKey(publicKey)
            .address(address)
            .user(user)
            .peerGroup(peerGroup)
            .build();
    }
}
