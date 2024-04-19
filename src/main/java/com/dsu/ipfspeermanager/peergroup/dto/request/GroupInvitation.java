package com.dsu.ipfspeermanager.peergroup.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GroupInvitation(
    @NotNull
    @Min(1)
    Long userId
) {

}
