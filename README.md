# Code 401 Midterm Project: Code Fellows Schedule App

## Team Members 
* James Dansie
* Jon Veach
* Sarah Fisher 

## Version
1.0.2

## Problem Domain
Our goal is to build a tool for Code Fellows prospective and current students to make course scheduling and educational planning easier.

## Deployment
https://code-fellows-planner.herokuapp.com/  

![Screen Shot](/assets/ScreenShotHomePage.png)
## Instructions to run the app on your computer
Install necessary dependencies from https://start.spring.io/;
1. GSON
2. SpringSecurity
3. SpringSecurity5
4. Spring Web
5. JPA
6. thymeleaf
7. devtools
8. PostGresSQL
Set up Database environment variables (URL for macs / URL, user, password for windows and linux). If you want to run tests, then set up test environment variables also.  
Run the main method in PlannerApplication.java  
Go to http://localhost:8080/  


### Contribute
No contribution guidelines at this point. 

## Build status

### Planning phase

### User Stories
* As a USER, I want a clean and easy to use User Interface.
* As a USER, I want to be able to save my generated education plans so that I can refer back to them later.
* As a USER, I would like to see the complete course schedule so I could plan out my time at Code Fellows accordingly.
* As a USER, I want to be able to save my course plans so that I can go back and edit them later on.
* As a USER, I want there to be descriptive messages if I do something wrong or cause any errors.
* As a STAFF MEMBER, I want the course information to be accurate to assist prospective users.
* As a STAFF MEMBER, I want this to be easily manageable and up dateable if course information changes.

### Wireframes
![Initial Wireframe](/assets/InitialWireframe.jpg)

### Communication Plan
* We will strive to create an open and welcoming environment where participation and contribution to the project and general community is a harassment-free experience for everyone, regardless of age, body size, disability, ethnicity, sex characteristics, gender identity and expression, level of experience, education, socio-economic status, nationality, personal appearance, race, religion, or sexual identity and orientation.
* We will use welcoming and inclusive language, be respectful of differing viewpoints and experiences, gracefully accept constructive criticism, focus on what is best for the team and the community, and show empathy towards others.
* We will not tolerate unwelcome sexual attention or advances, trolling, insulting/derogatory comments, and personal or political attacks, public or private harassment, publishing others’ private information, or other conduct which could reasonably be considered inappropriate in an academic and professional setting.
* We agree to work as a team to hold each other accountable to these responsibilities and take appropriate and fair corrective action in response to any instances of unacceptable behavior. We will not retaliate against those who have a differing opinion or those who hold others accountable.
* We will work as a team to support each individual reaching their full potential.
* If disagreements arise in the team we will make sure that everyone has a chance to state their opinion and be heard, not spoken over or dismissed, and discuss possible solutions as a team. If attempts to resolve the conflict is unsuccessful and a solution cannot be agreed upon we will escalate the discussion by asking one of the TAs or the instructor to mediate.
* If a team member is not adequately contributing we will raise the concern by speaking with them individually or as a team. We will focus on how we can help them contribute more. Are they struggling with something that is keeping them from moving forward? Are there other areas of the project they feel more comfortable contributing to? Consider the needs of the individual as well as the team.
* We will strive to make sure the team members communicate with each other regularly to keep the team running at the same pace and that everyone feels comfortable speaking up.
* We will listen to hear, not to reply, to ensure everyone's voice is heard. We will also make sure each team member agrees to the decisions made in the project before they are acted upon.
* We will create a safe environment where everyone feels comfortable speaking up by following our code of conduct and keeping each other accountable.
* Be patient and kind with each other.
* Suggestion: Start sentences with “I need...”
* There are no bad ideas.

### Specifics Guidelines:
* Code review occurs at each meetup with projector.
* Failure will happen and it’s okay! We do this thing together.
* If we’re moving too fast:
    Person who is slower - speak up! Fast coders can slow down.
    Person who is coding fast may need to take a break and help person catch up.

### After Hours Communication
Communication Formats After Hours and On Weekend:
* Slack - If there is any work done (coding, Trello, etc) the team member will send a summary of what was done so everyone is on the same page and we can keep the documentation updated.
* Trello - for file sharing, To Do lists.

### Communication in the code:
* Go way overkill on the comments.
* Group Working Hours
    9am – 6pm group time.
    8am- 9:15pm be aware group member(s) may need to communicate after group time
* Two daily stand-ups 9:00, 1pm
    Morning meeting - 9am start time – code review and prep for day
    After lunch – 1pm start time – review the morning, re-check in, and brainstorm as needed for afternoon.
* Check-in before leaving for the day – 5:30pm? How the morning and afternoon went

### Work Plan
* Work tasks will be sorted out at stand up, and reassigned throughout the day as needed.
* Tasks will be updated and tracked in trello.
* Daily summaries will be added to the read me.

### Git Process
* Daily PR in the morning.
* May PR more if needed (particularly for route setup).
* Master branch will only be working versions.
* Dev branch is where we can break things.

## Code of Conduct and Conflict Plan
### Process to resolve conflicts
* Everyone stops coding
* Return to communication ground rules.
* Try to understand the conflict with all 3 team members.
* Come to a mutual agreement without taking sides (keep user stories and scope in mind).
* Find a compromise between the two intersecting viewpoints
* Discuss things and find happy place
* When conflict is resolved, mandatory 10 minute break to relax.
* How to raise concerns to members not adequately contributing
* First step: Find out why the person is falling behind, without judgement.
* Put out the invitation to share - if something else outside of project is going on, preventing the person from being present, etc.
* During briefs in morning, check in with each other with how they’re doing with a task.
* Honesty from each of us about assigned tasks.
* Ask for help when needed.
* How and when to escalate conflict when attempts are unsuccessful:
* If person is unwilling to talk through or unwilling to improve. They may be going through the motions,   but not contributing, not willing to work on issue, and not willing to reach out.
Escalation will involve reaching out to Boss and HR (Nicholas, Michelle, and Ginger).

### Project Scope
#### MVP
* A ‘front page’ with the course scheduling form which will recommend a course based on the Code Fellows JSON data and the filters selected by the student. An option to save generated tracks with custom names.
* A ‘Create Account’ page for new users that will require an email, password, first and last name.
* Log in and log out functionality.
* A ‘Saved Tracks’ page where the user can view the tracks they have saved and delete them if desired.
* An ‘About Us’ page with information about the Float team members.

#### Stretch
* A responsive Gantt calendar that shows the generated track.
* A responsive Gantt calendar that shows all available courses and fades out the courses the user discards.
* Histogram summaries of job availability based on the language parsed from the Indeed API.
* Histogram of average salaries for the different languages.
* Course descriptions available as additional information as the user is selecting options in the form.
* User demographics/summary/contact information sent to Code Fellows Admission staff.

### Project Organization

#### Daily Team Workflow
<<<<<<< HEAD
Day 1: Built out basic front and back end. Got SQL hooked up. Setup Accounts. Got API Data into course class.
=======
Day 1: 
* Created initial Spring App
* Created Postgresql database named planner
    - created table named application_user;
    - created table named course;
    - created table named educational_plan;
* Created routes for / which returns index
    - This will soon utilize the course & educational_course tables
* Created routes for /signUp which allows a user to sign up
    - This utilizes the application_user table
* Created routes for /login & /logout which logs user in or out
    - This utilizes the application_user table
* Queried and saved API data to database
    - This utilizes the course table
    - Converted from JSON to course class
* Built basic HTML framework
    - Built forms for signup & login
* Initial deployment to heroku
>>>>>>> e4c55bdd1cb1aa5c1c477328d782d376d806f626
Day 2:
* Started CSS
    - Generalized CSS so it should apply on all pages
* Built out form on /index
    - Created thymeleaf logic to render courses in appropriate sections
    - Created SQL logic to return the course data that's after chosen course
    - Returned object in AJAX query
* Corrected route for app.js
    - Moved app.js to `static` folder
    - Included `/*.js` in WebSecurityConfig
* Created additional routes
    - /generateEdPlan which performs ajax call
    - /saveEdPlan which currently does nothing
* Created aboutus page
Day 3: 
* Saved course per user
    - User is able to view individual courses and delete them
    - viewable on `/myCourses` route
* Created logic to save courses to DB based on user
* Performed a ton of logic
    - Able to sort courses by Course
    - Able to sort courses by Fastest track
* Started about us page
Day 4: 
Day 5: Presentation Day!

## Code style
Code style 

## API Reference
Code Fellows

## Tests
Integration Tests for login, signup, and home page. Also checks API returns.


## Credits
* Java 401 Instructional Team

## License
MIT © Code Fellows
![Code Fellows Logo](https://i.imgur.com/7v5ASc8.png)
