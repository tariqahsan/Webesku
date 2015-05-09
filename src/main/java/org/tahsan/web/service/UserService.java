package org.tahsan.web.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tahsan.web.entity.Blog;
import org.tahsan.web.entity.Item;
import org.tahsan.web.entity.Role;
import org.tahsan.web.entity.User;
import org.tahsan.web.repository.BlogRepository;
import org.tahsan.web.repository.ItemRepository;
import org.tahsan.web.repository.RoleRepository;
import org.tahsan.web.repository.UserRepository;

@Service
@Transactional
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	public User findOne(int id) {
		
		return userRepository.findOne(id);
	}
	
	@Transactional
	public User findByOneWithBlogs(int id) {
		
		User user = findOne(id);
		List <Blog> blogs = blogRepository.findByUser(user);
		for(Blog blog : blogs) {			
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);			
		}
		
		user.setBlogs(blogs);
		
		return user;
		
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}

	public Object findByOneWithBlogs(String name) {
		User user = userRepository.findByName(name);
		return findByOneWithBlogs(user.getId());
	}

	public void delete(int id) {
		userRepository.delete(id);		
	}

	public User findOne(String username) {
		return userRepository.findByName(username);
		
	}
	
}
