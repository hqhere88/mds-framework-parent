import mds.framework.entity.User;
import mds.framework.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping()
public class UserController {

    @Resource
    private IUserService userService;

    public UserController(){

    }

    // get users by id
    @GetMapping(value = "/app/v1/users/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(userService.findById(id));
    }

}
