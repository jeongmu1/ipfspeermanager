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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Peer extends BaseTimeEntity {

    @Column(nullable = false)
    private String peerId; // CID

    @Column(nullable = false, length = 1024)
    private String publicKey;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private PeerGroup peerGroup;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean socketConnected = false;

    public long getUserId() {
        return user.getId();
    }

    public void updateEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public void updateSocketConnected(final boolean socketConnected) {
        this.socketConnected = socketConnected;
    }

    @Builder
    public Peer(
        final String peerId,
        final String publicKey,
        final String address,
        final User user,
        final PeerGroup peerGroup
    ) {
        this.peerId = peerId;
        this.publicKey = publicKey;
        this.user = user;
        this.address = address;
        this.peerGroup = peerGroup;
    }
}
