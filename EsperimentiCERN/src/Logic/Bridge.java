package Logic;

import netscape.javascript.JSObject;
import javafx.application.*;

public class Bridge {

    private MapController controller;

    public Bridge(MapController controller) {
        this.controller = controller;
    }

    /**
     * Chiamato da JavaScript quando la mappa è pronta
     */
    public void onMapReady() {
        Platform.runLater(() -> {
            System.out.println("✅ Mappa caricata e pronta");
            controller.onMapInitialized();
        });
    }

    /**
     * Chiamato da JavaScript quando cambia la location
     */
    public void onLocationChanged(String locationKey, String locationName) {
        Platform.runLater(() -> {
            System.out.println("📍 Location cambiata: " + locationName);
            controller.handleLocationChange(locationKey, locationName);
        });
    }

    /**
     * Metodo per ricevere eventi generici dalla mappa
     */
    public void logMessage(String message) {
        Platform.runLater(() -> {
            System.out.println("🗺️ [Map]: " + message);
        });
    }
}