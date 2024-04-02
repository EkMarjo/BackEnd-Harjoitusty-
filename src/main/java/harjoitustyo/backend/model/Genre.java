package harjoitustyo.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

//määrittelee Genre luokan ominaisuudet ja yhteyden Practice luokkaan
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreid;
    
    @NotEmpty
    @Size(min=1, max=100, message = "Kentän pituus pitää olla 1-100")
    private String laji;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private List<Practice> practice;



    public Genre (){
        super();
    }

    public Genre(String laji) {
        super();
        this.laji = laji;
    }

    public Long getGenreid() {
        return genreid;
    }

    public void setGenreid(Long genreid) {
        this.genreid = genreid;
    }

    public String getLaji() {
        return laji;
    }

    public void setLaji(String laji) {
        this.laji = laji;
    }

    public List<Practice> getPractice() {
        return practice;
    }

    public void setPractice(List<Practice> practice) {
        this.practice = practice;
    }

    @Override
    public String toString() {
        return "Genre [genreid=" + genreid + ", laji=" + laji + "]";
    }

    


}
