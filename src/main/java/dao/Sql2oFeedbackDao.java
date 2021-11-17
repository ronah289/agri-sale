package dao;

import models.Feedback;
import org.sql2o.Sql2o;

import java.sql.Connection;
import java.util.List;

public class Sql2oFeedbackDao implements FeedbackDao{
    private final Sql2o sql2o;
    public Sql2oFeedbackDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public List<Feedback> getAllFeedbacks(){
        String sql = "SELECT * FROM feedback";
        try(Connection con = sql2o.open()){
            return ((org.sql2o.Connection) con).createQuery(sql).executeAndFetch(Feedback.class);
        }
    }

    @Override
    public void addFeedback(Feedback feedback){
        String sql = "INSERT INTO feedback(name,message) VALUES(:name, :message)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(feedback)
                    .executeUpdate()
                    .getKey();
            feedback.setId(id);
        }
    }

    @Override
    public Feedback findFeedbackById(int id){
        String sql = "SELECT * FROM feedback WHERE id=:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Feedback.class);
        }
    }
    @Override
    public void updateFeedback(Feedback feedback, String name, String message){
        String sql = "UPDATE feedback SET (name,message) = (:name, :message) WHERE id =:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("message",message)
                    .addParameter("id",feedback.getId())
                    .executeUpdate();
            feedback.setName(name);
            feedback.setMessage(message);
        }
    }
    @Override
    public void clearAllFeedbacks(){
        String sql = "DELETE FROM feedback";
        try(Connection con = sql2o.oopen()){
            con.createQuery(sql)
                    .executeUpdate();
        }
    }
}