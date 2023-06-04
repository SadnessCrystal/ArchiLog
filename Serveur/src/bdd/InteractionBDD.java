package bdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abonne.Abonne;
import abonne.IAbonne;
import document.Document;
import dvd.DVD;
import dvd.IDVD;
import dvd.VerificationAge;

public class InteractionBDD implements IInteractionBDD {

	@Override
	public IAbonne getAbonne(Connection c, int numAbonne) throws SQLException {
		String sql = "SELECT * from abonne WHERE id = " + numAbonne + ";";
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.next();
		return traitementAbonne(resultSet);
	}

	@Override
	public Map<Integer, IAbonne> getAbonnes(Connection c) throws SQLException {
		String sql = "SELECT * from abonne;";
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		Map<Integer, IAbonne> abonnes = new HashMap<>();

		while (resultSet.next()) {
			IAbonne a = traitementAbonne(resultSet);
			abonnes.put(a.getId(), a);
		}

		return abonnes;
	}

	@Override
	public Document getDocument(Connection c, int numDocument) throws SQLException {
		String sql = "SELECT * from document WHERE id = " + numDocument + ";";
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.next();
		return traitementDocument(c, resultSet);
	}

	@Override
	public Map<Integer, Document> getDocuments(Connection c) throws SQLException {
		String sql = "SELECT * from document;";
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		Map<Integer, Document> documents = new HashMap<>();

		while (resultSet.next()) {
			Document d = traitementDocument(c, resultSet);
			documents.put(d.numero(), d);
		}

		return documents;
	}

	@Override
	public boolean commit(Connection c, IAbonne ab, Document d) throws SQLException {
		return false;
	}
	
	@Override
	public void emprunter(Connection c, IAbonne ab, Document d) throws SQLException {
		String sql = "UPDATE emprunter SET date = " + LocalDate.now().toString() + " WHERE id = " + d.numero() +";" +
				"UPDATE emprunter SET id_1 = " + ab.getId() + "WHERE id = " + d.numero() + ";";
		Statement statement = c.createStatement();
		statement.executeUpdate(sql);
	}
	
	@Override
	public void reserver(Connection c, IAbonne ab, Document d) throws SQLException {
		String sql = "UPDATE reserveur SET heureReservation = " + LocalDateTime.now().toString() + " WHERE id = " + d.numero() +";" +
		"UPDATE reserveur SET id_1 = " + ab.getId() + "WHERE id = " + d.numero() + ";";
		Statement statement = c.createStatement();
		statement.executeUpdate(sql);
	}
	
	@Override
	public void rendre(Connection c, Document d) throws SQLException{
		String sql = "UPDATE reserveur SET heureReservation = null WHERE id = " + d.numero() +";" +
		"UPDATE reserveur SET id_1 = null WHERE id = " + d.numero() + ";" +
		"UPDATE emprunteur SET heureReservation = null WHERE id = " + d.numero() +";" +
		"UPDATE emprunteur SET id_1 = null WHERE id = " + d.numero() + ";";
		Statement statement = c.createStatement();
		statement.executeUpdate(sql);
	}

	@Override
	public String getCatalogue(Connection c) throws SQLException {
		String sql = "SELECT * from document;";
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		StringBuilder sb = new StringBuilder();

		while (resultSet.next()) {
			String titre = resultSet.getString("titre");
			int numero = resultSet.getInt("id");
			
			sb.append("- " + titre + "(n°" + numero + ")" + System.lineSeparator());
		}
		return sb.toString();
	}

	private IAbonne traitementAbonne(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String nom = resultSet.getString("nom");
		String prenom = resultSet.getString("prenom");

		LocalDate dateNaissance = conversionDateToLocalDate(resultSet.getDate("dateNaissance"));
		LocalDate dateBanissement = conversionDateToLocalDate(resultSet.getDate("dateBanissement"));

		return new Abonne(id, nom, prenom, dateNaissance, dateBanissement);
	}

	private Document traitementDocument(Connection c, ResultSet resultSet) throws SQLException {
		boolean estEmprunte = false;

		int idDVD = resultSet.getInt("id");
		String titre = resultSet.getString("Titre");
		LocalDate dateEmprunt = null;
		LocalDateTime dateReservation = null;
		IAbonne abonne = null;

		String sql = "SELECT * from emprunteur WHERE id = " + idDVD + ";";
		Statement statement = c.createStatement();
		ResultSet resultSetEmprunteur = statement.executeQuery(sql);

		while (resultSetEmprunteur.next()) {
			dateEmprunt = conversionDateToLocalDate(resultSetEmprunteur.getDate("dateEmprunt"));
			abonne = getAbonne(c, resultSetEmprunteur.getInt("id_1"));
			estEmprunte = true;
		}

		if (!estEmprunte) {
			sql = "SELECT * from reserveur WHERE id = " + idDVD + ";";
			statement = c.createStatement();
			ResultSet resultSetReserveur = statement.executeQuery(sql);

			while (resultSetReserveur.next()) {
				dateReservation = conversionTimestampToLocalDateTime(resultSetReserveur.getTimestamp("heureReservation"));
				abonne = getAbonne(c, resultSetReserveur.getInt("id_1"));
			}
		}

		return traitementDVD(c, idDVD, titre, dateReservation, dateEmprunt, abonne);
	}

	private IDVD traitementDVD(Connection c, int id, String titre, LocalDateTime dateReservation, LocalDate dateEmprunt,
			IAbonne abonne) throws SQLException {

		String sql = "SELECT adulte from dvd WHERE id_dvd = " + id + ";";
		Statement statement = c.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.next();
		boolean estAdulte = resultSet.getBoolean("adulte");

		return new DVD(id, titre, dateReservation, dateEmprunt, abonne, estAdulte, new VerificationAge());
	}

	// Gros problème avec la conversion automatique, transition String subie
	private LocalDate conversionDateToLocalDate(Date d) {
		if (d == null) {
			return null;
		}

		String s = d.toString();
		String[] parts = s.split("-");

		int nAns = Integer.parseInt(parts[0]);
		int nMois = Integer.parseInt(parts[1]);
		int nJour = Integer.parseInt(parts[2]);

		return LocalDate.of(nAns, nMois, nJour);
	}

	private LocalDateTime conversionTimestampToLocalDateTime(Timestamp d) {
		if (d == null) {
			return null;
		}

		String s = d.toString();

		// Séparer la date et l'heure en utilisant l'espace comme délimiteur
		String[] parts = s.split(" ");

		// Extraire la partie de la date
		String datePart = parts[0];

		// Extraire la partie de l'heure
		String timePart = parts[1];

		// Séparer la partie de la date en utilisant le "-" comme délimiteur
		String[] dateParts = datePart.split("-");

		// Extraire le jour, le mois et l'année
		int annee = Integer.parseInt(dateParts[0]);
		int mois = Integer.parseInt(dateParts[1]);
		int jour = Integer.parseInt(dateParts[2]);

		// Séparer la partie de l'heure en utilisant le ":" comme délimiteur
		String[] timeParts = timePart.split(":");

		// Extraire l'heure, les minutes et les secondes
		int heure = Integer.parseInt(timeParts[0]);
		int minutes = Integer.parseInt(timeParts[1]);
		int secondes = (int) Float.parseFloat(timeParts[2]);

		return LocalDateTime.of(annee, mois, jour, heure, minutes, secondes);
	}

}
