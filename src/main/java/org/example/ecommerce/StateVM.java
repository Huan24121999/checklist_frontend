package org.example.ecommerce;

import org.example.ecommerce.dao.State;
import org.example.ecommerce.dao.Type;
import org.zkoss.bind.annotation.Init;

import java.util.LinkedList;
import java.util.List;

public class StateVM {
    private List<State> states;

    @Init
    public void init(){
        queryStates();
    }

    private void queryStates() {
        states = new LinkedList<>();
        for (int i = 0 ; i < 4 ; i++){
            State state = new State();
            state.setType(Type.values()[i % 4]);
            state.setValue(1317 * (i + 1));
            state.setRatio(0.329);
            states.add(state);
        }
    }

    public List<State> getStates() {
        return states;
    }

}
