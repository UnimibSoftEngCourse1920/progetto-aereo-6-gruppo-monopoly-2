package services.ticketsale.repository;

import java.util.HashMap;
import java.util.Map;

import services.ticketsale.model.Sale;

public class SaleRepository {
	private Map<String, Sale> sales;
	private static SaleRepository instance = null;

	public static SaleRepository getInstance() {
		if (instance == null) {
			instance = new SaleRepository();
		}
		return instance;
	}

	private SaleRepository() {
		this.sales = new HashMap<>();
	}

	public Sale find(String code) {
		return this.sales.get(code);
	}

	public void save(Sale sale) {
		this.sales.put(sale.getCode(), sale);
	}

	public Sale update(String code, Sale sale) {
		return this.sales.replace(code, sale);
	}
	
	public Sale delete(String code) {
		return this.sales.remove(code);
	}

}
