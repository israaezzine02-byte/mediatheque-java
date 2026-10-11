import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediathequeTest {

    // 1. Emprunt nominal : on emprunte, puis on rend
    @Test
    void empruntNominal() throws DocumentIndisponibleException {
        Livre l = new Livre("Clean Code", 2008, "R. Martin", 464);

        l.emprunter();
        assertFalse(l.estDisponible());

        l.rendre();
        assertTrue(l.estDisponible());
    }

    // 2. Double emprunt : la 2e tentative doit lever l'exception, avec le titre
    @Test
    void doubleEmpruntDvd() throws DocumentIndisponibleException {
        Dvd d = new Dvd("Inception", 2010, 148);
        d.emprunter();

        DocumentIndisponibleException ex =
                assertThrows(DocumentIndisponibleException.class, d::emprunter);
        assertTrue(ex.getMessage().contains("Inception"));
    }

    // 3. EmpruntManager : document inconnu
    @Test
    void empruntDocumentInconnu() {
        EmpruntManager m = new EmpruntManager(new Catalogue<>());

        MediathequeException ex = assertThrows(
                DocumentIntrouvableException.class,
                () -> m.emprunter("Fantome"));
        assertTrue(ex.getMessage().contains("Fantome"));
    }

    // 4. EmpruntManager : double emprunt via le manager
    @Test
    void doubleEmpruntViaManager() throws MediathequeException {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
        EmpruntManager m = new EmpruntManager(c);

        m.emprunter("Clean Code");
        assertThrows(DocumentIndisponibleException.class,
                () -> m.emprunter("Clean Code"));
    }
}