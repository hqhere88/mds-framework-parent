package mds.framework.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mds.framework.entity.Country;
import mds.framework.entity.Person;
import mds.framework.entity.Report;
import mds.framework.entity.UserPermissions;
import org.springframework.core.io.ClassPathResource;

import javax.smartcardio.Card;
import javax.xml.transform.Templates;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DataService {

    public static <T> T getItems(Class<T> clazz, String dataFileName) {
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

    public static List<Person> getPeople() {
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

    public static List<Person> getPeople(int count) {
        return getPeople().subList(0, count);
    }

    public static List<Person> getPeople(int count, Integer managerId) {
        List<Person> people = getPeople(managerId);
        return people.subList(0, count);
    }

    public static List<String> getProfessions() {
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

    /**
     * Get employees for a given manager.
     */
    public static List<Person> getPeople(Integer managerId) {
        List<Person> people = new ArrayList<>(getPeople());
        people.removeIf(person -> person.getManagerId() == null
                || !person.getManagerId().equals(managerId));
        return people;
    }

    /**
     * Get all managers.
     */
    public static List<Person> getManagers() {
        List<Person> people = new ArrayList<>(getPeople());
        people.removeIf(person -> !person.isManager());
        return people;
    }

    public static Templates getTemplates() {
        return getItems(Templates.class, "templates.json");
    }

    public static List<Card> getCards() {
        return Arrays.asList(Objects.requireNonNull(getItems(Card[].class, "cards.json")));
    }

    public static List<Country> getCountries() {
        return Arrays.asList(Objects.requireNonNull(getItems(Country[].class, "countries.json")));
    }

    public static List<UserPermissions> getUserPermissions() {
        return Arrays
                .asList(Objects.requireNonNull(getItems(UserPermissions[].class, "permissions.json")));
    }

    public static List<Report> getReports() {
        return Arrays.asList(Objects.requireNonNull(getItems(Report[].class, "reports.json")));
    }
}
