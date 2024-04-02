package harjoitustyo.backend.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import harjoitustyo.backend.model.Practice;
import harjoitustyo.backend.model.PracticeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




// Restcontrollerin avulla voidaan avata json muodossa tietokantaan vietyjä tietoja (eli Practice luokan tietoja)
@RestController
public class RestMarjoController {

    @Autowired
    private PracticeRepository repository;

    @GetMapping("/practices")
    public Iterable <Practice> haeTreenit() {
        return repository.findAll();
    }

    @GetMapping("/practice/{id}")
    public Optional<Practice> haeTreeni(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    
    @PostMapping("/practice")
    Practice newPractice(@RequestBody Practice newPractice){
        return repository.save(newPractice);
    }

    @PutMapping("practice/{id}")
    Practice editPractice(@PathVariable Long id, @RequestBody Practice editPractice) {
        editPractice.setId(id);
        return repository.save(editPractice);
    }
    
    

}
