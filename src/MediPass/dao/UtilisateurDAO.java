<<<<<<< HEAD
package MediPass.dao;

import java.util.List;
import java.util.Optional;

import MediPass.Utilisateur;

public interface UtilisateurDAO {

    // Crée et stocke un utilisateur
    Utilisateur create(Utilisateur utilisateur) throws DAOException;

    // Cherche un utilisateur par son id
    Optional<Utilisateur> findById(String id) throws DAOException;

    // Retourne tous les utilisateurs
    List<Utilisateur> findAll() throws DAOException;

    // Met à jour un utilisateur existant (retourne true si ok)
    boolean update(Utilisateur utilisateur) throws DAOException;

    // Supprime un utilisateur par id
    boolean delete(String id) throws DAOException;
=======
package MediPass;

public class UtilisateurDAO {

>>>>>>> 9445065752195d6a5bac4d85321be95f6ccb0e7e
}
