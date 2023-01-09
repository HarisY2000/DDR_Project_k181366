package com.jobready.producercomsumer;

import java.util.List;

public class Consumer implements Runnable{

	List<Integer> questionList = null;
	
	public Consumer(List<Integer> questionList) {
		this.questionList = questionList;
	}
	
	public void answerQuestion() throws InterruptedException
	{
		synchronized(questionList) {
		while(questionList.isEmpty()) {
			System.out.println("No Questions to answer..waiting for producer to get questions");
			questionList.wait();
		}
	  
		}
		synchronized(questionList)
		{
			Thread.sleep(5000);
			System.out.println("Answered Question: " + questionList.remove(0));			Thread.sleep(100);
			questionList.notify();
		}
	}
	
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				answerQuestion();
			} catch (InterruptedException e) {
				
			}
		}
		

		
	}

}
