package com.sontud.jwt.service;

import com.sontud.jwt.model.UserDao;
import com.sontud.jwt.model.UserDto;
import com.sontud.jwt.repository.UserRepository;
import com.sontud.jwt.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private CommonUtil commonUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found : " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	public UserDao save(UserDto user) {
		UserDao newUser = new UserDao();
		String memberType = commonUtil.checkMemberType(user.getSalary());
			if(memberType == "Reject"){
				newUser.setMessage("Reject Salary < 15000");
				newUser.setCode("400");
			}else{
				newUser.setRefId(commonUtil.doGenerateRefId(user));
				newUser.setUsername(user.getUsername());
				newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
				newUser.setfName(user.getfName());
				newUser.setlName(user.getlName());
				newUser.setPhoneNo(user.getPhoneNo());
				newUser.setAddress(user.getAddress());
				newUser.setSalary(user.getSalary());
				newUser.setMemberType(memberType);
				try{
					userDao.save(newUser);
					newUser.setMessage("Success");
					newUser.setCode("200");
				}catch(Exception e){
					newUser.setMessage("Insert Not Success");
					newUser.setCode("400");
				}
			}
		return newUser;
	}
}