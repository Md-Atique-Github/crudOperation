package complete.crud.IUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import complete.crud.User.User;

public interface IUserRepository extends JpaRepository <User,Integer> {
    
}
