package ergasia.katanemhmena.system.services;

import java.util.Collection;

import ergasia.katanemhmena.system.entities.User;

public interface UserService {
	User save(User user);

    Boolean delete(int id);

    User update(User user);

    User findById(int id);

     User findByUserName(String Username);

     User findByEmail(String email);

    Collection<User> findAll();
}