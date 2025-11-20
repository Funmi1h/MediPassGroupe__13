package MediPass;

public class Main {

	public static void main(String[] args) {
		String mdpMedecinA = "123";
		char[] mdpA = mdpMedecinA.toCharArray();

		ProfessionnelSante medecinA  = new ProfessionnelSante("ASSOGBA", "Louis", mdpA, "Géneraliste");
	}

}
