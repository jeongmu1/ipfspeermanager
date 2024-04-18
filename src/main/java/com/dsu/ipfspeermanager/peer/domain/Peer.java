package com.dsu.ipfspeermanager.peer.domain;

import com.dsu.ipfspeermanager.global.domain.BaseTimeEntity;
import com.dsu.ipfspeermanager.peergroup.domain.PeerGroup;
import com.dsu.ipfspeermanager.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Peer extends BaseTimeEntity {

    @Column(nullable = false)
    private String peerId; // CID

    @Column(nullable = false, length = 1024)
    private String publicKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private PeerGroup peerGroup;

    @Column(nullable = false)
    private boolean enabled = true;
}
