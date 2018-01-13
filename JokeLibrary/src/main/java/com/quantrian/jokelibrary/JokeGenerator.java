package com.quantrian.jokelibrary;

import java.util.Random;

public class JokeGenerator {
    private static final String[] jokes =new String[]
            {   "Q: How do you know when the moon has enough to eat? \rA: When it's full.",
                    "Q: What lies at the bottom of the ocean and shakes? \rA: A nervous wreck.",
                    "Q: Did you hear about the picket thieves? \rA: They took offence."
            };

    public JokeGenerator(){    }

    public static String tellAJoke(){
        Random r = new Random();
        int i = r.nextInt(jokes.length);
        return jokes[i];

    }
}
