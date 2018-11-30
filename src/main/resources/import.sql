/*#Insert OffenceCodes*/
INSERT INTO
  offence_code (id, offender_type, level, penalty_amount, number_label, description, offence_repetition_considered)
VALUES
  (1, 'Driver', 1, 50, 1, 'በአደራ የተቀበለውን መልዕክት፣ ፖስታ፣ ጋዜጣ፣ ወዘተ... ለሚመለከተው አካል ያላስረከበ', 1),
  (2, 'Driver', 1, 80, 1, 'ተቀያሪ ጐማና ተፈላጊ መፍቻ ሳይዝ ያሽከረከረ', 1),
  (3, 'Driver', 1, 100, 1, 'የሹፌሩን እይታ የሚከላክሉ መጋረጃዎች፣ ተለጣፊ ላስቲኮች የለጠፈ', 1),
  (4, 'Driver', 1, 100, 2, 'የተሽከርካሪውን የውስጥና የውጭ ንፅሕና በሚገባ ያልጠበቀ', 1),
  (5, 'Driver', 1, 100, 3, 'በሥምሪት መርሀ-ግብሩ መሠረት የጉዞ እንቅስቃሴውን ተግባራዊ ያላደረገ', 1),
  (6, 'Driver', 1, 100, 4, 'የተበላሸ ተሽከርካሪ ወይም በቁልፍ የማይነሣ ተሽከርካሪ ለሥምሪት ያቀረበ እንዲሁም ሆን ብሎ
ተሽከርካሪን ለአገልግሎት ሳይከፍት በመናኸሪያ ውስጥ ከሁለት ሰዓት በላይ አቁሞ የሄደ', 1),
  (7, 'Driver', 1, 100, 5, 'ተሣፋሪን ያለአግባብ የጐተተ፣ ያንገላታና ያንጓጠጠ', 1),
  (8, 'Driver', 1, 100, 6, 'በተሽከርካሪዎች መሀከል ያለውን ርቀት ጠብቆ ያላሽከረከረ', 1),
  (9, 'Driver', 1, 100, 7, 'የቀስት አቅጣጫን ጠብቆ ያላሽከረከረ', 1),
  (10, 'Driver', 1, 100, 8, 'በምሽት ባልተሟላ መብራት ያሽከረከረ', 1);

/*#Insert OffenderEntities*/
INSERT INTO
  offender_entity(id, entity_type, created_at, created_by, dml_flag, updated_at, updated_by, version)
VALUES
  (1, 'DRIVER', NOW(), 'Ferid', 0, NULL, NULL, NULL),
  (2, 'DRIVER', NOW(), 'Ferid', 0, NULL, NULL ,NULL);

/*#Insert Persons*/
INSERT INTO
  person(id, first_name, middle_name, last_name, gender)
VALUES
  (1, 'Ferid', 'Zuber', 'Oumer', 'MALE'),
  (2, 'Oreen', 'Zuber', 'Oumer', 'MALE');

/*#Insert Drivers*/
INSERT INTO
  driver(id, license_no, license_type)
VALUES
  (1, 087525, 'AUTO'),
  (2, 087526, 'AUTO');

/*Insert Offences*/
INSERT INTO
  offence(id, created_at, created_by, dml_flag, updated_at, updated_by, version, description, dispatch_no, drivers_license_taken, offence_date, offence_time, penalty_amount, place, reporting_location, status, vehicle_bolo_taken, vehicle_libre_taken, vehicle_plate_taken, offender_id, vehicle_id)
VALUES
  (1, NOW(), 'Ferid', 0, NULL, NULL, NULL, '', NULL, 1, '2018-6-29', '4:20:20', NULL, 'Nifas Silk Lafto', NULL, 'PENDING', NULL, NULL, NULL, 1, NULL),
  (6, NOW(), 'Ferid', 0, NULL, NULL, NULL, '', NULL, 1, '2017-9-29', '4:20:20', NULL, 'Nifas Silk Lafto', NULL, 'PENDING', NULL, NULL, NULL, 1, NULL),
  (2, NOW(), 'Ferid', 0, NULL, NULL, NULL, '', NULL, 1, '2018-9-29', '3:20:20', NULL, 'Nifas Silk Lafto', NULL, 'PENDING', NULL, NULL, NULL, 1, NULL),
  (3, NOW(), 'Ferid', 0, NULL, NULL, NULL, '', NULL, 1, '2018-10-29', '3:20:20', NULL, 'Nifas Silk Lafto', NULL, 'PENDING', NULL, NULL, NULL, 1, NULL),
  (4, NOW(), 'Ferid', 0, NULL, NULL, NULL, '', NULL, 1, '2018-11-29', '3:20:20', NULL, 'Nifas Silk Lafto', NULL, 'PENDING', NULL, NULL, NULL, 1, NULL),
  (5, NOW(), 'Ferid', 0, NULL, NULL, NULL, '', NULL, 1, '2018-12-29', '3:20:20', NULL, 'Nifas Silk Lafto', NULL, 'PENDING', NULL, NULL, NULL, 1, NULL);

/*Insert offence_offence_codes
Note: on different methods of penalty calculation currently i'm using method 1 but the one in rule book probably meant method 2
Question: How is the gap between offence 6 and 2 treated? can offence 2 see offence 6 for calculation check LocalDate.minus(). It should not be visible
offence with id of 6 has happened way before 4 of the others but is only visible for offence with id of 1
calculation for the offences from 1 to 5 which are within the range of one year.
if penalty is set to be
  maximum repetition * maximum penalty amount
  4(offence_code_id 2) * 100(which is that of offence code 3)
  400
else if
  maximum of (repetition * penalty amount)
  max of(3*50, 4*80, 2*100)
  320
else if
  punished for all
  3*50 + 4*80 + 2*100
  670*/
INSERT INTO
  offence_offence_codes(offence_id, offence_codes_id)
VALUES
  (5,1),
  (4,1),
  (2,1),
  (5,2),
  (4,2),
  (2,2),
  (1,2),
  (5,3),
  (3,3),
  (6,4);
