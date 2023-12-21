package com.example.server.dialect;

import org.hibernate.MappingException;
import org.hibernate.dialect.sequence.SequenceSupport;

public class SnowflakeSequenceSupport implements SequenceSupport {
    @Override
    public boolean supportsSequences() {
        return true;
    }

    @Override
    public boolean supportsPooledSequences() {
        return true;
    }

    @Override
    public String getSelectSequenceNextValString(String sequenceName) throws MappingException {
        return sequenceName + ".nextVal";
    }

    @Override
    public String getSelectSequencePreviousValString(String sequenceName) throws MappingException {
        return SequenceSupport.super.getSelectSequencePreviousValString(sequenceName);
    }

    @Override
    public String getSequenceNextValString(String sequenceName) throws MappingException {
        return "select " + getSelectSequenceNextValString(sequenceName);
    }

    @Override
    public String getSequencePreviousValString(String sequenceName) throws MappingException {
        return SequenceSupport.super.getSequencePreviousValString(sequenceName);
    }

    @Override
    public String getFromDual() {
        return SequenceSupport.super.getFromDual();
    }

    @Override
    public String getSequenceNextValString(String sequenceName, int increment) throws MappingException {
        return SequenceSupport.super.getSequenceNextValString(sequenceName, increment);
    }

    @Override
    public String[] getCreateSequenceStrings(String sequenceName, int initialValue, int incrementSize) throws MappingException {
        return SequenceSupport.super.getCreateSequenceStrings(sequenceName, initialValue, incrementSize);
    }

    @Override
    public String getCreateSequenceString(String sequenceName) throws MappingException {
        return SequenceSupport.super.getCreateSequenceString(sequenceName);
    }

    @Override
    public String getCreateSequenceString(String sequenceName, int initialValue, int incrementSize) throws MappingException {
        return SequenceSupport.super.getCreateSequenceString(sequenceName, initialValue, incrementSize);
    }

    @Override
    public String[] getDropSequenceStrings(String sequenceName) throws MappingException {
        return SequenceSupport.super.getDropSequenceStrings(sequenceName);
    }

    @Override
    public String getDropSequenceString(String sequenceName) throws MappingException {
        return SequenceSupport.super.getDropSequenceString(sequenceName);
    }

    @Override
    public boolean sometimesNeedsStartingValue() {
        return SequenceSupport.super.sometimesNeedsStartingValue();
    }

    @Override
    public String startingValue(int initialValue, int incrementSize) {
        return SequenceSupport.super.startingValue(initialValue, incrementSize);
    }
}
