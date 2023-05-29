package mds.framework.api.controller;

import mds.framework.entity.Person;
import mds.framework.service.IDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping()
public class UserController {

    @Resource(name= "dataService")
    private final IDataService dataService;

    public UserController(IDataService dataService){
        this.dataService = dataService;
    }

    @GetMapping(value = "/api/users")
    public ResponseEntity<List<Person>> getPeople(){
        return ResponseEntity.ok(dataService.getPeople());
    }

}
