package com.uss.restaurante.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.restaurante.entity.Pago;
import com.uss.restaurante.exception.GeneralServiceException;
//import com.uss.facturacion.almacen.exception.NoDataServiceException;
import com.uss.restaurante.exception.ValidateServiceException;
import com.uss.restaurante.repository.PagoRepository;
import com.uss.restaurante.service.PagoService;
import com.uss.restaurante.validator.PagoValidator;

@Service
public class PagoServiceImpl implements PagoService {
	@Autowired
	private PagoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pago> findAll() {
		try {
			return repository.findAll();		
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Pago findById(int id) {
		try {
	        Pago pagoDb = repository.findById(id)
	                .orElseThrow(() -> new ValidateServiceException("No hay un registro con ese ID"));
	        
	        return pagoDb;
	        
	    } catch (ValidateServiceException e) {
	        throw e; 	                 
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error en el servidor");
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public Pago findByDocumentoInquilino(String documentoInquilino) {
		try {
			return repository.findByDocumentoInquilino(documentoInquilino);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pago> findByDocumentoInquilinoContaining(String documentoInquilino) {
		try {
			return repository.findByDocumentoInquilinoContaining(documentoInquilino);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public Pago create(Pago obj) {
		try {
			PagoValidator.save(obj);
			Pago pago=findByDocumentoInquilino(obj.getDocumentoInquilino());
			if(pago!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese documento");
			}
			//obj.setActivo(true);
			return repository.save(obj);			
		} catch (ValidateServiceException e) {
			throw new ValidateServiceException(e.getMessage());
		} catch (Exception e) {
			throw new GeneralServiceException("Error en el servidor");
		}	
	}

	@Override
	@Transactional
	public Pago update(Pago obj) {
		try {
			PagoValidator.save(obj);
			Pago pagoDb=findById(obj.getId());
			//Validamos si ya existe el registro con ese nombre
			Pago pago=findByDocumentoInquilino(obj.getDocumentoInquilino());
			if(pago!=null && obj.getId()!=pago.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese documento");
			}
			pagoDb.setDocumentoInquilino(obj.getDocumentoInquilino());			
			pagoDb.setFecha(obj.getFecha());			
			pagoDb.setMontoPago(obj.getMontoPago());			
			pagoDb.setMetodoPago(obj.getMetodoPago());			
			pagoDb.setDescripcion(obj.getDescripcion());			
			return repository.save(pagoDb);
			
		} catch (ValidateServiceException e) {
			throw new ValidateServiceException(e.getMessage());
		} catch (Exception e) {
			throw new GeneralServiceException("Error en el servidor");
		}
	}

	@Override
	@Transactional
	public int delete(int id) {
		try {
			Pago pagoDb= findById(id);
			if(pagoDb==null) {
				return 0;
			}else {
				repository.delete(pagoDb);
				return 1;
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
