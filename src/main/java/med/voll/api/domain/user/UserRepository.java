package med.voll.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Alexandre
 */
public interface UserRepository extends JpaRepository<User, Long>{

    public UserDetails findByLogin(String login);
    
}
