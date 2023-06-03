package complete.crud.UserController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import complete.crud.IUserRepository.IUserRepository;
import complete.crud.User.User;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/User")
@RestController
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;

    @PostMapping("/add")
    public void savedata(@RequestBody User user) {
        iUserRepository.save(user);
    }

    @GetMapping("/get")
    public List<User> getData() {
        List<User> user = iUserRepository.findAll();
        return user;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable Integer id) {
        Optional<User> optionaluser = iUserRepository.findById(id);

        if (optionaluser.isPresent()) {
            User users = optionaluser.get();
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        iUserRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @DeleteMapping("/delete")
    public void deleteAll() {
        iUserRepository.deleteAll();
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<String> updateById(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> optionaluser = iUserRepository.findById(id);
        if (optionaluser.isPresent()) {
            User users = optionaluser.get();
            users.setName(user.getName());
            users.setF_name(user.getF_name());
            iUserRepository.save(user);
            return ResponseEntity.ok().body("Updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
