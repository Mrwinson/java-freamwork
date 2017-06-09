package com.cloudm.framework.common.sms;

import java.util.Date;

/**
 * @description:
 * @author: Courser
 * @date: 2017/6/8
 * @version: V1.0
 */
public class ExectTimeBO {
    private String classname;
    private Date starttime;
    private String time;
    private Integer maxtime;
    private Integer mintime;
    private Integer avgtime;
    private Integer calls;
    private Integer system_id;

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMaxtime(Integer maxtime) {
        this.maxtime = maxtime;
    }

    public void setMintime(Integer mintime) {
        this.mintime = mintime;
    }

    public void setAvgtime(Integer avgtime) {
        this.avgtime = avgtime;
    }

    public void setCalls(Integer calls) {
        this.calls = calls;
    }

    public void setSystem_id(Integer system_id) {
        this.system_id = system_id;
    }

    public String getClassname() {

        return classname;
    }

    public Date getStarttime() {
        return starttime;
    }

    public String getTime() {
        return time;
    }

    public Integer getMaxtime() {
        return maxtime;
    }

    public Integer getMintime() {
        return mintime;
    }

    public Integer getAvgtime() {
        return avgtime;
    }

    public Integer getCalls() {
        return calls;
    }

    public Integer getSystem_id() {
        return system_id;
    }
}
