package com.lingar.SocialEvents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.lingar.SocialEvents.tutorial.services.TutorialRunning;

@SpringBootApplication//(exclude=TutorialRunning.class)
//For make tutorial running U need to uncomment those two lines below  Or U just need to replace the exclude command liner
@ComponentScan(excludeFilters  = {@ComponentScan.Filter( // works at the end
              type = FilterType.ASSIGNABLE_TYPE, classes = {TutorialRunning.class})})// classes = {TutorialRunning.class //DatabaseLoader1.class}
public class SocialEventsTrying1Application {

	public static void main(String[] args) {
		System.out.println("new words22");
		SpringApplication.run(SocialEventsTrying1Application.class, args);
		System.out.println("new words3");
	}

}
