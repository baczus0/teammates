package teammates.logic.automated;

import java.util.ArrayList;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teammates.common.util.HttpRequestHelper;
import teammates.logic.core.Emails;
import teammates.logic.core.FeedbackSessionsLogic;

@SuppressWarnings("serial")
public class FeedbackSessionPublishedRemindersServlet extends AutomatedRemindersServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		FeedbackSessionsLogic feedbackSessionsLogic = FeedbackSessionsLogic.inst();
		
		try {
			ArrayList<MimeMessage> emails = feedbackSessionsLogic.sendFeedbackSessionPublishedEmails();
			logActivitySuccess(req, emails);
		}  catch (Throwable e) {
			String reqParam = HttpRequestHelper.printRequestParameters(req);
			new Emails().sendErrorReport(req.getServletPath(), reqParam, e);
			logActivityFailure(req, e);	
		} 
	}

}
