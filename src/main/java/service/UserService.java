package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

   void save(User user);

   List<User> getAllUsers();

   Optional<User> check(User user);

   void edit(User user);

   void edit(int id, User user);

   void deleteUser(int id);
}
