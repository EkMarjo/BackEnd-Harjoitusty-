package harjoitustyo.backend.model;



import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.NotEmpty;

//Määrittää Practice luokan ominaisuudet ja yhteyden Genre luokkaan
@Entity
public class Practice {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Muistiinpanot kenttä ei saa olla tyhjä")
    private String muistiinpano;

    @Range(min = 10, message="Kesto pitää olla enemmän kuin 10")
    private int kesto;

    @Range(min = 30, message="Keskisykkeen pitää olla enemmän kuin 30")
    private int keskisyke;

    @Range(min = 01012020, message="Kentän pitää olla suurempi kuin 01012020")
    private int paivamaara;


    @ManyToOne
    @JoinColumn(name="genreid")
    private Genre genre;



    public Practice(){
        super();
    }

    public Practice(String muistiinpano, int kesto, int keskisyke, int paivamaara, Genre genre) {
        this.muistiinpano = muistiinpano;
        this.kesto = kesto;
        this.keskisyke = keskisyke;
        this.paivamaara = paivamaara;
        this.genre= genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMuistiinpano() {
        return muistiinpano;
    }

    public void setMuistiinpano(String muistiinpano) {
        this.muistiinpano = muistiinpano;
    }

    public int getKesto() {
        return kesto;
    }

    public void setKesto(int kesto) {
        this.kesto = kesto;
    }

    public int getKeskisyke() {
        return keskisyke;
    }

    public void setKeskisyke(int keskisyke) {
        this.keskisyke = keskisyke;
    }

    public int getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(int paivamaara) {
        this.paivamaara = paivamaara;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        if(this.genre != null)
        return "Practice [id=" + id + ", muistiinpano=" + muistiinpano + ", kesto=" + kesto + ", keskisyke=" + keskisyke
                + ", paivamaara=" + paivamaara + ", genre= " + this.getGenre() +"]";
                else
                return "Practice [id=" + id + ", muistiinpano=" + muistiinpano + ", kesto=" + kesto + ", keskisyke=" + keskisyke
                + ", paivamaara=" + paivamaara + "]";
    }



    

    



}
