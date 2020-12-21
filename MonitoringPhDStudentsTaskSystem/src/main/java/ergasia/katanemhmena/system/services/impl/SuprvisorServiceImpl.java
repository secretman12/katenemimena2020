package ergasia.katanemhmena.system.services.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ergasia.katanemhmena.system.entities.Supervisor;
import ergasia.katanemhmena.system.repositories.SupervisorRepository;
import ergasia.katanemhmena.system.services.SupervisorService;
@Service
@Transactional
public class SuprvisorServiceImpl implements SupervisorService {

	@Autowired
    private SupervisorRepository supervisorRepository;
    @Override
    public Supervisor save(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    public Boolean delete(int id) {
        if (supervisorRepository.existsById(id)) {
            supervisorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Supervisor update(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    public Supervisor findById(int id) {
        return supervisorRepository.findById(id).get();
    }
    @Override
    public Supervisor findByUserName(String Username) {
        return supervisorRepository.findByUsername(Username);
    }
    @Override
    public Supervisor findByEmail(String email) {
        return supervisorRepository.findByEmail(email);
    }

    @Override
    public Collection<Supervisor> findAll() {
        Iterable<Supervisor> itr = supervisorRepository.findAll();
        return (Collection<Supervisor>) itr;
    }

}
