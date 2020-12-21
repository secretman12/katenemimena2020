package ergasia.katanemhmena.system.services;

import java.util.Collection;

import ergasia.katanemhmena.system.entities.Secretery;

public interface SecreteryService {

	Secretery save(Secretery secretery);

	Boolean delete(int id);

	Secretery update(Secretery secretery);

	Secretery findById(int id);

	Secretery findByUserName(String Username);

	Secretery findByEmail(String email);

	Collection<Secretery> findAll();
}
