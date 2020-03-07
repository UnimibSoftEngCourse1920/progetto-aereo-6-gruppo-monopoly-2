package services.customer.model;

public class CreditCard {
	private String holder;
	private String code;
	private String pin;
	private String expirationDate;
	
	public CreditCard(String holder, String code, String pin, String expirationDate) {
		super();
		this.holder = holder;
		this.code = code;
		this.pin = pin;
		this.expirationDate = expirationDate;
	}
	
	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "CreditCard [holder=" + holder + ", code=" + code + ", pin=" + pin + ", expirationDate=" + expirationDate
				+ "]";
	}
}
