package com.dsu.ipfspeermanager.peergroup.controller;

import static com.dsu.ipfspeermanager.global.util.HttpStatusResponseEntity.*;

import com.dsu.ipfspeermanager.peergroup.dto.request.GroupInvitation;
import com.dsu.ipfspeermanager.peergroup.dto.request.PeerGroupCreation;
import com.dsu.ipfspeermanager.peergroup.service.PeerGroupService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/peer-groups")
public class PeerGroupController {

    private final PeerGroupService peerGroupService;

    @PostMapping
    public ResponseEntity<HttpStatus> createPeerGroup(
        @RequestBody final PeerGroupCreation dto,
        final Principal principal
    ) {
        peerGroupService.addPeerGroup(dto, principal.getName());
        return RESPONSE_CREATED;
    }

    @PostMapping("/{id}/invitations")
    public ResponseEntity<HttpStatus> inviteGroup(
        @RequestBody final GroupInvitation dto,
        @PathVariable("id") final long id,
        final Principal principal
    ) {
        peerGroupService.addGroupAccess(dto, id, principal.getName());
        return RESPONSE_CREATED;
    }
}
