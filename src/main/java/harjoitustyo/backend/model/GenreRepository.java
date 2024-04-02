package harjoitustyo.backend.model;

import org.springframework.data.repository.CrudRepository;

//rajapinta, hyödyntää Genre luokkaa.Tarjoaa perusominaisuudet
public interface GenreRepository extends CrudRepository <Genre, Long>{
    
}
