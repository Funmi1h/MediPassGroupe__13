package MediPass.dao;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.Optional;

import MediPass.Utilisateur;

// Implémentation simple en mémoire (pour débutant)

public class UtilisateurDAOEnMemoire implements UtilisateurDAO {

    private final Map<String, Utilisateur> store = new HashMap<>();

    @Override
    public Utilisateur create(Utilisateur utilisateur) throws DAOException {
        if (utilisateur == null) {
            throw new DAOException("Utilisateur nul");
        }
        store.put(utilisateur.getIdUtilisateur(), utilisateur);
        return utilisateur;
    }

    @Override
    public Optional<Utilisateur> findById(String id) throws DAOException {
        if (id == null) {
            throw new DAOException("id nul");
        }
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Utilisateur> findAll() throws DAOException {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean update(Utilisateur utilisateur) throws DAOException {
        if (utilisateur == null) {
            throw new DAOException("Utilisateur nul");
        }
        String id = utilisateur.getIdUtilisateur();
        if (!store.containsKey(id)) {
            return false;
        }
        store.put(id, utilisateur);
        return true;
    }

    @Override
    public boolean delete(String id) throws DAOException {
        if (id == null) {
            throw new DAOException("id nul");
        }
        return store.remove(id) != null;
    }
}