DROP TRIGGER add_completed_course;
DELIMITER $$
CREATE TRIGGER add_completed_course
    AFTER INSERT
    ON DatabaseApplication02.students_courses FOR EACH ROW
BEGIN
    DECLARE semester_isactive int;

        SET semester_isactive = (SELECT sem.active FROM DatabaseApplication02.semester sem INNER JOIN
			DatabaseApplication02.course_registration cr
			WHERE sem.id = cr.semester_id
            AND cr.id = NEW.course_registration_id);

        IF semester_isactive = 0 THEN
			INSERT INTO DatabaseApplication02.students_courses_completed(course_registration_id, student_id, final_grade)
            VALUES(NEW.course_registration_id, NEW.student_id, '');
END IF;
END$$

DELIMITER ;






DROP TRIGGER manage_completed_semester_course;
DELIMITER //
CREATE TRIGGER manage_completed_semester_course
    AFTER UPDATE
    ON DatabaseApplication02.semester FOR EACH ROW
BEGIN
    DECLARE creg_id bigint;
    DECLARE sdnt_id bigint;
    DECLARE reg_id bigint;
    DECLARE done int default 0;
    DECLARE cur1 CURSOR FOR SELECT id FROM DatabaseApplication02.course_registration cr
                            WHERE cr.semester_id = NEW.id;
    DECLARE cur2 CURSOR FOR SELECT course_registration_id, student_id FROM DatabaseApplication02.students_courses;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    data_loop1:loop

		OPEN cur1;
    FETCH cur1 INTO creg_id;

    data_loop2:loop

			OPEN cur2;
    FETCH cur2 INTO reg_id,sdnt_id;

    IF NEW.active = 0 THEN
				IF reg_id = creg_id THEN
					INSERT INTO DatabaseApplication02.students_courses_completed(course_registration_id, student_id, final_grade)
					VALUES(reg_id, sdnt_id, '');
END IF;
END IF;

            IF NEW.active = 1 THEN
				IF reg_id = creg_id THEN
DELETE FROM DatabaseApplication02.students_courses_completed scc WHERE scc.course_registration_id = reg_id;
END IF;
END IF;

            IF done THEN
				LEAVE data_loop2;
END IF;
END LOOP;
CLOSE cur2;

IF done THEN
			LEAVE data_loop1;
END IF;
END LOOP;
CLOSE cur1;
END//















INSERT INTO roles (id, name)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, name)
VALUES (2, 'STUDENT');

-- INSERT admin account with username: 'admin' and password 'qwerty123'
INSERT INTO users (id, username, password)
VALUES (1, 'admin', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO users (id, username, password)
VALUES (1, 'testStudent', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2);

INSERT INTO DatabaseApplication02.courses (id, description, name, section, department_id, credit_hours)
VALUES (21, '', 'College  Algebra', '110', 4, 3);
INSERT INTO DatabaseApplication02.courses (id, description, name, section, department_id, credit_hours)
VALUES (22, '', 'Elementary  Statistics', '105', 4, 3);
INSERT INTO DatabaseApplication02.courses (id, description, name, section, department_id, credit_hours)
VALUES (23, '', 'Precalculus I', '130', 4, 3);
INSERT INTO DatabaseApplication02.courses (id, description, name, section, department_id, credit_hours)
VALUES (24, '', 'Precalculus II', '140', 4, 3);
INSERT INTO DatabaseApplication02.courses (id, description, name, section, department_id, credit_hours)
VALUES (25, '', 'Calculus I', '230', 4, 3);
INSERT INTO DatabaseApplication02.courses (id, description, name, section, department_id, credit_hours)
VALUES (26, '', 'Calculus II', '240', 4, 3);
INSERT INTO DatabaseApplication02.courses (id, description, name, section, department_id, credit_hours)
VALUES (27, '', 'Differential  Equations', '325', 4, 3);

INSERT INTO DatabaseApplication02.instructors (id, department, email, first_name, last_name, office)
VALUES (2, 'Business', 'sandersjoe@gmail.com', 'Joe', 'Sanders', 'L102');
INSERT INTO DatabaseApplication02.instructors (id, department, email, first_name, last_name, office)
VALUES (3, 'Art', 'harrislisa@gmail.com', 'Lisa', 'Harris', 'C67');
INSERT INTO DatabaseApplication02.instructors (id, department, email, first_name, last_name, office)
VALUES (4, 'Mathmatics', 'richard122@gmail.com', 'Richard', 'Ellis', 'P200');
INSERT INTO DatabaseApplication02.instructors (id, department, email, first_name, last_name, office)
VALUES (5, 'History', 'carolbaskins@gmail.com', 'Carole', 'Baskins', 'P80');
INSERT INTO DatabaseApplication02.instructors (id, department, email, first_name, last_name, office)
VALUES (6, 'Political Science', 'drakegoodson@gmail.com', 'Drake', 'Goodson', 'L170');
INSERT INTO DatabaseApplication02.instructors (id, department, email, first_name, last_name, office)
VALUES (7, 'Computer Science', 'nick1009@gmail.com', 'Nick', 'Weeks', 'C40');
INSERT INTO DatabaseApplication02.instructors (id, department, email, first_name, last_name, office)
VALUES (8, 'Education', 'norrisjones99@gmail.com', 'Jones', 'Norris', 'P15');


INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('3', '1', '2021-12-15', '011:20:00', '35', '0', '0', '2021-08-12', '10:30:00', '1', '3', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('4', '1', '2021-12-15', '12:50:00', '25', '1', '0', '2021-08-12', '12:00:00', '0', '9', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('5', '2', '2021-12-15', '14:10:00', '30', '0', '0', '2021-08-12', '13:00:00', '1', '9', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('6', '3', '2021-12-15', '15:30:00', '20', '1', '0', '2021-08-12', '14:20:00', '0', '9', '3');

INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('7', '8', '2021-12-15', '10:10:00', '30', '0', '0', '2021-08-12', '09:00:00', '1', '2', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('8', '9', '2021-12-15', '11:20:00', '35', '1', '0', '2021-08-12', '10:30:00', '0', '2', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('9', '10', '2021-12-15', '12:50:00', '35', '1', '0', '2021-08-12', '12:00:00', '1', '2', '3');

INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('10', '13', '2021-12-15', '09:45:00', '30', '0', '0', '2021-08-12', '08:50:00', '1', '10', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('11', '14', '2021-12-15', '11:20:00', '30', '1', '0', '2021-08-12', '10:30:00', '0', '10', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('12', '15', '2021-12-15', '12:50:00', '25', '1', '0', '2021-08-12', '12:00:00', '0', '10', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('13', '16', '2021-12-15', '15:00:00', '30', '0', '0', '2021-08-12', '14:00:00', '1', '10', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('14', '17', '2021-12-15', '15:00:00', '30', '1', '0', '2021-08-12', '14:20:00', '0', '1', '3');


INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('15', '21', '2021-12-15', '10:20:00', '35', '0', '0', '2021-08-12', '9:00:00', '1', '4', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('16', '22', '2021-12-15', '12:50:00', '25', '1', '0', '2021-08-12', '12:00:00', '0', '4', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('17', '23', '2021-12-15', '14:00:00', '30', '0', '0', '2021-08-12', '13:00:00', '1', '4', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('18', '23', '2021-12-15', '9:20:00', '30', '1', '0', '2021-08-12', '8:30:00', '0', '4', '3');


INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('19', '29', '2021-12-15', '11:50:00', '30', '1', '0', '2021-08-12', '11:00:00', '0', '7', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('20', '30', '2021-12-15', '10:30:00', '25', '1', '0', '2021-08-12', '11:40:00', '1', '7', '3');

INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('21', '28', '2021-12-15', '9:20:00', '30', '1', '0', '2021-08-12', '8:30:00', '0', '7', '3');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('22', '28', '2021-12-15', '10:00:00', '30', '0', '0', '2021-08-12', '9:00:00', '1', '7', '3');

INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('23', '1', '2021-12-15', '011:20:00', '35', '0', '0', '2021-08-12', '10:30:00', '1', '3', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('24', '1', '2021-12-15', '12:50:00', '25', '1', '0', '2021-08-12', '12:00:00', '0', '9', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('25', '2', '2021-12-15', '14:10:00', '30', '0', '0', '2021-08-12', '13:00:00', '1', '9', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('26', '3', '2021-12-15', '15:30:00', '20', '1', '0', '2021-08-12', '14:20:00', '0', '9', '1');

INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('27', '8', '2021-12-15', '10:10:00', '30', '0', '0', '2021-08-12', '09:00:00', '1', '2', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('28', '9', '2021-12-15', '11:20:00', '35', '1', '0', '2021-08-12', '10:30:00', '0', '2', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('29', '10', '2021-12-15', '12:50:00', '35', '1', '0', '2021-08-12', '12:00:00', '1', '2', '1');

INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('30', '13', '2021-12-15', '09:45:00', '30', '0', '0', '2021-08-12', '08:50:00', '1', '10', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('31', '14', '2021-12-15', '11:20:00', '30', '1', '0', '2021-08-12', '10:30:00', '0', '10', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('32', '15', '2021-12-15', '12:50:00', '25', '1', '0', '2021-08-12', '12:00:00', '0', '10', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('33', '16', '2021-12-15', '15:00:00', '30', '0', '0', '2021-08-12', '14:00:00', '1', '10', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('34', '17', '2021-12-15', '15:00:00', '30', '1', '0', '2021-08-12', '14:20:00', '0', '1', '1');


INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('35', '21', '2021-12-15', '10:20:00', '35', '0', '0', '2021-08-12', '9:00:00', '1', '4', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('36', '22', '2021-12-15', '12:50:00', '25', '1', '0', '2021-08-12', '12:00:00', '0', '4', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('37', '23', '2021-12-15', '14:00:00', '30', '0', '0', '2021-08-12', '13:00:00', '1', '4', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('38', '23', '2021-12-15', '9:20:00', '30', '1', '0', '2021-08-12', '8:30:00', '0', '4', '1');


INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('39', '29', '2021-12-15', '11:50:00', '30', '1', '0', '2021-08-12', '11:00:00', '0', '7', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('40', '30', '2021-12-15', '10:30:00', '25', '1', '0', '2021-08-12', '11:40:00', '1', '7', '1');

INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('41', '28', '2021-12-15', '9:20:00', '30', '1', '0', '2021-08-12', '8:30:00', '0', '7', '1');
INSERT INTO DatabaseApplication02.course_registration (id, course_id, end_date, end_time, max_seats, monday_wednesday_friday, seats_filled, start_date, start_time, tuesday_thursday, instructor_id, semester_id)
VALUES ('42', '28', '2021-12-15', '10:00:00', '30', '0', '0', '2021-08-12', '9:00:00', '1', '7', '1');

