package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro :");
		String carModel = sc.nextLine();
		
		System.out.println("Data de início da locação :");
		Date start = sdf.parse(sc.nextLine());
		
		System.out.println("Data de fim da locação :");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental car = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Digite o preço por hora :");
		Double pricePerHour = sc.nextDouble();
		
		System.out.println("Digite o preço por dia :");
		Double pricePerDay = sc.nextDouble();
		
		RentalService rs = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rs.processInvoice(car);
		
		System.out.println("INVOICE:");
		System.out.println("Pagamento básico : " + String.format("%.2f", car.getInvoice().getBasicPayment()));
		System.out.println("Impostos : " + String.format("%.2f", car.getInvoice().getTax()));
		System.out.println("Pagamento total : " + String.format("%.2f", car.getInvoice().getTotalPayment()));
		
		sc.close();
	}

}
