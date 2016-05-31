package com.carloscardona.tns.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author candr
 *
 */
@Entity
public class Vuelo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String aerolinea;

	@Temporal(TemporalType.TIMESTAMP)
	private Date salida;

	@Temporal(TemporalType.TIMESTAMP)
	private Date llegada;

	@ManyToOne
	@JoinColumn(name = "origen")
	private Aeropuerto origen;

	@ManyToOne
	@JoinColumn(name = "destino")
	private Aeropuerto destino;

	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the aeropuerto
	 */
	public String getAeropuerto() {
		return aerolinea;
	}

	/**
	 * @param aeropuerto
	 *            the aeropuerto to set
	 */
	public void setAeropuerto(String aeropuerto) {
		this.aerolinea = aeropuerto;
	}

	/**
	 * @return the salida
	 */
	public Date getSalida() {
		return salida;
	}

	/**
	 * @param salida
	 *            the salida to set
	 */
	public void setSalida(Date salida) {
		this.salida = salida;
	}

	/**
	 * @return the llegada
	 */
	public Date getLlegada() {
		return llegada;
	}

	/**
	 * @param llegada
	 *            the llegada to set
	 */
	public void setLlegada(Date llegada) {
		this.llegada = llegada;
	}

	/**
	 * @return the origen
	 */
	public Aeropuerto getOrigen() {
		return origen;
	}

	/**
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(Aeropuerto origen) {
		this.origen = origen;
	}

	/**
	 * @return the destino
	 */
	public Aeropuerto getDestino() {
		return destino;
	}

	/**
	 * @param destino
	 *            the destino to set
	 */
	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vuelo [id=" + id + ", aerolinea=" + aerolinea + ", salida=" + salida + ", llegada=" + llegada + ", origen=" + origen
				+ ", destino=" + destino + "]";
	}

}