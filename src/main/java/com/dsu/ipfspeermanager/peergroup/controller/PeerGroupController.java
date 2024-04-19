package com.dsu.ipfspeermanager.peergroup.controller;

import static com.dsu.ipfspeermanager.global.util.HttpStatusResponseEntity.*;

import com.dsu.ipfspeermanager.peer.dto.response.PeerInfo;
import com.dsu.ipfspeermanager.peergroup.dto.request.PeerGroupCreation;
import com.dsu.ipfspeermanager.peergroup.service.PeerGroupService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
