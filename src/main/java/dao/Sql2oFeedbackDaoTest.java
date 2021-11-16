package dao;

import models.Feedback;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oFeedbackDaoTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
        FeedbackDao.clearAllFeedbacks();
    }

    @org.junit.Test
    public void getAllFeedbacks_ReturnsAllFeedbacks_True() {
        Feedback f1 = setUpFeedback();
        Feedback f2 = setUpFeedback();

        feedbackDao.addFeedback(f1);
        feedbackDao.addFeedBack(f2);

        assertEquals(2, feedBackDao.getAllFeedbacks().size());
        asserTrue(feedBackDao.getAllFeedBacks().containsAll(Arrays.asList(f1,f2)));
    }

    @org.junit.Test
    public void addFeedback_AddsFeedbackSetsId_True() {
        Feedback f1 = setUpFeedback();
        Feedback f2 = setUpFeedback();

        int f1_id = f1.getId();
        int f2_id = f2.getId();

        feedbackDao.addFeedback(f1);
        feedbackDao.addFeedback(f2);

        asserTrue(f1_id!=f1.getId());
        asserTrue(f2_id!=f2.getId());
        asserTrue(f2.getId()>f1.getId());
        asserTrue(1=f2.getId()-f1.getId());
        asserTrue(feedbackDao.getAllFeedbacks().containsAll(Arrays.asList(f1,f2)));

    }

    @org.junit.Test
    public void findFeedbackById_ReturnsCorrectFeedback_True() {
        Feedback f1 = setUpFeedback();
        Feedback f2 = setUpFeedback();

        feedbackDao.addFeedback(f1);
        feedbackDao.addFeedback(f2);

        Feedback foundFeedback = feedbackDao.findFeedbackById(f1.getId());
        assertEquals(f1,foundFeedback);

    }

    @org.junit.Test
    public void updateFeedback_UpdatesFeedback_True() {
        Feedback f1 = setUpFeedback();
        Feedback f2 = setUpFeedback();
        feedbackDao.addUser(f1);
        feedbackDao.addUser(f2);

        String ol_name = f1.getName();
        String ol_message = f1.getMessage();

        feedbackDao.updateUser(f1, "John Doe","Good service");

        assertNotEquals(ol_name, f1.getName());
        assertNotEquals(ol_message,f1.getMessage());
        assertNotEquals(ol_name, f2.getName());
        assertNotEquals(ol_message,f2.getMessage());


    }

    @org.junit.Test
    public void clearAllFeedbacks_clearAllFeedbacks_True() {
        Feedback f1 = setUpFeedback();
        Feedback f2 = setUpFeedback();

        feedbackDao.addFeedback(f1);
        feedbackDao.addFeedback(f2);
        feedbackDao.clearAllFeedbacks();

        assertEquals(0, feedbackDao.getFeedbacks().size())
    }
    private Feedback setUpFeedback(){
        return new Feedback(0,"Cliff","Crucial");
    }
}