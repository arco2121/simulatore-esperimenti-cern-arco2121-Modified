package Logic;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.*;
import netscape.javascript.JSObject;

import java.net.URL;

public class MapController {

    @FXML
    private WebView mapWebView;

    private WebEngine webEngine;
    private Bridge jsBridge;
    private boolean mapInitialized = false;

    @FXML
    public void initialize() {
        webEngine = mapWebView.getEngine();
        jsBridge = new Bridge(this);
        webEngine.setJavaScriptEnabled(true);
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                System.out.println("✅ Pagina HTML caricata");

                // Esponi il bridge Java a JavaScript
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaBridge", jsBridge);

                System.out.println("🔗 Bridge Java-JavaScript stabilito");
            } else if (newState == Worker.State.FAILED) {
                System.err.println("❌ Errore caricamento mappa");
            }
        });

        loadMapHTML();
    }

    private void loadMapHTML() {
        try {
            URL htmlUrl = getClass().getResource("/Graphic/map.html");
            if (htmlUrl != null) {
                webEngine.load(htmlUrl.toExternalForm());
                System.out.println("📄 Caricamento mappa da: " + htmlUrl);
            } else {
                System.err.println("❌ File map.html non trovato!");
            }
        } catch (Exception e) {
            System.err.println("❌ Errore caricamento HTML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onMapInitialized() {
        mapInitialized = true;
        System.out.println("🗺️ Mappa inizializzata e pronta all'uso");
    }

    public void handleLocationChange(String locationKey, String locationName) {
        System.out.println("📍 Java ricevuto cambio location: " + locationName);

        // Qui puoi aggiornare il modello dell'applicazione
        // o notificare altri componenti del cambio di location

        // Esempio: aggiorna un esperimento basato sulla location
        updateExperimentForLocation(locationKey);
    }

    public void changeLocationFromJava(String locationKey) {
        if (!mapInitialized) {
            System.err.println("⚠️ Mappa non ancora inizializzata");
            return;
        }

        try {
            webEngine.executeScript("setLocationFromJava('" + locationKey + "')");
            System.out.println("✅ Location cambiata da Java: " + locationKey);
        } catch (Exception e) {
            System.err.println("❌ Errore cambio location: " + e.getMessage());
        }
    }
    public void sendExperimentDataToMap(String experimentName, String status) {
        if (!mapInitialized) {
            return;
        }

        try {
            String script = String.format(
                    "updateExperimentInfo('%s', '%s')",
                    experimentName,
                    status
            );
            webEngine.executeScript(script);
        } catch (Exception e) {
            System.err.println("❌ Errore invio dati: " + e.getMessage());
        }
    }

    private void updateExperimentForLocation(String locationKey) {
        switch (locationKey) {
            case "lhc":
                System.out.println("🔬 Attivando esperimenti LHC...");
                // Logica per LHC
                break;
            case "cms":
                System.out.println("🔬 Attivando esperimento CMS...");
                // Logica per CMS
                break;
            case "atlas":
                System.out.println("🔬 Attivando esperimento ATLAS...");
                // Logica per ATLAS
                break;
            default:
                System.out.println("🔬 Location generica CERN");
        }
    }

    @FXML
    public void onShowMapClicked() {
        System.out.println("🗺️ Apertura mappa...");
    }
}