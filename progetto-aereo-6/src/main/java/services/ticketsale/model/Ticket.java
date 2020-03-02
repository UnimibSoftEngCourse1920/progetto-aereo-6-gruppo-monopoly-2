package services.ticketsale.model;

public class Ticket {
	private Flight flight;
	private int seat;
	
	public Ticket(Flight flight) {
		super();
		this.flight = flight;
	}
	
	public Ticket(Ticket ticket) {
		super();
		this.flight = ticket.getFlight();
		this.seat = ticket.getSeat();
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	@Override
	public String toString() {
		return "Ticket [flight=" + flight + ", seat=" + seat + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + seat;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ticket))
			return false;
		Ticket other = (Ticket) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (seat != other.seat)
			return false;
		return true;
	}

	
	/*
	public Ticket(String jsonString) {
		super();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Ticket ticket = gson.fromJson(jsonString, Ticket.class);
		this.code = ticket.getCode();
		this.departureAirport = ticket.getDepartureAirport();
		this.arrivalAirport = ticket.getArrivalAirport();
		this.departureTime = ticket.getDepartureTime();
		this.arrivalTime = ticket.getArrivalTime();
		this.price = ticket.getPrice();
		this.seat = ticket.getSeat();
	}*/
	
	/* Costruttore alternativo da stringa JSON
	public Ticket(String jsonString) {
		super();
		String[] attr = jsonToAttr(jsonString);
		this.code = attr[0];
		this.departureAirport = attr[1];
		this.arrivalAirport = attr[2];
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		try {
			this.departureTime = sdf.parse(attr[3]);
			this.arrivalTime = sdf.parse(attr[4]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.price = Double.parseDouble(attr[5]);
		this.seat = Integer.parseInt(attr[6]);
		this.links = null;
	}*/
	
	/* Supporto al costruttore
	public static String[] jsonToAttr(String jsonString) {
		String[] ret = new String[7];
		int i = 0;
		int j = 0;
		String sub = null;
		
		i = jsonString.indexOf("code")+7;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[0] = jsonString.substring(i, j);
		
		i = jsonString.indexOf("departureAirport")+19;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[1] = jsonString.substring(i, j);
		
		i = jsonString.indexOf("arrivalAirport")+17;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[2] = jsonString.substring(i, j);
		
		int x = 0;
		
		i = jsonString.indexOf("departureTime")+16;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[3] = jsonString.substring(i, j);
		x = ret[3].lastIndexOf(":");
		ret[3] = ret[3].substring(0, x).replaceAll("T", " ");
		
		i = jsonString.indexOf("arrivalTime")+14;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")+i;
		ret[4] = jsonString.substring(i, j);
		x = ret[4].lastIndexOf(":");
		ret[4] = ret[4].substring(0, x).replaceAll("T", " ");
		
		i = jsonString.indexOf("price")+7;
		sub = jsonString.substring(i);
		j = sub.indexOf("\"")-1+i;
		ret[5] = jsonString.substring(i, j);
		
		i = jsonString.indexOf("seat")+6;
		sub = jsonString.substring(i);
		j = sub.indexOf("}")+i;
		ret[6] = jsonString.substring(i, j);
		
		return ret;
	}
	*/
}
