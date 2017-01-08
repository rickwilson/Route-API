package core.services;

import core.entities.CourierSlugName;
import core.entities.CourierSlugNameDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;

@Service
public class CourierNameService {

    private final CourierSlugNameDAO courierSlugNameDAO;
    private final HashMap<String, String> shippingCarrierCodeShippingCarrierNameMap;

    @Autowired
    public CourierNameService(CourierSlugNameDAO courierSlugNameDAO) {
        Assert.notNull(courierSlugNameDAO, "CourierSlugNameDAO must not be null!");
        this.courierSlugNameDAO = courierSlugNameDAO;

        shippingCarrierCodeShippingCarrierNameMap = new HashMap<>();
        for (CourierSlugName courierSlugName: courierSlugNameDAO.findAll()) {
            try {
                shippingCarrierCodeShippingCarrierNameMap.put(courierSlugName.getSlug(),courierSlugName.getName());
            } catch (Exception e) {
            }
        }
    }

    public HashMap<String, String> getShippingCarrierCodeShippingCarrierNameMap() {
        return shippingCarrierCodeShippingCarrierNameMap;
    }

    @Cacheable("slugNames")
    public String getCourierName(String slug) {
        CourierSlugName courierSlugName = courierSlugNameDAO.findBySlug(slug);
        if(courierSlugName !=null && courierSlugName.getName() != null && courierSlugName.getName().trim().length() > 0) {
            return courierSlugName.getName();
        }
        return "Unknown";
    }

    @Cacheable("isSlug")
    public boolean isSlug(String slug) {
        CourierSlugName courierSlugName = courierSlugNameDAO.findBySlug(slug);
        if(courierSlugName !=null && courierSlugName.getId() > 0) {
            return true;
        }
        return false;
    }

    @Cacheable("getCourierSlug")
    public String getCourierSlug(String name) {
        CourierSlugName courierSlugName = courierSlugNameDAO.findByName(name);
        if(courierSlugName !=null && courierSlugName.getId() > 0) {
            return courierSlugName.getSlug();
        }
        return null;
    }
}
