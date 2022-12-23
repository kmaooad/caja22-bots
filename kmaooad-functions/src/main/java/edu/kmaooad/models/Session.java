package edu.kmaooad.models;

import edu.kmaooad.statemachine.State;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

/*
* Stores the state of bot session with the user
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Session
{
    public Session(Long id) {
        this.id = id;
        this.currentState = new State.Any().key();
        this.statesCache = new HashMap<>();
    }

    public Long id;
    public String currentState;
    public Map<String, String> statesCache;
}
