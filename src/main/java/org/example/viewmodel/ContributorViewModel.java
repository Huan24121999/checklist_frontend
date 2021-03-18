package org.example.viewmodel;

import org.example.model.Contributor;
import org.example.model.ContributorData;
import org.zkoss.bind.annotation.Init;

import java.util.ArrayList;
import java.util.List;

public class ContributorViewModel {
    private Contributor selected;
    private List<String> titles= new ArrayList<>(new ContributorData().getTitles());
    private List<Contributor> contributors= new ArrayList<>(new ContributorData().getContributors());

    @Init
    public void init(){
        selected=contributors.get(0);
    }

    public Contributor getSelected() {
        return selected;
    }

    public void setSelected(Contributor selected) {
        this.selected = selected;
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
