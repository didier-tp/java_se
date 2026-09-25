package tp.service;


import tp.dao.DaoDevise;
import tp.entity.Devise;
import tp.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceDeviseImpl implements ServiceDevise {

	
	private DaoDevise daoDevise;
	
	/*
	public ServiceDeviseImpl() {
		logger.trace("ServiceDeviseImpl instance="+this.toString());
	}
	*/
   

	//injection de dépendance par constructeur
	public ServiceDeviseImpl(DaoDevise daoDevise) {
		this.daoDevise = daoDevise;
		log.trace("ServiceDeviseImpl instance="+this.toString()
	    + " using repdaoDeviseositoryDevise="+daoDevise.getClass().getName());
	}

	@Override
	public double convertir(double montant, String codeDeviseSource, String codeDeviseCible) throws NotFoundException {
		//logger.trace("ServiceDeviseImpl instance="+this.toString() 
	    // + " using repdaoDeviseositoryDevise="+daoDevise.getClass().getName());
		try {
			Devise deviseSource = daoDevise.findByCode(codeDeviseSource);
			Devise deviseCible = daoDevise.findByCode(codeDeviseCible);
			return  montant * deviseCible.getExchangeRate() / deviseSource.getExchangeRate();
		} catch (NotFoundException e) {
			throw new NotFoundException("devise_not_found: " + e.getMessage(),e);
		}
		catch (Exception e) {
			throw new NotFoundException("devise_not_found",e);//ameliorable en précision
		}
	}

}
