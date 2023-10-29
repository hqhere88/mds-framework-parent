package mds.framework.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import mds.framework.entity.*;
import mds.framework.service.IDataService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.transform.Templates;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service("dataService")
public class DataServiceImpl implements IDataService {

    @Override
    public <T> T getItems(Class<T> clazz, String dataFileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream stream = new ClassPathResource("data/" + dataFileName)
                    .getInputStream();
            return mapper.readValue(stream, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> getPeople() {
        List<Person> people = Arrays
                .asList(Objects.requireNonNull(getItems(Person[].class, "people.json")));

        List<String> peopleImages = Arrays
                .asList(Objects.requireNonNull(getItems(String[].class, "peopleImages.json")));
        for (int index = 0; index < people.size(); index++) {
            String pictureUrl = peopleImages.get(index % peopleImages.size());
            people.get(index).setPictureUrl(pictureUrl);
        }
        return people;
    }

    @Override
    public List<Person> getPeopleById(Integer id) {
        List<Person> people = new ArrayList<>(getPeople());
        people.removeIf(person -> person.getId() == null
                || !person.getId().equals(id));
        return people;
    }

    @Override
    public List<Person> getPeople(int count) {
        return getPeople().subList(0, count);
    }

    @Override
    public List<Person> getPeople(int count, Integer managerId) {
        List<Person> people = getPeople(managerId);
        return people.subList(0, count);
    }

    @Override
    public List<String> getProfessions() {
        Person[] people = Objects.requireNonNull(getItems(Person[].class, "people.json"));
        ArrayList<String> professions = new ArrayList<>();
        for (Person person : people) {
            String profession = person.getProfession();
            if (!professions.contains(profession)) {
                professions.add(profession);
            }
        }
        return professions;
    }

    @Override
    public List<Person> getPeople(Integer managerId) {
        List<Person> people = new ArrayList<>(getPeople());
        people.removeIf(person -> person.getManagerId() == null
                || !person.getManagerId().equals(managerId));
        return people;
    }

    @Override
    public List<Person> getManagers() {
        List<Person> people = new ArrayList<>(getPeople());
        people.removeIf(person -> !person.isManager());
        return people;
    }

    @Override
    public Templates getTemplates() {
        return getItems(Templates.class, "templates.json");
    }

    @Override
    public List<Card> getCards() {
        return Arrays.asList(Objects.requireNonNull(getItems(Card[].class, "cards.json")));
    }

    @Override
    public List<Country> getCountries() {
        return Arrays.asList(Objects.requireNonNull(getItems(Country[].class, "countries.json")));
    }

    @Override
    public List<CurrencyPairs> getCurrencyPairs() {
        return Arrays.asList(Objects.requireNonNull(getItems(CurrencyPairs[].class, "CurrencyPairs.json")));
    }

    @Override
    public List<UserPermissions> getUserPermissions() {
        return Arrays
                .asList(Objects.requireNonNull(getItems(UserPermissions[].class, "permissions.json")));
    }

    @Override
    public List<Report> getReports() {
        return Arrays.asList(Objects.requireNonNull(getItems(Report[].class, "reports.json")));
    }
}
