package mds.framework.service;

import mds.framework.entity.CurrencyPairs;
import mds.framework.entity.Person;

import java.util.List;

public interface ICurrencyPairsService {

    List<CurrencyPairs> getCurrencyPairs();

    List<CurrencyPairs> getCurrencyPairs(int count);

}
