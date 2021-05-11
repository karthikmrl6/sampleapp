package academy.learnprog;
public class EmployeeNode {
	private Employee employee;
	private EmployeeNode next;
	private EmployeeNode previous;

	public EmployeeNode(Employee employee) {
		super();
		this.employee = employee;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @return the next
	 */
	public EmployeeNode getNext() {
		return next;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(EmployeeNode next) {
		this.next = next;
	}

	/**
	 * @return the previous
	 */
	public EmployeeNode getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(EmployeeNode previous) {
		this.previous = previous;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return employee.toString();
	}
}
