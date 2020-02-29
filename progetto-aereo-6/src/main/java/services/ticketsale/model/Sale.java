package services.ticketsale.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Link;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Sale {
	@Expose
	private String code;
	
	@Expose
	private int quantity;
	
	@Expose
	private double totPrice;
	
	@Expose
	private Date saleDate;
	
	@Expose
	private List<Ticket> tickets;
	
	private List<Link> links;
	
	public Sale(String jsonString) {
		super();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Sale sale = gson.fromJson(jsonString, Sale.class);
		this.code = sale.getCode();
		this.quantity = sale.getQuantity();
		this.totPrice = sale.getTotPrice();
		this.saleDate = sale.getSaleDate();
		this.tickets = sale.getTicket();
		this.links = null;
	}

	public Sale(String code, int quantity, Ticket ticket) {
		super();
		this.code = code;
		
		this.quantity = quantity;
		int baseQ = 1;
		if (baseQ >= quantity)
			this.quantity = baseQ;

		tickets = new ArrayList<>();
		for (int ticketsQ = 0; ticketsQ < this.quantity; ticketsQ++)
			this.tickets.add(new Ticket(ticket));

		this.totPrice = ticket.getPrice() * quantity;;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
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

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	// Mettere un ciclo per selezionare il singolo ticket al posto di
	// tickets.toString()
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
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
