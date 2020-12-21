package ergasia.katanemhmena.system.services.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ergasia.katanemhmena.system.entities.Task;
import ergasia.katanemhmena.system.enums.Status;
import ergasia.katanemhmena.system.repositories.TaskRepository;
import ergasia.katanemhmena.system.services.TaskService;
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Boolean delete(int id) {
		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Task update(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task findById(int id) {
		return taskRepository.findById(id).get();
	}


	@Override
	public Collection<Task> findAll() {
		Iterable<Task> itr = taskRepository.findAll();
		return (Collection<Task>) itr;
	}

	@Override
	public Collection<Task> findByStatus(Status status) {
		Iterable<Task> itr = taskRepository.findByStatus(status);
		return (Collection<Task>) itr;
	}
}
