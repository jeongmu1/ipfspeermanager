package com.dsu.ipfspeermanager.peergroup.service;

import com.dsu.ipfspeermanager.peer.domain.Peer;
import com.dsu.ipfspeermanager.peer.dto.response.PeerInfo;
import com.dsu.ipfspeermanager.peergroup.domain.GroupAccess;
import com.dsu.ipfspeermanager.peergroup.domain.PeerGroup;
import com.dsu.ipfspeermanager.peergroup.dto.request.GroupInvitation;
import com.dsu.ipfspeermanager.peergroup.dto.request.PeerGroupCreation;
import com.dsu.ipfspeermanager.peergroup.dto.response.SimpleGroupInfo;
import com.dsu.ipfspeermanager.peergroup.exception.ForbiddenAccessException;
import com.dsu.ipfspeermanager.peergroup.repository.GroupAccessRepository;
import com.dsu.ipfspeermanager.peergroup.repository.PeerGroupRepository;
import com.dsu.ipfspeermanager.user.domain.User;
import com.dsu.ipfspeermanager.user.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Transactional
    public void addGroupAccess(
        final GroupInvitation dto,
        final long groupId,
        final String username
    ) {
        final User inviter = userRepository.findByUsername(username).orElseThrow();
        final PeerGroup group = findAndHandleNullability(groupId);
        checkAccessibility(inviter, group);

        final User targetUser = userRepository.findById(dto.userId())
            .orElseThrow(() -> new NoSuchElementException("해당 사용자를 찾을 수 없습니다 : " + dto.userId()));
        groupAccessRepository.save(new GroupAccess(targetUser, group));
    }

    @Transactional(readOnly = true)
    public List<PeerInfo> getPeersOfGroup(
        final long groupId,
        final String requesterUsername
    ) {
        final User requester = userRepository.findByUsername(requesterUsername).orElseThrow();
        final PeerGroup group = findAndHandleNullability(groupId);
        checkAccessibility(requester, group);

        return group.getPeers()
            .stream()
            .filter(Peer::isEnabled)
            .map(PeerInfo::from)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<SimpleGroupInfo> getSimpleGroupInfos(
        final String username
    ) {
        final User user = userRepository.findByUsername(username).orElseThrow();
        return groupAccessRepository.findGroupAccessesByUser(user)
            .stream()
            .map(GroupAccess::getPeerGroup)
            .map(SimpleGroupInfo::from)
            .toList();
    }

    private void checkAccessibility(final User user, final PeerGroup group) {
        if (!groupAccessRepository.existsByPeerGroupAndUser(group, user)) {
            throw new ForbiddenAccessException(group.getId());
        }
    }

    private PeerGroup findAndHandleNullability(final long groupId) {
        return peerGroupRepository.findById(groupId)
            .orElseThrow(() -> new NoSuchElementException("해당 그룹을 찾을 수 없습니다 : " + groupId));
    }
}
