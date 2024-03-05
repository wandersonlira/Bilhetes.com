package com.symplesweb.controller.dto.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.symplesweb.model.entities.Evento;
import com.symplesweb.model.entities.Produtor;


public class ProdutorDtoView implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String firstName;
	private String lastName;
	private String email;
	private List<EventoOutputParticipante> listEventos = new ArrayList<EventoOutputParticipante>();
	
	
	public ProdutorDtoView() {}
	
	
	public ProdutorDtoView(Produtor projection) {
		this.firstName = projection.getFirstName();
		this.lastName = projection.getLastName();
		this.email = projection.getEmail();
		
		for (Evento evento : projection.getListEventos()) {
			EventoOutputParticipante novoEvento = new EventoOutputParticipante(evento);
			listEventos.add(novoEvento);
			System.err.println("paseeeei");
		}
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


	public List<EventoOutputParticipante> getListEventos() {
		return listEventos;
	}

	public void setListEventos(List<EventoOutputParticipante> listEventos) {
		this.listEventos = listEventos;
	}
	
	
	
	

}
