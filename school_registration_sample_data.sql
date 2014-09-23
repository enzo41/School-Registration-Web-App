-- faculty sample data 
insert into faculty (pk, faculty_number) values (1, 10);
insert into faculty (pk, faculty_number) values (2, 20);
insert into faculty (pk, faculty_number) values (3, 30);
insert into faculty (pk, faculty_number) values (4, 40);
insert into faculty (pk, faculty_number) values (5, 50);


-- students sample data
insert into students (pk, student_number) values (1, 11);
insert into students (pk, student_number) values (2, 22);
insert into students (pk, student_number) values (3, 33);
insert into students (pk, student_number) values (4, 44);
insert into students (pk, student_number) values (5, 55);


-- subject sample data
insert into subjects (pk, subject_id) values (1, 'MA1');
insert into subjects (pk, subject_id) values (2, 'MA2');
insert into subjects (pk, subject_id) values (3, 'MA3');
insert into subjects (pk, subject_id) values (4, 'EN1');
insert into subjects (pk, subject_id) values (5, 'EN2');

-- sections sample data
insert into sections (pk, section_number, fk_subject, fk_faculty, schedule) values (1, 'MA110MTAM', 1, 1, 'MTH AM10');
insert into sections (pk, section_number, fk_subject, fk_faculty, schedule) values (2, 'MA220TFPM', 2, 2, 'TF PM4');
insert into sections (pk, section_number, fk_subject, fk_faculty, schedule) values (3, 'MA330WSAM', 3, 3, 'WS AM1130');
insert into sections (pk, section_number, fk_subject, fk_faculty, schedule) values (4, 'EN1040MTAM', 4, 4, 'MTH AM10');
insert into sections (pk, section_number, fk_subject, fk_faculty, schedule) values (5, 'EN2050TFPM', 5, 5, 'TF PM1');



-- subject_prerequisites sample data
insert into subject_prerequisites (pk, fk_subject, fk_prerequisite) values (1, 3, 1);
insert into subject_prerequisites (pk, fk_subject, fk_prerequisite) values (2, 3, 2);
insert into subject_prerequisites (pk, fk_subject, fk_prerequisite) values (3, 5, 3);
