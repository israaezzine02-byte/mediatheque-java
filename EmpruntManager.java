public class EmpruntManager {

    private final Catalogue<Document> catalogue;

    public EmpruntManager(Catalogue<Document> catalogue) {
        this.catalogue = catalogue;
    }

    public void emprunter(String titre)
            throws MediathequeException {

        Document d = catalogue.rechercherParTitre(titre)
            .orElseThrow(
                () -> new DocumentIntrouvableException(titre)
            );

        if (!(d instanceof Empruntable e)) {
            throw new IllegalArgumentException(
                "Document non empruntable : " + titre
            );
        }

        e.emprunter();
    }
}