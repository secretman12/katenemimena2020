package ergasia.katanemhmena.system.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ergasia.katanemhmena.system.enums.General_member_type;
import ergasia.katanemhmena.system.enums.Role;

@Entity
@DiscriminatorValue("General_member")
public class General_member extends User {
    @Enumerated(EnumType.STRING)
	General_member_type type;

	public General_member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public General_member(Role role, String username, String password, String email, String full_name, String phone,
			General_member_type type) {
		super(role, username, password, email, full_name, phone);
		this.type = type;
	}

	public General_member(Role role, String username, String password, String email, String full_name, String phone) {
		super(role, username, password, email, full_name, phone);
	}

	public General_member_type getType() {
		return type;
	}

	public void setType(General_member_type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "General_member [type=" + type + ", id=" + id + ", username=" + username + "]";
	}


}
