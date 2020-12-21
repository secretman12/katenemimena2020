package ergasia.katanemhmena.system.repositories;

import org.springframework.data.repository.CrudRepository;

import ergasia.katanemhmena.system.entities.PhDStudent;

public interface PhDStudentRepository extends CrudRepository<PhDStudent, Integer> {

    PhDStudent findByUsername(String username);

    PhDStudent findByEmail(String email);
}
