package com.learning.fuelpricehistory.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApplicationUser implements GenericModel<Long> {

	public static final String PK = "id";
	public static final String FK = "user_id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = PK)
	private Long id;

	@Column(name = "name")
	@NotNull(message = "name can not be null")
	private String name;

	@Column(name = "username", unique = true)
	@Email(message = "Email need be valid")
	@NotNull(message = "Email can not be null")
	private String username;

	@NotNull(message = "Password can not be null")
	@Size(min = 8, message = "Password must be longer than 8 characters")
	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_authorities", 
        joinColumns = { @JoinColumn(name = FK,nullable = false) }, 
        inverseJoinColumns = { @JoinColumn(name = Authority.FK,nullable = false) }
    )
	private Set<Authority> authorities;
}
