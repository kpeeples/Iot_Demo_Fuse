package com.redhat.demo.iot.datacenter.monitor;

import java.util.logging.Logger;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class BRMSServer {
  
    private KieServices 	kieServices;
    private KieContainer 	kieContainer;
    private KieSession 		kieSession;
    
    public BRMSServer() {
    	initKieSession();
    }
    
    private void initKieSession() {
    	KieServices kieServices = KieServices.Factory.get();
	    KieContainer kieContainer = this.kieServices.getKieClasspathContainer();
    	KieSession kieSession = kieContainer.newKieSession("ksession-rules");
    }
    
    public DataSet insert( DataSet event ) {
    	kieSession.insert(event);
        kieSession.fireAllRules();
        
		return event;
	}

}
