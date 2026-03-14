package com.gamifiedlibrary.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.gamifiedlibrary.api.domain.model.QuizResult;
import com.gamifiedlibrary.api.domain.model.QuizResultId;
import com.gamifiedlibrary.api.infrastructure.dto.leaderboard.UserLeaderboardDTO;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, QuizResultId> {
	
	//atribuicao ao dto é por ordem do select
	@NativeQuery("select  app_user.id, app_user.username, app_user.profile_pic,"
			+ "SUM(quiz_result.points) as total_points, SUM(quiz_result.random_answers_choosed) as total_random_answers"
			+ " from quiz_result "
			+ "inner join app_user on app_user.id = quiz_result.user_id "
			+ "group by app_user.id, app_user.username, app_user.profile_pic "
			+ "order by total_points desc limit ?1")
	public List<UserLeaderboardDTO> findUsersWithHighestTotalScore(int size);
	
	public List<QuizResult> findByUserId(Long userId);
}

/*
 * @Query("select SUM(points) as totalPoints from quiz_result "
			+ "inner join app_user on app_user.id = quiz_result.user_id "
			+ "group by app_user.id order by total_points desc limit 25")*/
