package ergasia.katanemhmena.system.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import ergasia.katanemhmena.system.entities.Task;
import ergasia.katanemhmena.system.enums.Status;

public interface TaskRepository  extends CrudRepository<Task, Integer> {
	Collection<Task> findByStatus(Status status);
}
