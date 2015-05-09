package org.tahsan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tahsan.web.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
