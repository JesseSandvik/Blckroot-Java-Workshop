package com.blckroot.sdk.command.model;

public class Option {
    private String longName;
    private String shortName;
    private String synopsis;
    private String parameterLabel;
    private Object value;

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getParameterLabel() {
        return parameterLabel;
    }

    public void setParameterLabel(String parameterLabel) {
        this.parameterLabel = parameterLabel;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
