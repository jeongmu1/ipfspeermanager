package com.dsu.ipfspeermanager.peergroup.repository;

import com.dsu.ipfspeermanager.peergroup.domain.GroupAccess;
import com.dsu.ipfspeermanager.peergroup.domain.PeerGroup;
import com.dsu.ipfspeermanager.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface GroupAccessRepository extends CrudRepository<GroupAccess, Long> {

    boolean existsByPeerGroupAndUser(final PeerGroup peerGroup, final User user);
}
