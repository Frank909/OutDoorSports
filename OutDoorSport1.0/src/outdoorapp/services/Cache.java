package outdoorapp.services;

import java.util.ArrayList;
import java.util.List;

class Cache {
	private List<Service> services;

	public Cache(){
		services = new ArrayList<Service>();
	}

	public Service getService(ServiceType serviceType){

		for (Service service : services) {
			if(service.getType().equals(serviceType)){
				return service;
			}
		}
		return null;
	}

	public void addService(Service newService){
		boolean exists = false;

		for (Service service : services) {
			if(service.getType().equals(newService.getType())){
				exists = true;
			}
		}
		
		if(!exists){
			services.add(newService);
		}
	}
}
