package services.ticketsale.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import services.customer.model.Customer;

public class Sale {
	private String code;
	private int quantity;
	private double totPrice;
	private Date saleDate;
	private Customer customer;
	private List<Ticket> tickets;

	public Sale(String code, int quantity, Ticket ticket, Customer customer) {
		super();
		this.code = code;
		this.quantity = quantity;
		
		int baseQ = 1;
		if (baseQ >= quantity)
			this.quantity = baseQ;
		
		this.totPrice = ticket.getFlight().getPrice() * quantity;
		
		this.setCustomer(customer);
		
		this.tickets = new ArrayList<>();
		for (int ticketsQ = 0; ticketsQ < this.quantity; ticketsQ++) {
			Ticket nTicket = new Ticket(ticket);
			nTicket.setSeat(ticket.getFlight().findSeat());
			this.tickets.add(new Ticket(nTicket));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			this.saleDate = sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(double totPrice) {
		this.totPrice = totPrice;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Ticket> getTicket() {
		return tickets;
	}

	public void setTicket(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket getTicket(int i) {
		return tickets.get(i);
	}

	public void setTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
	public void changeSale(Ticket ticket, int quantity) throws Exception{
		
		int baseQ = 1;
		if (baseQ >= quantity)
			quantity = baseQ;
		
		double newTotPrice = ticket.getFlight().getPrice() * quantity;
		System.out.println(newTotPrice);
		if(this.totPrice < newTotPrice)
			throw new Exception();
		
		for (int i = 0; i < this.quantity; i++) {
			int seat = this.getTicket(i).getSeat();
			this.getTicket(i).getFlight().setSeats(seat, false);
		}
		
		this.quantity = quantity;
		
		this.totPrice = newTotPrice;
		
		this.tickets = new ArrayList<>();
		for (int i = 0; i < this.quantity; i++) {
			Ticket nTicket = new Ticket(ticket);
			nTicket.setSeat(ticket.getFlight().findSeat());
			this.tickets.add(new Ticket(nTicket));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			this.saleDate = sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		String ticket = "";
		for(int i = 0; i < quantity; i++)
			ticket = ticket + tickets.get(i).toString();
		return "Sale [code=" + code + ", quantity=" + quantity + ", totPrice=" + totPrice + ", saleDate=" + saleDate
				+ ", ticket=" + ticket + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Sale))
			return false;
		Sale other = (Sale) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public static String ticketFromJson(String jsonString) {
		String[] sub = jsonString.split("ticket\":");
		int finalIndex = sub[1].lastIndexOf("]")+1;
		return sub[1].substring(0, finalIndex);
	}
	
	/*
	public Sale(String jsonString) {
		super();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Sale sale = gson.fromJson(jsonString, Sale.class);
		this.code = sale.getCode();
		this.quantity = sale.getQuantity();
		this.totPrice = sale.getTotPrice();
		this.saleDate = sale.getSaleDate();
		String ticketString = ticketFromJson(jsonString);
		/*
		gson = new Gson();
		List<Ticket> tickets = gson.fromJson(ticketString, new TypeToken<ArrayList<Ticket>>(){}.getType());
		sale.setTicket(tickets);
		//System.out.println(sale.getTicket(0));
		//System.out.println(sale.getTicket(1));
		 
	} 
*/
	
	/*{"code":"AAA",
	 * "links":
	 * [
	 *   {"params": {"rel":"self","title":"sale","type":"POST"},
	 *   "rel":"self",
	 *   "rels":["self"],
	 *   "title":"sale",
	 *   "type":"POST",
	 *   "uri":"http://localhost:8080/progetto-aereo-6/ticketsale/tickets/C10001/sale",
	 *   "uriBuilder":{"absolute":true}},
	 *   
	 *   {"params":{"rel":"next","title":"sale","type":"PUT"},
	 *   "rel":"next",
	 *   "rels":["next"],
	 *   "title":"sale",
	 *   "type":"PUT",
	 *   "uri":"http://localhost:8080/progetto-aereo-6/ticketsale/tickets/C10001/sale",
	 *   "uriBuilder":{"absolute":true}}
	 * ],
	 * "quantity":2,
	 * "saleDate":"2020-02-28T03:11:00Z[UTC]",
	 * "ticket":
	 * [
	 *   {"arrivalAirport":"Milano",
	 *   "arrivalTime":"0016-04-09T14:10:00Z[UTC]",
	 *   "code":"C10001",
	 *   "departureAirport":"Roma",
	 *   "departureTime":"0016-04-09T13:10:00Z[UTC]",
	 *   "price":20.0,
	 *   "seat":0},
	 *   
	 *   {"arrivalAirport":"Milano",
	 *   "arrivalTime":"0016-04-09T14:10:00Z[UTC]",
	 *   "code":"C10001",
	 *   "departureAirport":"Roma",
	 *   "departureTime":"0016-04-09T13:10:00Z[UTC]",
	 *   "price":20.0,
	 *   "seat":0}
	 *   ],
	 *    "totPrice":40.0}*/

}
