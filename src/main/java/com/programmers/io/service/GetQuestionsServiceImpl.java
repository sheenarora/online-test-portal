package com.programmers.io.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmers.io.bean.ExamBean;
import com.programmers.io.bean.OptionBean;
import com.programmers.io.bean.QuestionBean;
import com.programmers.io.bean.QuestionCategoryBean;
import com.programmers.io.bean.SectionBean;
import com.programmers.io.common.CustomException;
import com.programmers.io.entities.Exam;
import com.programmers.io.entities.ExamDetail;
import com.programmers.io.entities.Options;
import com.programmers.io.entities.Question;
import com.programmers.io.entities.QuestionCategory;
import com.programmers.io.entities.Section;
import com.programmers.io.entities.User;
import com.programmers.io.repository.ExamDetailRepository;
import com.programmers.io.repository.ExamRepository;
import com.programmers.io.repository.QuestionRepository;
import com.programmers.io.repository.UserRepository;
import com.programmers.io.securityConfig.JwtTokenUtil;

@Service
public class GetQuestionsServiceImpl implements GetQuestionsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetQuestionsServiceImpl.class);

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public ExamRepository examRepository;

	@Autowired
	public ExamDetailRepository examDetailRepository;

	@Autowired
	public QuestionRepository questionRepository;

	@Autowired
	public JwtTokenUtil jwtTokenUtil;

	@Override
	public ExamBean getQuestions(String examId, String userId) throws Exception {
		ExamBean examBean = getExamBean(examId, userId);
		return examBean;
	}

	private ExamBean getExamBean(String examId, String userId) throws Exception {
		ExamBean examBean = new ExamBean();
		// Exam
		Exam exam = examRepository.findById(Long.parseLong(examId)).get();
		examBean.setExamName(exam.getExamName());

		// user
		User user = userRepository.findById(Long.parseLong(userId)).get();
		String name = user.getFirstName() + " " + user.getLastName();
		examBean.setUserName(name);

		// Exam Detail
		Set<ExamDetail> examDetailList = exam.getExamDetailList();

		Map<Long, QuestionCategoryBean> QuestionCategoryMap = new HashMap<Long, QuestionCategoryBean>(0);

		for (ExamDetail examDetail : examDetailList) {
			QuestionCategory questionCategory = examDetail.getQuestionCategory();
			Long questionCategoryId = questionCategory.getId();
			Section section = examDetail.getSection();
			Long sectionId = section.getId();
			int QuestionLimit = examDetail.getNumberOfQuestions();

			// get quetions for section and QuetionCategory
			List<Question> questions = questionRepository.findRandomQuestions(questionCategoryId, sectionId,
					QuestionLimit);
			if (questions.size() != QuestionLimit) {
				throw new CustomException("Insert more Questions of " + section.getMarksPerQuestion()
						+ " marks in Database for Question Category-" + questionCategory.getQuestionCategoryName());
			}

			List<QuestionBean> questionBeanList = new ArrayList();
			int j = 0;
			for (Question question : questions) {
				QuestionBean Qb = new QuestionBean();

				Set<Options> options = question.getOptions();
				List<OptionBean> optionBeanList = new ArrayList();
				int k = 0;
				for (Options option : options) {
					OptionBean Ob = new OptionBean();
					Ob.setId(String.valueOf(option.getId()));
					Ob.setText(option.getOptionText());
					optionBeanList.add(Ob);
					k++;
				}

				Qb.setId(String.valueOf(question.getId()));
				Qb.setText(question.getQuestionText());
				Qb.setOptions(optionBeanList);
				questionBeanList.add(Qb);
				j++;
			}

			// sectionBean
			SectionBean sb = new SectionBean();
			// sb.setId(String.valueOf(sectionId));
			sb.setExamDetailId(String.valueOf(examDetail.getId()));
			sb.setMarksPerQuestion(String.valueOf(section.getMarksPerQuestion()));
			sb.setNoOfQuestions(String.valueOf(QuestionLimit));
			sb.setQuestions(questionBeanList);

			QuestionCategoryBean Qcb = null;

			// If QuestionCategoryBean exist already then fetch that otherwise
			// create one
			if (QuestionCategoryMap.get(questionCategoryId) != null) {
				Qcb = QuestionCategoryMap.get(questionCategoryId);
				Qcb.getSections().add(sb);
			} else {
				Qcb = new QuestionCategoryBean();
				List<SectionBean> sbList = new ArrayList();
				sbList.add(sb);
				Qcb.setSections(sbList);
				// Qcb.setId(String.valueOf(questionCategoryId));
				Qcb.setText(questionCategory.getQuestionCategoryName());
				QuestionCategoryMap.put(questionCategoryId, Qcb);
			}

		}

		//
		List<QuestionCategoryBean> QuestionCategoryList = new ArrayList(QuestionCategoryMap.values());
		examBean.setQuestionCategories(QuestionCategoryList);

		return examBean;

	}

	private void fetchClaimsFromToken() {
		// TODO Auto-generated method stub

	}

}
