package ergasia.katanemhmena.system.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ergasia.katanemhmena.system.enums.Role;

@Entity
@DiscriminatorValue("Supervisor")
public class Supervisor extends User{
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "phdStudent_id", nullable = true,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	PhDStudent phdStudent;

	public Supervisor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supervisor(Role role, String username, String password, String email, String full_name, String phone,
			PhDStudent phdStudent) {
		super(role, username, password, email, full_name, phone);
		this.phdStudent = phdStudent;
	}

	public Supervisor(Role role, String username, String password, String email, String full_name, String phone) {
		super(role, username, password, email, full_name, phone);
	}

	public PhDStudent getPhdStudent() {
		return phdStudent;
	}

	public void setPhdStudent(PhDStudent phdStudent) {
		this.phdStudent = phdStudent;
	}

	@Override
	public String toString() {
		return "Supervisor [phdStudent=" + phdStudent + ", id=" + id + ", username=" + username + "]";
	}
	
	
}
