package com.example.server.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.sequence.SequenceSupport;

public class SnowflakeDialect extends org.hibernate.dialect.Dialect {
    public SnowflakeDialect(){
        super();
    }
    @Override
    public SequenceSupport getSequenceSupport(){
        return new SnowflakeSequenceSupport();
    }
}
