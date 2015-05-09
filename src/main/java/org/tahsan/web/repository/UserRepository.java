package org.tahsan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tahsan.web.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

}
