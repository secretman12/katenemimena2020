package ergasia.katanemhmena.system.services;

import java.util.Collection;

import ergasia.katanemhmena.system.entities.Supervisor;

public interface SupervisorService {
	Supervisor save(Supervisor supervisor);

	Boolean delete(int id);

	Supervisor update(Supervisor supervisor);

	Supervisor findById(int id);

	Supervisor findByUserName(String Username);

	Supervisor findByEmail(String email);

	Collection<Supervisor> findAll();

}
