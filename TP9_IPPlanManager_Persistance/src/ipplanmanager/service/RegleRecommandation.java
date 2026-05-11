package ipplanmanager.service;

import ipplanmanager.model.Recommandation;
import ipplanmanager.model.VLAN;

public interface RegleRecommandation {
    Recommandation analyser(VLAN vlan);
}  
