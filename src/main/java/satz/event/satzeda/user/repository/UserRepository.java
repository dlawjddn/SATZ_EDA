package satz.event.satzeda.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import satz.event.satzeda.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
