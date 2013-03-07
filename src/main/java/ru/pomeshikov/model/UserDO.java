package ru.pomeshikov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="User")
@Data
public class UserDO {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="ukey")
	private String ukey;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	
	
}
