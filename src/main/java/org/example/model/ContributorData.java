package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class ContributorData {
    private List<String> titles= new ArrayList<>();
    private List<Contributor> contributors= new ArrayList<>();

    public ContributorData() {
        titles.add("Code");
        titles.add("Bug");
        titles.add("Docs");
        titles.add("Arts");

        contributors.add(new Contributor("Kaleb", "Leonel", titles.get(0).toString(), 321));
        contributors.add(new Contributor("Balu", "Haben", titles.get(0).toString(), 321));
        contributors.add(new Contributor("Trey", "Wyatt", titles.get(0).toString(), 323));
        contributors.add(new Contributor("Balu", "Chen", titles.get(0).toString(), 324));
        contributors.add(new Contributor("Terry", "Tornado", titles.get(0).toString(), 711));
        contributors.add(new Contributor("Jesse", "Miles", titles.get(1).toString(), 712));
        contributors.add(new Contributor("Sadira", "Jobs", titles.get(1).toString(), 713));
        contributors.add(new Contributor("Jaquan", "Frederick", titles.get(2).toString(), 451));
        contributors.add(new Contributor("Avery", "Katrina", titles.get(2).toString(), 453));
        contributors.add(new Contributor("Heidi", "Nikolas", titles.get(2).toString(), 455));
        contributors.add(new Contributor("Katelyn", "Clara", titles.get(2).toString(), 457));
        contributors.add(new Contributor("Branden", "Shane", titles.get(2).toString(), 459));
        contributors.add(new Contributor("Dacey", "Obert", titles.get(2).toString(), 450));
        contributors.add(new Contributor("Julianna", "Allison", titles.get(3).toString(), 643));
        contributors.add(new Contributor("Rachel", "Elisabeth", titles.get(3).toString(), 644));
        contributors.add(new Contributor("Clarissa", "Francesca", titles.get(3).toString(), 645));
        contributors.add(new Contributor("Gabby", "Taffy", titles.get(3).toString(), 646));
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }
}
