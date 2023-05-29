package mds.framework.service;

import mds.framework.entity.*;
import javax.xml.transform.Templates;
import java.util.List;

public interface IDataService {

    <T> T getItems(Class<T> clazz, String dataFileName);

    List<Person> getPeople();

    List<Person> getPeople(int count);

    List<Person> getPeople(int count, Integer managerId);

    List<String> getProfessions();

    List<Person> getPeople(Integer managerId);

    List<Person> getManagers();

    Templates getTemplates();

    List<Card> getCards() ;

    List<Country> getCountries();

    List<UserPermissions> getUserPermissions();

    List<Report> getReports();
}
