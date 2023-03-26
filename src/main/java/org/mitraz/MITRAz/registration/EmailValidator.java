package org.mitraz.MITRAz.registration;


import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {

        //TODO: Regex to validate

        if (s.contains(" ") || !s.contains("@"))
            return false;
        else
            return true;

    }
}
