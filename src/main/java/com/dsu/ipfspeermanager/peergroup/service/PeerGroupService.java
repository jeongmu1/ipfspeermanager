package com.dsu.ipfspeermanager.peergroup.service;

import com.dsu.ipfspeermanager.peer.domain.Peer;
import com.dsu.ipfspeermanager.peer.dto.response.PeerInfo;
import com.dsu.ipfspeermanager.peer.repository.PeerRepository;
import com.dsu.ipfspeermanager.peergroup.domain.GroupAccess;
import com.dsu.ipfspeermanager.peergroup.domain.PeerGroup;
import com.dsu.ipfspeermanager.peergroup.dto.request.PeerGroupCreation;
import com.dsu.ipfspeermanager.peergroup.repository.GroupAccessRepository;
import com.dsu.ipfspeermanager.peergroup.repository.PeerGroupRepository;
import com.dsu.ipfspeermanager.user.domain.User;
import com.dsu.ipfspeermanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PeerGroupService {

    private final PeerGroupRepository peerGroupRepository;
    private final UserRepository userRepository;
    private final GroupAccessRepository groupAccessRepository;

    @Transactional
    public void addPeerGroup(
        final PeerGroupCreation dto,
        final String username
    ) {
        final User user = userRepository.findByUsername(username).orElseThrow();
        final PeerGroup group = peerGroupRepository.save(dto.toEntity(user));
        groupAccessRepository.save(new GroupAccess(user, group));
    }
}
