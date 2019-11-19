package gxyclub.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class MatchBean implements  Parcelable {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {


        private String blueCountryImageUrl;
        private int bluePlayerId;
        private String bluePlayerImageUrl;
        private String blueRecord;
        private String blueZhName;
        private int competitionId;
        private String competitionLocation;
        private int competitionStatus;
        private String competitionStatusName;
        private long competitionTime;
        private String competitionTimeStr;
        private String competitionTitle;
        private long endGuessTime;
        private String gameDurationStr;
        private String gameResultStr;
        private int guessRuleStatus;
        private int guessRuleType;
        private int id;
        private int invalidDay;
        private int isGuessing;
        private String kilogramLevel;
        private String kilogramLevelStr;
        private int minGuessSession;
        private int nature;
        private String natureStr;
        private int project;
        private String projectStr;
        private String redCountryImageUrl;
        private int redPlayerId;
        private String redPlayerImageUrl;
        private String redRecord;
        private String redZhName;
        private int result;
        private String resultStr;
        private long startGuessTime;
        private int status;
        private String statusName;
        private int blueScore;
        private int gameDuration;
        private int gameResult;
        private int redScore;
        private String videoCommonUrl;
        private int videoId;
        private String videoUrl;
        private int isHot;

        public String getBlueCountryImageUrl() {
            return blueCountryImageUrl;
        }

        public void setBlueCountryImageUrl(String blueCountryImageUrl) {
            this.blueCountryImageUrl = blueCountryImageUrl;
        }

        public int getBluePlayerId() {
            return bluePlayerId;
        }

        public void setBluePlayerId(int bluePlayerId) {
            this.bluePlayerId = bluePlayerId;
        }

        public String getBluePlayerImageUrl() {
            return bluePlayerImageUrl;
        }

        public void setBluePlayerImageUrl(String bluePlayerImageUrl) {
            this.bluePlayerImageUrl = bluePlayerImageUrl;
        }

        public String getBlueRecord() {
            return blueRecord;
        }

        public void setBlueRecord(String blueRecord) {
            this.blueRecord = blueRecord;
        }

        public String getBlueZhName() {
            return blueZhName;
        }

        public void setBlueZhName(String blueZhName) {
            this.blueZhName = blueZhName;
        }

        public int getCompetitionId() {
            return competitionId;
        }

        public void setCompetitionId(int competitionId) {
            this.competitionId = competitionId;
        }

        public String getCompetitionLocation() {
            return competitionLocation;
        }

        public void setCompetitionLocation(String competitionLocation) {
            this.competitionLocation = competitionLocation;
        }

        public int getCompetitionStatus() {
            return competitionStatus;
        }

        public void setCompetitionStatus(int competitionStatus) {
            this.competitionStatus = competitionStatus;
        }

        public String getCompetitionStatusName() {
            return competitionStatusName;
        }

        public void setCompetitionStatusName(String competitionStatusName) {
            this.competitionStatusName = competitionStatusName;
        }

        public long getCompetitionTime() {
            return competitionTime;
        }

        public void setCompetitionTime(long competitionTime) {
            this.competitionTime = competitionTime;
        }

        public String getCompetitionTimeStr() {
            return competitionTimeStr;
        }

        public void setCompetitionTimeStr(String competitionTimeStr) {
            this.competitionTimeStr = competitionTimeStr;
        }

        public String getCompetitionTitle() {
            return competitionTitle;
        }

        public void setCompetitionTitle(String competitionTitle) {
            this.competitionTitle = competitionTitle;
        }

        public long getEndGuessTime() {
            return endGuessTime;
        }

        public void setEndGuessTime(long endGuessTime) {
            this.endGuessTime = endGuessTime;
        }

        public String getGameDurationStr() {
            return gameDurationStr;
        }

        public void setGameDurationStr(String gameDurationStr) {
            this.gameDurationStr = gameDurationStr;
        }

        public String getGameResultStr() {
            return gameResultStr;
        }

        public void setGameResultStr(String gameResultStr) {
            this.gameResultStr = gameResultStr;
        }

        public int getGuessRuleStatus() {
            return guessRuleStatus;
        }

        public void setGuessRuleStatus(int guessRuleStatus) {
            this.guessRuleStatus = guessRuleStatus;
        }

        public int getGuessRuleType() {
            return guessRuleType;
        }

        public void setGuessRuleType(int guessRuleType) {
            this.guessRuleType = guessRuleType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInvalidDay() {
            return invalidDay;
        }

        public void setInvalidDay(int invalidDay) {
            this.invalidDay = invalidDay;
        }

        public int getIsGuessing() {
            return isGuessing;
        }

        public void setIsGuessing(int isGuessing) {
            this.isGuessing = isGuessing;
        }

        public String getKilogramLevel() {
            return kilogramLevel;
        }

        public void setKilogramLevel(String kilogramLevel) {
            this.kilogramLevel = kilogramLevel;
        }

        public String getKilogramLevelStr() {
            return kilogramLevelStr;
        }

        public void setKilogramLevelStr(String kilogramLevelStr) {
            this.kilogramLevelStr = kilogramLevelStr;
        }

        public int getMinGuessSession() {
            return minGuessSession;
        }

        public void setMinGuessSession(int minGuessSession) {
            this.minGuessSession = minGuessSession;
        }

        public int getNature() {
            return nature;
        }

        public void setNature(int nature) {
            this.nature = nature;
        }

        public String getNatureStr() {
            return natureStr;
        }

        public void setNatureStr(String natureStr) {
            this.natureStr = natureStr;
        }

        public int getProject() {
            return project;
        }

        public void setProject(int project) {
            this.project = project;
        }

        public String getProjectStr() {
            return projectStr;
        }

        public void setProjectStr(String projectStr) {
            this.projectStr = projectStr;
        }

        public String getRedCountryImageUrl() {
            return redCountryImageUrl;
        }

        public void setRedCountryImageUrl(String redCountryImageUrl) {
            this.redCountryImageUrl = redCountryImageUrl;
        }

        public int getRedPlayerId() {
            return redPlayerId;
        }

        public void setRedPlayerId(int redPlayerId) {
            this.redPlayerId = redPlayerId;
        }

        public String getRedPlayerImageUrl() {
            return redPlayerImageUrl;
        }

        public void setRedPlayerImageUrl(String redPlayerImageUrl) {
            this.redPlayerImageUrl = redPlayerImageUrl;
        }

        public String getRedRecord() {
            return redRecord;
        }

        public void setRedRecord(String redRecord) {
            this.redRecord = redRecord;
        }

        public String getRedZhName() {
            return redZhName;
        }

        public void setRedZhName(String redZhName) {
            this.redZhName = redZhName;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getResultStr() {
            return resultStr;
        }

        public void setResultStr(String resultStr) {
            this.resultStr = resultStr;
        }

        public long getStartGuessTime() {
            return startGuessTime;
        }

        public void setStartGuessTime(long startGuessTime) {
            this.startGuessTime = startGuessTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public int getBlueScore() {
            return blueScore;
        }

        public void setBlueScore(int blueScore) {
            this.blueScore = blueScore;
        }

        public int getGameDuration() {
            return gameDuration;
        }

        public void setGameDuration(int gameDuration) {
            this.gameDuration = gameDuration;
        }

        public int getGameResult() {
            return gameResult;
        }

        public void setGameResult(int gameResult) {
            this.gameResult = gameResult;
        }

        public int getRedScore() {
            return redScore;
        }

        public void setRedScore(int redScore) {
            this.redScore = redScore;
        }

        public String getVideoCommonUrl() {
            return videoCommonUrl;
        }

        public void setVideoCommonUrl(String videoCommonUrl) {
            this.videoCommonUrl = videoCommonUrl;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.blueCountryImageUrl);
            dest.writeInt(this.bluePlayerId);
            dest.writeString(this.bluePlayerImageUrl);
            dest.writeString(this.blueRecord);
            dest.writeString(this.blueZhName);
            dest.writeInt(this.competitionId);
            dest.writeString(this.competitionLocation);
            dest.writeInt(this.competitionStatus);
            dest.writeString(this.competitionStatusName);
            dest.writeLong(this.competitionTime);
            dest.writeString(this.competitionTimeStr);
            dest.writeString(this.competitionTitle);
            dest.writeLong(this.endGuessTime);
            dest.writeString(this.gameDurationStr);
            dest.writeString(this.gameResultStr);
            dest.writeInt(this.guessRuleStatus);
            dest.writeInt(this.guessRuleType);
            dest.writeInt(this.id);
            dest.writeInt(this.invalidDay);
            dest.writeInt(this.isGuessing);
            dest.writeString(this.kilogramLevel);
            dest.writeString(this.kilogramLevelStr);
            dest.writeInt(this.minGuessSession);
            dest.writeInt(this.nature);
            dest.writeString(this.natureStr);
            dest.writeInt(this.project);
            dest.writeString(this.projectStr);
            dest.writeString(this.redCountryImageUrl);
            dest.writeInt(this.redPlayerId);
            dest.writeString(this.redPlayerImageUrl);
            dest.writeString(this.redRecord);
            dest.writeString(this.redZhName);
            dest.writeInt(this.result);
            dest.writeString(this.resultStr);
            dest.writeLong(this.startGuessTime);
            dest.writeInt(this.status);
            dest.writeString(this.statusName);
            dest.writeInt(this.blueScore);
            dest.writeInt(this.gameDuration);
            dest.writeInt(this.gameResult);
            dest.writeInt(this.redScore);
            dest.writeString(this.videoCommonUrl);
            dest.writeInt(this.videoId);
            dest.writeString(this.videoUrl);
            dest.writeInt(this.isHot);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.blueCountryImageUrl = in.readString();
            this.bluePlayerId = in.readInt();
            this.bluePlayerImageUrl = in.readString();
            this.blueRecord = in.readString();
            this.blueZhName = in.readString();
            this.competitionId = in.readInt();
            this.competitionLocation = in.readString();
            this.competitionStatus = in.readInt();
            this.competitionStatusName = in.readString();
            this.competitionTime = in.readLong();
            this.competitionTimeStr = in.readString();
            this.competitionTitle = in.readString();
            this.endGuessTime = in.readLong();
            this.gameDurationStr = in.readString();
            this.gameResultStr = in.readString();
            this.guessRuleStatus = in.readInt();
            this.guessRuleType = in.readInt();
            this.id = in.readInt();
            this.invalidDay = in.readInt();
            this.isGuessing = in.readInt();
            this.kilogramLevel = in.readString();
            this.kilogramLevelStr = in.readString();
            this.minGuessSession = in.readInt();
            this.nature = in.readInt();
            this.natureStr = in.readString();
            this.project = in.readInt();
            this.projectStr = in.readString();
            this.redCountryImageUrl = in.readString();
            this.redPlayerId = in.readInt();
            this.redPlayerImageUrl = in.readString();
            this.redRecord = in.readString();
            this.redZhName = in.readString();
            this.result = in.readInt();
            this.resultStr = in.readString();
            this.startGuessTime = in.readLong();
            this.status = in.readInt();
            this.statusName = in.readString();
            this.blueScore = in.readInt();
            this.gameDuration = in.readInt();
            this.gameResult = in.readInt();
            this.redScore = in.readInt();
            this.videoCommonUrl = in.readString();
            this.videoId = in.readInt();
            this.videoUrl = in.readString();
            this.isHot = in.readInt();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.list);
    }

    public MatchBean() {
    }

    protected MatchBean(Parcel in) {
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Creator<MatchBean> CREATOR = new Creator<MatchBean>() {
        @Override
        public MatchBean createFromParcel(Parcel source) {
            return new MatchBean(source);
        }

        @Override
        public MatchBean[] newArray(int size) {
            return new MatchBean[size];
        }
    };
}



