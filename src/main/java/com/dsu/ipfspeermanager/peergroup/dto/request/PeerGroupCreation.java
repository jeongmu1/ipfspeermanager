package com.dsu.ipfspeermanager.peergroup.dto.request;

import com.dsu.ipfspeermanager.peergroup.domain.PeerGroup;
import com.dsu.ipfspeermanager.user.domain.User;
import jakarta.validation.constraints.NotBlank;

public record PeerGroupCreation(
    @NotBlank
    String name,
    @NotBlank
    String swarm
) {

    public PeerGroup toEntity(final User host) {
        return PeerGroup.builder()
            .name(name)
            .swarm(swarm)
            .host(host)
            .build();
    }
}
