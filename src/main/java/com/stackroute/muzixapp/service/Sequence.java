package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.exceptions.SequenceException;

public interface Sequence {

    int getNextSequenceId(String key) throws SequenceException;
}
