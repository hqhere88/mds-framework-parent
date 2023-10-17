package mds.framework.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import mds.framework.entity.CurrencyPairs;
import mds.framework.entity.Person;
import mds.framework.service.ICurrencyPairsService;
import mds.framework.service.IDataService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service("currencyPairsService")
public class CurrencyPairsServiceImpl implements ICurrencyPairsService {

    @Resource(name="dataService")
    private IDataService dataService;

    @Override
    public List<CurrencyPairs> getCurrencyPairs() {
        List<CurrencyPairs> currencyPairs = Arrays
                .asList(Objects.requireNonNull(dataService.getItems(CurrencyPairs[].class, "currencyPairs.json")));

        return currencyPairs;
    }

    @Override
    public List<CurrencyPairs> getCurrencyPairs(int count) {
        return getCurrencyPairs().subList(0, count);
    }
}
