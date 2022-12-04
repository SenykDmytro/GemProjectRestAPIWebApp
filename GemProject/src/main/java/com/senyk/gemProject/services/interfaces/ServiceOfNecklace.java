package com.senyk.gemProject.services.interfaces;

import java.util.List;

public interface ServiceOfNecklace<T,P> extends Service<T> {
    public Double getPrice(Long id);
    public Double getCarat(Long id);
    public List<P> getGems(Long id);
}
