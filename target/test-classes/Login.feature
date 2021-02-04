Feature: Log in fucntionality works

	Scenario: Logging in as an employee
		Given The employee is on the Login page
		When The employee enters his email
		When The employee enters his password
		When The employee clicks on log in
		Then The employee should be on his Home Page