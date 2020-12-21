package ergasia.katanemhmena.system.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import ergasia.katanemhmena.system.enums.Status;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private int teach_lab;
	@Column(nullable = false)
	private int grading;
	@Column(nullable = false)
	private int surveillance;
	@Column(nullable = false,length = 15, columnDefinition = "varchar(15) default 'Unregistered'")
	@Enumerated(value = EnumType.STRING)
	private Status status=Status.Unregistered;
	public Task( int teach_lab, int grading, int surveillance) {
		super();
		this.teach_lab = teach_lab;
		this.grading = grading;
		this.surveillance = surveillance;
	}

	public Task() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeach_lab() {
		return teach_lab;
	}

	public void setTeach_lab(int teach_lab) {
		this.teach_lab = teach_lab;
	}

	public int getGrading() {
		return grading;
	}

	public void setGrading(int grading) {
		this.grading = grading;
	}

	public int getSurveillance() {
		return surveillance;
	}

	public void setSurveillance(int surveillance) {
		this.surveillance = surveillance;
	}

	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", teach_lab=" + teach_lab + ", grading=" + grading + ", surveillance=" + surveillance
				+ ", status=" + status + "]";
	}

	
}
