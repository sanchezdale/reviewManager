package me.sanchezdale.reviewManager.data;

import java.util.List;

public interface ReviewRequestRepository {

    public void createReviewRequest(ReviewRequest reviewRequest);

    public ReviewRequest updateReviewRequest(ReviewRequest reviewRequest);

    public ReviewRequest retrieveReviewRequest(ReviewRequest reviewRequest);

    public ReviewRequest deleteReviewRequest(ReviewRequest reviewRequest);

    public List<ReviewRequest> listReviewRequest();

}
