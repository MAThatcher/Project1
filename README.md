# Tuition Reimbursement Management System (TRMS)

## TRMS Description

The Tuition Reimbursement System, TRMS, allows users to submit reimbursements for courses and training. The submitted reimbursement must be approved by that employee's supervisor, department head, and benefits coordinator. The benefits coordinator then reviews the grade received before finalizing the reimbursement.

## Technologies Used

* Servlets
* Java
* JavaScript
* HTML
* CSS
* JDBC
* SQL

## Features

* Login feature
* Create a new reimbursement request
* Supervisors, department heads, and Benefit coordinator all have the ability to approve or reject a reimbursement request
* Department heads don't see the requests until the supervisor approves. Benefit coordinator does not see request until department head approves
* If supervisor is also a department head, the department head step is skipped. 
* If a request is denied, A reason must be provided. 
* Every step the reimbursement request takes will notify the requester via his home page. 
* Cucumber used for testing
* Requester is able to communicate via messages with the approvers through the application and vice versa. 

