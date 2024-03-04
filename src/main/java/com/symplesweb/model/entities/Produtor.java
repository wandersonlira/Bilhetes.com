package com.symplesweb.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "tab_produtor")
public class Produtor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_produtor;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "produtor")
	private List<Evento> listEventos = new ArrayList<Evento>();
	
	
	public Produtor() {}


	public Produtor(Long id_produtor, String firstName, String lastName, String email, String password/*,
			List<Evento> listEventos*/) {
		super();
		this.id_produtor = id_produtor;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
//		this.listEventos = listEventos;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Evento> getListEventos() {
		return listEventos;
	}


	public void setListEventos(List<Evento> listEventos) {
		this.listEventos = listEventos;
	}


	public Long getId_produtor() {
		return id_produtor;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, id_produtor);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtor other = (Produtor) obj;
		return Objects.equals(email, other.email) && Objects.equals(id_produtor, other.id_produtor);
	}


	@Override
	public String toString() {
		return "Produtor [id_produtor=" + id_produtor + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + "]";
	}
	

	
}
