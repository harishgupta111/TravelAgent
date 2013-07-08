package com.travel.agent.test.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class QuizDaoService {

//	@Autowired
//	private SchoolDaoService quizDaoService;
//
//	@Test
//	@Rollback(true)
//	public void testCreateQuiz() throws OQSystemException {
//		int n = quizDaoService.create(1L, 2, QuestionDifficultyLevel.BEGINNER,
//				QuestionSubject.JAVA);
//		Assert.assertEquals(2, n);
//
//	}

}
