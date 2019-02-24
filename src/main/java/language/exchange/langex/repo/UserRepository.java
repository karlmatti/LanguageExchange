package language.exchange.langex.repo;

import language.exchange.langex.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {}
