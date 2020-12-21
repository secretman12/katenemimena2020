package ergasia.katanemhmena.system.services;

import java.util.Collection;

import ergasia.katanemhmena.system.entities.Task;
import ergasia.katanemhmena.system.enums.Status;

public interface TaskService {

	Task save(Task task);

	Boolean delete(int id);

	Task update(Task task);

	Task findById(int id);

	Collection<Task> findAll();
	Collection<Task> findByStatus(Status status);
}
