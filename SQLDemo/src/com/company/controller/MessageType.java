package com.company.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public enum MessageType {
    CREATE_TABLE,
    INSERT_REGISTER,
    UPDATE_REGISTER,
    READ_REGISTER,
    DELETE_TABLE,
    DELETE_REGISTER
}
