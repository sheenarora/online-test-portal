package com.programmers.io.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmers.io.bean.ExamBean;
import com.programmers.io.bean.QuestionBean;
import com.programmers.io.bean.QuestionCategoryBean;
import com.programmers.io.bean.ResultBean;
import com.programmers.io.bean.ResultPerCategoryBean;
import com.programmers.io.bean.SectionBean;
import com.programmers.io.bean.StatusBean;
import com.programmers.io.common.Constant;
import com.programmers.io.common.CustomException;
import com.programmers.io.entities.Exam;
import com.programmers.io.entities.ExamDetail;
import com.programmers.io.entities.Result;
import com.programmers.io.entities.ResultDetail;
import com.programmers.io.entities.Section;
import com.programmers.io.entities.User;
import com.programmers.io.repository.ExamDetailRepository;
import com.programmers.io.repository.ExamRepository;
import com.programmers.io.repository.OptionsRepository;
import com.programmers.io.repository.QuestionCategoryRepository;
import com.programmers.io.repository.QuestionRepository;
import com.programmers.io.repository.ResultDetailRepository;
import com.programmers.io.repository.ResultRepository;
import com.programmers.io.repository.SectionRepository;
import com.programmers.io.repository.UserRepository;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private ResultDetailRepository resultDetailRepository;

	@Autowired
	public ExamRepository examRepository;

	@Autowired
	public ExamDetailRepository examDetailRepository;

	@Autowired
	public QuestionRepository questionRepository;

	@Autowired
	public SectionRepository sectionRepository;

	@Autowired
	public OptionsRepository optionsRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public QuestionCategoryRepository questionCategoryRepository;

	@Override
	public Result saveResult(Result result) {
		result = resultRepository.save(result);
		return result;
	}

	@Override
	public StatusBean validateSubmitRequest(ExamBean examBean, String examId, String userId) throws Exception {
		StatusBean status = new StatusBean(Constant.SUCCESS_CODE, "Valid Request");
		List<QuestionCategoryBean> questionCategoryBeans = examBean.getQuestionCategories();

		Exam exam = examRepository.findById(Long.parseLong(examId)).get();
		Set<ExamDetail> examDetailSet = exam.getExamDetailList();
		boolean error = false;
		String message = null;
		if (questionCategoryBeans == null) {
			error = true;
			message = "QuestionCategory not found.";
		} else {
			for (QuestionCategoryBean questionCategoryBean : questionCategoryBeans) {
				List<SectionBean> sectionBeans = questionCategoryBean.getSections();
				if (sectionBeans == null) {
					error = true;
					message = "Sections not found.";
				} else {
					for (SectionBean sectionBean : sectionBeans) {
						long ExamDetailId = 0;
						if (sectionBean.getExamDetailId() != null) {
							ExamDetailId = Long.parseLong(sectionBean.getExamDetailId());
							int noOfQuestions = Integer.parseInt(sectionBean.getNoOfQuestions());
							ExamDetail examDetail = null;
							if (examDetailRepository.findById(ExamDetailId).isPresent()) {
								examDetail = examDetailRepository.findById(ExamDetailId).get();
								if (!examDetailSet.contains(examDetail)) {
									error = true;
									message = "Invalid ExamDetail.";
								} else if (sectionBean.getQuestions().size() != noOfQuestions) {
									error = true;
									message = "Invalid value for noOfQuestions.";
								}
							} else {
								error = true;
								message = "Invalid ExamDetailId.";
							}

						} else {
							error = true;
							message = "ExamDetailId not found in sections.";
						}
					}

				}

			}
		}

		if (error == true) {
			status.setCode(Constant.BAD_REQUEST_CODE);
			status.setMessage(message);
		}

		return status;
	}

	@Override
	public List calculateResult(ExamBean examBean, String examId, String userId) throws Exception {
		Set<ResultDetail> resultByCategorySet = new HashSet<ResultDetail>();

		Result result = new Result();
		User user = userRepository.findById(Long.parseLong(userId)).get();
		result.setUser(user);

		Exam exam = examRepository.findById(Long.parseLong(examId)).get();
		result.setExam(exam);

		result = saveResult(result);

		List<ResultPerCategoryBean> resultResponseList = new ArrayList<ResultPerCategoryBean>();

		List<QuestionCategoryBean> questionCategoryBeans = examBean.getQuestionCategories();
		for (QuestionCategoryBean questionCategoryBean : questionCategoryBeans) {
			int totalMarksPerCategory = 0;
			int obtainedMarksPerCategory = 0;
			List<SectionBean> sectionBeans = questionCategoryBean.getSections();
			ResultPerCategoryBean resultResponse = new ResultPerCategoryBean();
			resultResponse.setQuestionCategory(questionCategoryBean.getText());
			for (SectionBean sectionBean : sectionBeans) {
				long ExamDetailId = Long.parseLong(sectionBean.getExamDetailId());
				ExamDetail examDetail = examDetailRepository.findById(ExamDetailId).get();

				Section section = examDetail.getSection();

				ResultDetail resultDetail = new ResultDetail();
				resultDetail.setExamDetail(examDetail);
				int obtainedMarks = 0;
				int totalMarks = examDetail.getNumberOfQuestions() * section.getMarksPerQuestion();
				totalMarksPerCategory = totalMarksPerCategory + totalMarks;
				resultDetail.setTotalMarks(totalMarks);

				List<QuestionBean> questionbeans = sectionBean.getQuestions();
				for (QuestionBean questionBean : questionbeans) {
					if (questionBean.getAnswer() != null) {
						long answerId = 0;
						try {
							answerId = Long.parseLong(questionBean.getAnswer());
						} catch (Exception e) {
							throw new CustomException("Answer should always be numeric (id of correct option)");
						}
						if (optionsRepository.findById(answerId).isPresent() && questionBean.getId() != null) {
							if (optionsRepository.findById(answerId).get().getAnswer()) {
								obtainedMarks += section.getMarksPerQuestion();
							}
						}
					}

				}

				obtainedMarksPerCategory += obtainedMarks;

				resultDetail.setObtainedMarks(obtainedMarks);
				resultDetail = resultDetailRepository.save(resultDetail);
				resultByCategorySet.add(resultDetail);

			}

			resultResponse.setObtainedMarks(obtainedMarksPerCategory);
			resultResponse.setTotalMarks(totalMarksPerCategory);
			resultResponseList.add(resultResponse);

		}
		result.setResultDetailSet(resultByCategorySet);
		result = saveResult(result);

		return resultResponseList;
	}

}