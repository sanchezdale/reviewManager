package me.sanchezdale.reviewManager;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<String> homeResolver(){
        return new ResponseEntity<>("<h1>Hello World</h1>", HttpStatus.OK);
    }
}
