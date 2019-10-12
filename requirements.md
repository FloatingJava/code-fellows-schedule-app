# Java 401 Midterm Project: Software Requirements

## Vision
Our goal is for this to be a publishable tool for Code Fellows to use on their website. Prospective and current students would be able to create and save tracks through the classes based on filters such as start date, 401 language preferred, or fastest track to graduation. If it becomes a viable tool, subsequent versions could include functionality to edit and delete saved tracks, collecting prospective student information to funnel to Code Fellows Admissions, responsive calendars, course descriptions, and job demand with average salaries for each 401-language offered. Currently students are not given tools to calculate educational plans beyond sorting by level on the Code Fellows website. This would give students a more comprehensive scheduling tool. We hope that when fully developed this tool will provide benefits to prospective students, current students, admission staff, and Code Fellows Administration.

## Scope (In/Out)
### In
Our scheduling tool will provide the following functionality in the MVP:
•	Front page will feature a form that allows students to explore custom tracks through the program with the ability to clear the form or save the track with a name they choose. Saving the track will require the student has created an account and is signed in.
•	The navbar will include the Saved Tracks, Sign Up, Log In, Log Out, About Us pages/functionality.
•	The ‘Create Account’ page will collect at minimum the user’s email, password, first name, and last name.
•	The ‘Saved Tracks’ page will list the user’s saved tracks and allow the user to click on a track to view the details of that track (classes, dates, language, etc).
•	The tool will pull the scheduling data from Code Fellow’s live url feed of published classes.
### Out
Our scheduling tool will not provide the following functionality in the MVP:
•	This tool will never track general items, it is specific to Code Fellows courses.
•	This tool will never be a job board.
## MVP
Our MVP will include:
•	A ‘front page’ with the course scheduling form which will recommend a course based on the Code Fellows JSON data and the filters selected by the student. An option to save generated tracks with custom names.
•	A ‘Create Account’ page for new users that will require an email, password, first and last name.
•	Log in and log out functionality.
•	A ‘Saved Tracks’ page where the user can view the tracks they have saved and delete them if desired.
•	An ‘About Us’ page with information about the Float team members.
## Stretch
Our stretch goals include:
•	A responsive Gantt calendar that shows the generated track.
•	A responsive Gantt calendar that shows all available courses and fades out the courses the user discards.
•	Histogram summaries of job availability based on the language parsed from the Indeed API.
•	Histogram of average salaries for the different languages.
•	Course descriptions available as additional information as the user is selecting options in the form.
•	User demographics/summary/contact information sent to Code Fellows Admission staff.
## Functional Requirements
Functional requirements of our MVP include:
1.	A user can view all available courses published by Code Fellows.
2.	A user can create and save tracks generated on the front page.
3.	A user can delete saved tracks and their account.
4.	A user can log in and log out.
## Non-Functional Requirements
Non-Functional requirements of our MVP include:
1.	A user’s password will be protected using BCrypt and Spring’s Security.
2.	The tool will be scalable since as the user base grows the number of data pulls do not increase. The data will continue to feed from the Code Fellows published courses.
## Data Flow
 
![alt text](/assets/DataFlow.jpg)