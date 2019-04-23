package br.eti.krebscode.domain;

public enum UserRole {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private int cod;
	private String description;
	
	private UserRole(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static UserRole toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (UserRole x : UserRole.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
	
}
