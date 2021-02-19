package ergasia.katanemhmena.system.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ergasia.katanemhmena.system.enums.Role;

@Entity
@DiscriminatorValue("PhDStudent")
public class PhDStudent extends User {
    @Column(columnDefinition = "integer default 0")
    private int teach_lab_hours;
    @Column(columnDefinition = "integer default 0")
	private int xp_per_task;
    @Column(columnDefinition = "integer default 0")
	private int surveillance_hours;
    @OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "task_id", nullable = true,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    Task task;


	public PhDStudent() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PhDStudent(Role role, String username, String password, String email, String full_name, String phone) {
		super(role, username, password, email, full_name, phone);
		// TODO Auto-generated constructor stub
	}



	public PhDStudent(Role role, String username, String password, String email, String full_name, boolean enabled,
			String age, int teach_lab_hours, int xp_per_task, int surveillance_hours, Task task) {
		super(role, username, password, email, full_name,  age);
		this.teach_lab_hours = teach_lab_hours;
		this.xp_per_task = xp_per_task;
		this.surveillance_hours = surveillance_hours;
		this.task = task;
	}

	public int getTeach_lab_hours() {
		return teach_lab_hours;
	}
	public void setTeach_lab_hours(int teach_lab_hours) {
		this.teach_lab_hours = teach_lab_hours;
	}
	public int getXp_per_task() {
		return xp_per_task;
	}
	public void setXp_per_task(int xp_per_task) {
		this.xp_per_task = xp_per_task;
	}
	public int getSurveillance_hours() {
		return surveillance_hours;
	}
	public void setSurveillance_hours(int surveillance_hours) {
		this.surveillance_hours = surveillance_hours;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "PhDStudent [teach_lab_hours=" + teach_lab_hours + ", xp_per_task=" + xp_per_task
				+ ", surveillance_hours=" + surveillance_hours + ", task=" + task + ", id=" + id + ", username="
				+ username + "]";
	}

}
