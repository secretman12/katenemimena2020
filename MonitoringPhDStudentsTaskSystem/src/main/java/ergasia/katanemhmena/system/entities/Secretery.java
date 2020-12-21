package ergasia.katanemhmena.system.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import ergasia.katanemhmena.system.enums.Role;

@Entity
@DiscriminatorValue("Secretery")
public class Secretery extends User {

	public Secretery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Secretery(Role role, String username, String password, String email, String full_name, String phone) {
		super(role, username, password, email, full_name, phone);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Secretery [id=" + id + ", username=" + username + "]";
	}
	
}
