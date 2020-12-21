package ergasia.katanemhmena.system.services.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ergasia.katanemhmena.system.entities.Secretery;
import ergasia.katanemhmena.system.repositories.SecreteryRepository;
import ergasia.katanemhmena.system.services.SecreteryService;
@Service
@Transactional
public class SecreteryServiceImpl implements SecreteryService {

	@Autowired
	private SecreteryRepository secreteryRepository;

	@Override
	public Secretery save(Secretery secretery) {
		return secreteryRepository.save(secretery);
	}

	@Override
	public Boolean delete(int id) {
		if (secreteryRepository.existsById(id)) {
			secreteryRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Secretery update(Secretery secretery) {
		return secreteryRepository.save(secretery);
	}

	@Override
	public Secretery findById(int id) {
		return secreteryRepository.findById(id).get();
	}

	@Override
	public Secretery findByUserName(String Username) {
		return secreteryRepository.findByUsername(Username);
	}

	@Override
	public Secretery findByEmail(String email) {
		return secreteryRepository.findByEmail(email);
	}

	@Override
	public Collection<Secretery> findAll() {
		Iterable<Secretery> itr = secreteryRepository.findAll();
		return (Collection<Secretery>) itr;
	}
}
