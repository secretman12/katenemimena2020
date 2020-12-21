package ergasia.katanemhmena.system.services;

import java.util.Collection;

import ergasia.katanemhmena.system.entities.General_member;

public interface GeneralMemberService {

	General_member save(General_member General_member);

    Boolean delete(int id);

    General_member update(General_member General_member);

    General_member findById(int id);

     General_member findByUserName(String username);

     General_member findByEmail(String email);

    Collection<General_member> findAll();
}
