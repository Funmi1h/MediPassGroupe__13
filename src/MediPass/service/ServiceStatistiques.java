package MediPass.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MediPass.model.Consultation;

public class ServiceStatistiques {
    public Map<String, Integer> compterParStatut(List<Consultation> consultations) {
    Map<String, Integer> compteur = new HashMap<>();

    for (Consultation c : consultations) {
        String statut = c.getStatut().name(); // récupère le nom du statut
        compteur.put(statut, compteur.getOrDefault(statut, 0) + 1);
    }

    return compteur;
}
}
