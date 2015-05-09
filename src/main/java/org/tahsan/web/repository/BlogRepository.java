package org.tahsan.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tahsan.web.entity.Blog;
import org.tahsan.web.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
}
