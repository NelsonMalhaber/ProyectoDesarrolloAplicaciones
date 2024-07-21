package com.uss.restaurante.entity;
import java.util.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pagos")
@EntityListeners(AuditingEntityListener.class)
public class Pago {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name = "documento_inquilino",nullable = false, length = 15)
	private String documentoInquilino;	
	
	@Column(name = "fecha",nullable = false, length = 100)
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime fecha;
	
	@Column(name = "monto_pago",nullable = false, length = 100)
	private float montoPago;
	
	@Column(name = "metodo_pago",nullable = false, length = 100)
	private String metodoPago;
	
	@Column(name = "descripcion",nullable = false, length = 100)
	private String descripcion;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt2;
	@Column(name="updated_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
}
