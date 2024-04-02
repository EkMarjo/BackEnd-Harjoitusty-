package harjoitustyo.backend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import harjoitustyo.backend.model.GenreRepository;
import harjoitustyo.backend.model.Practice;
import harjoitustyo.backend.model.PracticeRepository;


@Controller
public class MarjoController {
    private static final Logger log = LoggerFactory.getLogger(MarjoController.class);
	@Autowired
	private PracticeRepository repository;

    @Autowired
    private GenreRepository gRepository;

    // Etusivu, hae harjoitukset
	@RequestMapping(value= {"/", "/etusivu"})
    public String naytaEtusivu(Model model) {
        model.addAttribute("practices", repository.findAll());
        return "etusivu";
    }

    @RequestMapping(value= {"/harjoitukset"})
    public String naytaHarjoitukset(Model model) {
        model.addAttribute("practices", repository.findAll());
        return "/harjoitukset";
    }

    //Lisää uusi harjoitus
    @RequestMapping(value = "/lisaatreeni")
    public String lisaaHarjoitus(Model model) {
        model.addAttribute("practice", new Practice());
        model.addAttribute("genres", gRepository.findAll());
        return "/lisaatreeni";
    }

    //Tallenna uusi harjoitus. palaa etusivulle, jos tallennus onnistui. palaa samalla sivulle, jos tallennus ei onnistunut ja näytä virhe
    @RequestMapping(value= "/save", method=RequestMethod.POST)
    public String tallenna(@Valid @ModelAttribute("practice") Practice practice, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            log.info("Tapahtui virhe");
            model.addAttribute("practice", practice);
            model.addAttribute("genres", gRepository.findAll());
            return "/lisaatreeni";
            }
    repository.save(practice);
    return "redirect:/etusivu";
    }

    //poista valitttu harjoitus ja jää etusivulle. vain hallitsija saa tehdä tämän toiminnon
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String poistaHarjoitus(@PathVariable("id") Long id, Model model) {
        repository.deleteById(id);
        return "redirect:../etusivu";
    }

    //muokkaa valitun harjoituksen tietoja ja palaa etusivulle. vain hallitsija saa tehdä tämän toiminnon
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/muokkaa/{id}", method=RequestMethod.GET)
    public String muokkaaTreeni(@PathVariable("id") Long id, Model model) {
        model.addAttribute("muokkaa", repository.findById(id));
        model.addAttribute("genres", gRepository.findAll());
        return "/muokkaa";
    }

  

 
	

}
