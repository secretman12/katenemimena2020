package ergasia.katanemhmena.system.repositories;

import org.springframework.data.repository.CrudRepository;

import ergasia.katanemhmena.system.entities.Secretery;

public interface SecreteryRepository extends CrudRepository<Secretery, Integer> {

    Secretery findByUsername(String username);

    Secretery findByEmail(String email);
}
