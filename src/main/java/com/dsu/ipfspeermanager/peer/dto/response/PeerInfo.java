package com.dsu.ipfspeermanager.peer.dto.response;

import com.dsu.ipfspeermanager.peer.domain.Peer;
import lombok.Builder;

@Builder
public record PeerInfo(
    String peerId,
    long userId,
    String publicKey,
    String address
) {

    public static PeerInfo from(final Peer peer) {
        return PeerInfo.builder()
            .peerId(peer.getPeerId())
            .userId(peer.getUserId())
            .publicKey(peer.getPublicKey())
            .address(peer.getAddress())
            .build();
    }
}
