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
class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(name = "usuario")
	@ManyToOne
	private Usuario cliente;

	@JoinColumn(name = "vuelo")
	@ManyToOne
	private Vuelo vuelo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the cliente
	 */
	public Usuario getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the vuelo
	 */
	public Vuelo getVuelo() {
		return vuelo;
	}

	/**
	 * @param vuelo
	 *            the vuelo to set
	 */
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Reserva)) {
			return false;
		}
		Reserva other = (Reserva) object;
		return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
	}

	@Override
	public String toString() {
		return "" + this.id;
	}
}
