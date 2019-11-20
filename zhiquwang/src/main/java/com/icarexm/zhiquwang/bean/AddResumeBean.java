package com.icarexm.zhiquwang.bean;

public class AddResumeBean {
    private String company_name;
    private String  job_start;
    private String  job_end;
    private String job_content;
    private String  leave_cause;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getJob_start() {
        return job_start;
    }

    public void setJob_start(String job_start) {
        this.job_start = job_start;
    }

    public String getJob_end() {
        return job_end;
    }

    public void setJob_end(String job_end) {
        this.job_end = job_end;
    }

    public String getJob_content() {
        return job_content;
    }

    public void setJob_content(String job_content) {
        this.job_content = job_content;
    }

    public String getLeave_cause() {
        return leave_cause;
    }

    public void setLeave_cause(String leave_cause) {
        this.leave_cause = leave_cause;
    }

    public AddResumeBean(String company_name, String job_start, String job_end, String job_content, String leave_cause) {
        this.company_name = company_name;
        this.job_start = job_start;
        this.job_end = job_end;
        this.job_content = job_content;
        this.leave_cause = leave_cause;
    }
}
