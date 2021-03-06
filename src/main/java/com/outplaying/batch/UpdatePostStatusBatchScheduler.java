package com.outplaying.batch;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.outplaying.enumerables.PostStatus;
import com.outplaying.model.Post;
import com.outplaying.repository.IPostRepository;

@Component
public class UpdatePostStatusBatchScheduler {
	
	 private static int  yearsOfAntiquityToDelete = -2;
	 @Autowired
	 private IPostRepository postRepository;
	 
	
	@Scheduled(cron ="0 0 0 * * *" )
//	@Scheduled(cron = "*/10 * * * * *")
	public void schedule() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, yearsOfAntiquityToDelete);
		date = c.getTime();
		List<Post> postToDelete = postRepository.getPostOftwoYearsAgoAndStatusNotPosted(date, PostStatus.Posteado);
		
		for(int i = 0; i < postToDelete.size(); i++) {
			postRepository.deleteById(postToDelete.get(i).getIdPost());
		}
	}
}
