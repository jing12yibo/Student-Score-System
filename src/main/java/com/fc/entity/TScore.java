package com.fc.entity;

import java.io.Serializable;

public class TScore  implements Serializable {
    private Integer scoreId;
    private String scoreTypeName;
    private Integer scoreValue;

    private String scoreType;

    private Integer studentId;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Integer scoreValue) {
        this.scoreValue = scoreValue;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType == null ? null : scoreType.trim();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setScoreTypeName(String scoreTypeName) {
        this.scoreTypeName = scoreTypeName;
    }

    public String getScoreTypeName() {
        return scoreTypeName;
    }
}