package com.xiaoman.dao;

public class marking {
    private Integer markingId;

    private Integer textId;

    private Integer userId;

    private String eventType;

    private String trigger;

    private String holder;

    private String activityName;

    private String activityPlace;

    private String avtivityTime;

    private String constructor;

    private String buildingName;

    private String startingTime;

    private String buildingPlace;

    private String signatory;

    private String file;

    private String fileTime;

    private String filePlace;

    private String participant1;

    private String participant2;

    private String meetingTime;

    private String meetingPlace;

    public marking(Integer markingId, Integer textId, Integer userId, String eventType, String trigger, String holder, String activityName, String activityPlace, String avtivityTime, String constructor, String buildingName, String startingTime, String buildingPlace, String signatory, String file, String fileTime, String filePlace, String participant1, String participant2, String meetingTime, String meetingPlace) {
        this.markingId = markingId;
        this.textId = textId;
        this.userId = userId;
        this.eventType = eventType;
        this.trigger = trigger;
        this.holder = holder;
        this.activityName = activityName;
        this.activityPlace = activityPlace;
        this.avtivityTime = avtivityTime;
        this.constructor = constructor;
        this.buildingName = buildingName;
        this.startingTime = startingTime;
        this.buildingPlace = buildingPlace;
        this.signatory = signatory;
        this.file = file;
        this.fileTime = fileTime;
        this.filePlace = filePlace;
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.meetingTime = meetingTime;
        this.meetingPlace = meetingPlace;
    }

    public marking() {
        super();
    }

    public Integer getMarkingId() {
        return markingId;
    }

    public void setMarkingId(Integer markingId) {
        this.markingId = markingId;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger == null ? null : trigger.trim();
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder == null ? null : holder.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace == null ? null : activityPlace.trim();
    }

    public String getAvtivityTime() {
        return avtivityTime;
    }

    public void setAvtivityTime(String avtivityTime) {
        this.avtivityTime = avtivityTime == null ? null : avtivityTime.trim();
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor == null ? null : constructor.trim();
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime == null ? null : startingTime.trim();
    }

    public String getBuildingPlace() {
        return buildingPlace;
    }

    public void setBuildingPlace(String buildingPlace) {
        this.buildingPlace = buildingPlace == null ? null : buildingPlace.trim();
    }

    public String getSignatory() {
        return signatory;
    }

    public void setSignatory(String signatory) {
        this.signatory = signatory == null ? null : signatory.trim();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime == null ? null : fileTime.trim();
    }

    public String getFilePlace() {
        return filePlace;
    }

    public void setFilePlace(String filePlace) {
        this.filePlace = filePlace == null ? null : filePlace.trim();
    }

    public String getParticipant1() {
        return participant1;
    }

    public void setParticipant1(String participant1) {
        this.participant1 = participant1 == null ? null : participant1.trim();
    }

    public String getParticipant2() {
        return participant2;
    }

    public void setParticipant2(String participant2) {
        this.participant2 = participant2 == null ? null : participant2.trim();
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime == null ? null : meetingTime.trim();
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace == null ? null : meetingPlace.trim();
    }
}