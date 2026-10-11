public class Bibliothecaire {

    private final String nom;
    private final Catalogue<Document> catalogue;

    public Bibliothecaire(String nom,Catalogue<Document> catalogue) {
        this.nom = nom;
        this.catalogue = catalogue;
    }

    public void accueillir() {
        System.out.println("Bonjour, " + nom);
    }
}