package com.toprate.hr_tek_demo.generators;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.stream.Stream;

public class GeneratorUserId implements IdentifierGenerator {

    private String prefix = "USER";
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = "SELECT u.userId FROM Users u";

        Stream<String> ids = session.createQuery(query, String.class).stream();
        Long max = ids.map(o -> o.replace(prefix, ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);
        return prefix + (String.format("%04d", max + 1));
    }
}
