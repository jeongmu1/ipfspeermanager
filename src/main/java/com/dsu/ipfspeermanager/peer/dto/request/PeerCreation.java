package com.dsu.ipfspeermanager.peer.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PeerCreation(
    @NotBlank
    String peerId,
    @NotBlank
    String publicKey
) {

}
