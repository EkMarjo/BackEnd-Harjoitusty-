package harjoitustyo.backend.model;

import org.springframework.data.repository.CrudRepository;

//rajapinta, hyödyntää AppUser luokkaa.Tarjoaa perusominaisuudet
public interface AppUserRepository extends CrudRepository <AppUser,Long> {
    AppUser findByUsername(String username);
}
