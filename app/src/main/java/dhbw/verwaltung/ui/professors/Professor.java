package dhbw.verwaltung.ui.professors;

import java.util.Arrays;

public class Professor {
    public final String name, role, office, email, site;

    public Professor(String name, String office) {
        this(name, "Professor für Informatik", office);
    }

    public Professor(String name, String role, String office) {
        this.name = name;
        this.role = role;
        this.office = office;
        String[] words = name.toLowerCase().split(" ");
        email = String.format("%s.%s@dhbw-stuttgart.de", (Object[]) Arrays.copyOfRange(words, words.length - 2, words.length));
        site = String.format("https://www.dhbw-stuttgart.de/themen/studienangebot/fakultaet-technik/informatik/kontakt/%s/",
            name.replaceAll(" ", "-").replaceAll("\\.", "").toLowerCase());
    }

    public Professor(String name, String role, String office, String email, String site) {
        this.name = name;
        this.role = role;
        this.office = office;
        this.email = email;
        this.site = site;
    }
}
