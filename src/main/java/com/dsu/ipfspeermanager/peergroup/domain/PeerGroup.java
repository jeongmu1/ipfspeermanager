package com.dsu.ipfspeermanager.peergroup.domain;

import com.dsu.ipfspeermanager.global.domain.BaseTimeEntity;
import com.dsu.ipfspeermanager.peer.domain.Peer;
import com.dsu.ipfspeermanager.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class PeerGroup extends BaseTimeEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String swarm;


    @ManyToOne(fetch = FetchType.LAZY)
    private User host;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Peer> peers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<GroupAccess> groupAccesses = new ArrayList<>();
}
