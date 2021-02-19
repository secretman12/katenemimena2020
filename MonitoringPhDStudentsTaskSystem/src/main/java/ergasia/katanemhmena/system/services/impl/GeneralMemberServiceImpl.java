package ergasia.katanemhmena.system.services.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ergasia.katanemhmena.system.entities.General_member;
import ergasia.katanemhmena.system.repositories.GeneralMemberRepository;
import ergasia.katanemhmena.system.services.GeneralMemberService;
@Service
@Transactional
public class GeneralMemberServiceImpl implements GeneralMemberService {

	@Autowired
    private GeneralMemberRepository gmRepository;
    @Override
    public General_member save(General_member gm) {
        return gmRepository.save(gm);
    }

    @Override
    public Boolean delete(int id) {
        if (gmRepository.existsById(id)) {
            gmRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public General_member update(General_member gm) {
        return gmRepository.save(gm);
    }

    @Override
    public General_member findById(int id) {
        return gmRepository.findById(id).get();
    }
    @Override
    public General_member findByUserName(String Username) {
        return gmRepository.findByUsername(Username);
    }
    @Override
    public General_member findByEmail(String email) {
        return gmRepository.findByEmail(email);
    }

    @Override
    public Collection<General_member> findAll() {
        Iterable<General_member> itr = gmRepository.findAll();
        return (Collection<General_member>) itr;
    }

}
