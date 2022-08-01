import com.ak.hrms.appraisal.HRWorkflow;
import com.ak.hrms.appraisal.ManagerWorkflow;
import com.ak.hrms.appraisal.Utils;
import com.ak.hrms.appraisal.model.Employee;

public class Test {

	public static void main(String[] args) {
		HRWorkflow hr = new HRWorkflow();
		ManagerWorkflow mgr = new ManagerWorkflow();

		for (Employee employee : Utils.getAllEmployees()) {
			System.out.println("=========================Next Employee=================");
			employee = hr.onBoarding(employee);
			hr.appraisalInitiated(employee);

			mgr.inviteEmployeeForDiscussion(employee);
			mgr.discussionClosed(employee);
			mgr.setRating(employee, Utils.rating(employee), Utils.comment(employee));
			System.out.println("Before Appraisal: \n"+employee.toString()+"\n");
			hr.closeAppraisal(employee);
			mgr.distributeLetter(employee);
			System.out.println("After Appraisal: \n"+employee.toString()+"\n");
			

		}

	}

	
}
