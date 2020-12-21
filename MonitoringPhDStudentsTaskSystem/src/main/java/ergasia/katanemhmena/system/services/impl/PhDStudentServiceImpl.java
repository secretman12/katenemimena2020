package ergasia.katanemhmena.system.services.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ergasia.katanemhmena.system.entities.PhDStudent;
import ergasia.katanemhmena.system.repositories.PhDStudentRepository;
import ergasia.katanemhmena.system.services.PhDStudentService;
@Service
@Transactional
public class PhDStudentServiceImpl implements PhDStudentService {
	@Autowired
    private PhDStudentRepository phdStudentRepository;
    @Override
    public PhDStudent save(PhDStudent phdStudent) {
        return phdStudentRepository.save(phdStudent);
    }

    @Override
    public Boolean delete(int id) {
        if (phdStudentRepository.existsById(id)) {
            phdStudentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PhDStudent update(PhDStudent phdStudent) {
        return phdStudentRepository.save(phdStudent);
    }

    @Override
    public PhDStudent findById(int id) {
        return phdStudentRepository.findById(id).get();
    }
    @Override
    public PhDStudent findByUserName(String Username) {
        return phdStudentRepository.findByUsername(Username);
    }
    @Override
    public PhDStudent findByEmail(String email) {
        return phdStudentRepository.findByEmail(email);
    }

    @Override
    public Collection<PhDStudent> findAll() {
        Iterable<PhDStudent> itr = phdStudentRepository.findAll();
        return (Collection<PhDStudent>) itr;
    }

	
}
