package com.example.demo.model;

import java.util.UUID;

import javax.validation.constraints.Pattern;

import com.example.demo.validators.Patterns;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    @Pattern(regexp = Patterns.UUID_PATTERN, message = "studentID is not valid")
    public UUID studentId;
    @Pattern(regexp = Patterns.UUID_PATTERN, message = "advisorID is not valid")
    public UUID advisorId; 
    public boolean resolved;
    public Request( UUID studentId,UUID advisorId,boolean resolved) {
        this.studentId = studentId;
        this.advisorId = advisorId;
        this.resolved = resolved;
    }
    public Request(@JsonProperty("studentId") UUID studentId, @JsonProperty("advisorId")UUID advisorId) {
        this.studentId = studentId;
        this.advisorId = advisorId;
        this.resolved = false;
    }
    @Override
    public String toString() {
        return "Request [studentId=" + studentId + ", advisorId=" + advisorId + ", resolved=" + resolved + "]";
    }
}
