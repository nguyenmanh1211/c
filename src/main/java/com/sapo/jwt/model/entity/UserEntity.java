package com.sapo.jwt.model.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column
	private Integer status;

	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "permission",
			joinColumns = @JoinColumn(name = "user_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
	private List<RoleEntity> roles = new ArrayList<>();


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
}
