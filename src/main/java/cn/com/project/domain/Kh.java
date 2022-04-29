package cn.com.project.domain;

public class Kh {
    private Integer id;

    private String kname;

    private String bianhao;

    private String lxr;

    private String dz;

    private String khjb;

    private String khly;

    private String lname;

    private Integer aid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname == null ? null : kname.trim();
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao == null ? null : bianhao.trim();
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr == null ? null : lxr.trim();
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz == null ? null : dz.trim();
    }

    public String getKhjb() {
        return khjb;
    }

    public void setKhjb(String khjb) {
        this.khjb = khjb == null ? null : khjb.trim();
    }

    public String getKhly() {
        return khly;
    }

    public void setKhly(String khly) {
        this.khly = khly == null ? null : khly.trim();
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname == null ? null : lname.trim();
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

}