package ergasia.katanemhmena.system.repositories;

import org.springframework.data.repository.CrudRepository;

import ergasia.katanemhmena.system.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}