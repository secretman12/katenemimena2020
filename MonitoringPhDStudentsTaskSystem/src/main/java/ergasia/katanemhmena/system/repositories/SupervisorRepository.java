package ergasia.katanemhmena.system.repositories;

import org.springframework.data.repository.CrudRepository;

import ergasia.katanemhmena.system.entities.Supervisor;

public interface SupervisorRepository extends CrudRepository<Supervisor, Integer> {

	Supervisor findByUsername(String username);

	Supervisor findByEmail(String email);

}
