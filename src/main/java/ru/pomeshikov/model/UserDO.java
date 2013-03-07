package ru.pomeshikov.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="User")
@Data
public class UserDO {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="ukey")
	private String ukey;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
	private List<TransactionDO> transactions;
	
}
