package org.example.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

//public class IdGenerator implements IdentifierGenerator {
//
//    public static final String NAME = "IdGenerator";
//
//    private final Random random = new Random();
//
//    @Override
//    public Object generate(SharedSessionContractImplementor session, Object object) {
//        return random.nextInt(MAX_VALUE);
//    }
//
//}
