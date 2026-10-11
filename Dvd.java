public class Dvd extends Document implements Empruntable {
    private final int dureeMin;
    private boolean disponible;

    public Dvd(String titre, int dureeMin, int annee) {
        super(titre, annee);
        this.dureeMin = dureeMin;
        this.disponible = true;
    }

    @Override
    public void emprunter() throws DocumentIndisponibleException {
        if (!disponible) {
            throw new DocumentIndisponibleException(getTitre());
        }
        disponible = false;
    }
    @Override
    public String descriptionCourte() {
        return "[DVD] " + titre + " (" + annee + "), " + dureeMin + " min";
    }

    @Override
    public void rendre() {
        disponible = true;
    }

    @Override
    public boolean estDisponible() {
        return disponible;
    }
    
}
