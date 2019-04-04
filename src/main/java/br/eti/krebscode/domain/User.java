package br.eti.krebscode.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private long salary;
    @Column
    private int age;
    
    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="ROLES")
	private Set<Integer> roles = new HashSet<>();
    
    public User() {
		addUserRole(UserRole.CLIENT);
	}

	public User(long id, String username, String password, long salary, int age, UserRole userRole) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.salary = salary;
		this.age = age;
		addUserRole(userRole);
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void addUserRole(UserRole role) {
		roles.add(role.getCod());
	}
	
	public Set<UserRole> getRoles() {
		return roles.stream().map(x -> UserRole.toEnum(x)).collect(Collectors.toSet());
	}
}
