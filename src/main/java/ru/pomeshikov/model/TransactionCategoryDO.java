package ru.pomeshikov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TransactionCategory")
@Data
public class TransactionCategoryDO {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="ukey")
	private String ukey;
	
	@Column(name="name")
	private String name;
	
}
