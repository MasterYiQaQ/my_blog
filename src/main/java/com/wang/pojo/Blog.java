package com.wang.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    private String picture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean share;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;

    @Transient
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @ManyToOne
    private Type type;

    @ManyToMany
    private List<Tag> tags;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;

    public Blog(){

    }

    public Blog(Long id, String title, String content, String picture, String flag, Integer views, boolean appreciation, boolean share, boolean commentabled, boolean published, boolean recommend, Date createdate, Date updatedate, Type type, List<Tag> tags, User user, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.share = share;
        this.commentabled = commentabled;
        this.published = published;
        this.recommend = recommend;
        this.createdate = createdate;
        this.updatedate = updatedate;
        this.type = type;
        this.tags = tags;
        this.user = user;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void init(){
        this.ids= tagsToIds(this.getTags());
    }
    private  String tagsToIds(List<Tag> tags){
        if (!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag:tags){
                if(flag){
                    ids.append(",");
                }else{
                    flag=true;
                }
                ids.append(tag.getId());

            }
            return ids.toString();
        }else{
            return ids;
        }
    }
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", share=" + share +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createdate=" + createdate +
                ", updatedate=" + updatedate +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}
