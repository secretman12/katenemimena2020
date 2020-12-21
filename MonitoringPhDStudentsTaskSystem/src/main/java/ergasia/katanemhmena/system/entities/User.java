package ergasia.katanemhmena.system.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.*;
import com.sun.istack.NotNull;

import ergasia.katanemhmena.system.enums.Role;

@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		  name="USER_TYPE", 
		  discriminatorType=DiscriminatorType.STRING
		  )
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private Role role;
	@Column(unique = true)
	String username;
	@NotNull
	private String password;
	@Column(length = 45, nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	@Pattern(regexp = "^[\\p{L} .'-]+$",message = "Fullname is invalid !")
	private String full_name;
	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean enabled = true;
	@NotNull
    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[0-9]+", message = "Mobile number is invalid!!")	
	private String phone;
	public User(Role role, String username, String password, String email, String full_name,
			String phone) {
		super();
		this.role = role;
		this.username = username;
		this.password = password;
		this.email = email;
		this.full_name = full_name;
		this.phone = phone;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", username=" + username + ", password=" + password + ", email="
				+ email + ", full_name=" + full_name + ", enabled=" + enabled + ", phone=" + phone + "]";
	}
	
}
