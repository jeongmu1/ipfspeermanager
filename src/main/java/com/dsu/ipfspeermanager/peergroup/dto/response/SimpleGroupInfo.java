package com.dsu.ipfspeermanager.peergroup.dto.response;

import com.dsu.ipfspeermanager.peergroup.domain.PeerGroup;
import lombok.Builder;

@Builder
public record SimpleGroupInfo(
    long id,
    long hostId,
    String name
) {

    public static SimpleGroupInfo from(final PeerGroup peerGroup) {
        return SimpleGroupInfo.builder()
            .id(peerGroup.getId())
            .hostId(peerGroup.getHostId())
            .name(peerGroup.getName())
            .build();
    }
}
