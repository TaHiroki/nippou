/** http://localhost:8080/daily_report_system/reports/index **/

package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "comments")
@NamedQueries({
   @NamedQuery(
           name = "getAllComments",
           query = "SELECT c FROM Comment As c ORDER BY c.id DESC"
           ),
   @NamedQuery(
           name = "getCommentsCount",
           query = "SELECT COUNT(c) FROM Comment AS c"
           ),
})
@Entity
public class Comment {
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "report_id", nullable = false)
   private Report report;

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "title", length = 255, nullable = false)
   private String title;

   @Column(name = "comment_date", nullable = false)
   private Date comment_date;

   @Lob
   @Column(name = "comment", nullable = false)
   private String comment;

   @Column(name = "created_at", nullable = false)
   private Timestamp creater_at;

   @Column(name ="updated_at", nullable = false)
   private Timestamp updated_at;

   public Integer getId() {
       return id;
   }

   public void setId(Integer id) {
       this.id = id;
   }

   public Report getReport() {
       return report;
   }

   public void setReport(Report report) {
       this.report = report;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getTitle() {
       return title;
   }

   public void setTitle(String title) {
       this.title = title;
   }

   public Date getComment_date() {
       return comment_date;
   }

   public void setComment_date(Date comment_date) {
       this.comment_date = comment_date;
   }

   public String getComment() {
       return comment;
   }

   public void setComment(String comment) {
       this.comment = comment;
   }

   public Timestamp getCreater_at() {
       return creater_at;
   }

   public void setCreater_at(Timestamp creater_at) {
       this.creater_at = creater_at;
   }

   public Timestamp getUpdated_at() {
       return updated_at;
   }

   public void setUpdated_at(Timestamp updated_at) {
       this.updated_at = updated_at;
   }
}