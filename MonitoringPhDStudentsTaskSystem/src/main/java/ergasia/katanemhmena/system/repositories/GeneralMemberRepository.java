package ergasia.katanemhmena.system.repositories;

import org.springframework.data.repository.CrudRepository;

import ergasia.katanemhmena.system.entities.General_member;

public interface GeneralMemberRepository extends CrudRepository<General_member, Integer> {

	General_member findByUsername(String username);

	General_member findByEmail(String email);
}
