package rendu2;

import rendu2.DAOs.SalleDAO;
import rendu2.Models.Salle;

public class Main {
    public static void main(String[] args) {
    //    Utilisateur user=new Utilisateur();
    //    user.setEmail("ayoub@gmail");
    //    user.setNom("ayoub");
    //    user.setId_user(3);
    //    user.setPrenom("ayoub");
    //    user.setType("bouaoud");
    //    UtilisateurDAO  dao=new UtilisateurDAO();
    //    dao.add(user);
Salle salle=new Salle();
salle.setNom_salle("cafe");
salle.setCapacite(10);
    
    SalleDAO salledDao=new SalleDAO();
    salledDao.add(salle);

    }
}