package com.dsu.ipfspeermanager.user.repository;

import com.dsu.ipfspeermanager.user.domain.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(final String username);

    boolean existsUserByUsername(final String username);
}
