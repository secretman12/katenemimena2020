package ergasia.katanemhmena.system.services;

import java.util.Collection;

import ergasia.katanemhmena.system.entities.PhDStudent;

public interface PhDStudentService {

	PhDStudent save(PhDStudent phdStudent);

	Boolean delete(int id);

	PhDStudent update(PhDStudent phdStudent);

	PhDStudent findById(int id);

	PhDStudent findByUserName(String Username);

	PhDStudent findByEmail(String email);

	Collection<PhDStudent> findAll();

	
}
