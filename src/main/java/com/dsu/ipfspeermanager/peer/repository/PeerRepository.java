package com.dsu.ipfspeermanager.peer.repository;

import com.dsu.ipfspeermanager.peer.domain.Peer;
import com.dsu.ipfspeermanager.peergroup.domain.PeerGroup;
import com.dsu.ipfspeermanager.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface PeerRepository extends CrudRepository<Peer, Long> {

    boolean existsByPeerGroupAndUser(final PeerGroup peerGroup, final User user);
}
