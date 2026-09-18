insert into todo(id,username,description,target_date,is_done)
values(10001,'username','Learn JPA',CURRENT_DATE,false);
insert into todo(id,username,description,target_date,is_done)
values(10002,'username','Learn AWS',CURRENT_DATE,false);
insert into todo(id,username,description,target_date,is_done)
values(10003,'username','Learn SPRING',CURRENT_DATE,false);

-- ==============================================================================================
-- SAMPLE / PLACEHOLDER DATA for the Den Haag preschool research map.
-- Names, ratings, review counts, prices and diversity figures below are illustrative only,
-- they are NOT real institutions, real prices, or real published statistics. Replace with data from:
--   - LRK (Landelijk Register Kinderopvang, landelijkregisterkinderopvang.nl) for real,
--     officially registered preschool/childcare locations, addresses and coordinates.
--   - Gemeente Den Haag open data portal / CBS StatLine for aggregate, neighbourhood-level
--     statistics (never per-child data).
--   - Each provider's own website for real hourly rates; the Dutch childcare benefit
--     (kinderopvangtoeslag, belastingdienst.nl) reimburses a large share of the cost for
--     eligible families, so the effective price is usually much lower than the sticker rate.
-- internationalProgram/languages describe the medium of instruction and curriculum on offer
-- (e.g. bilingual Dutch/English, IB) - a normal, non-discriminatory school-choice factor,
-- kept deliberately distinct from diversity_index_percent (which is ethnicity-related and
-- research-only, see Preschool.java).
-- ==============================================================================================
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20001,'Kinderdagverblijf De Zonnebloem (sample)','Schilderswijk','Hoefkade 100, Den Haag',52.0740,4.3151,4.1,42,'0-4 jaar',68.5,2023,8.95,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20002,'Peuterspeelzaal Transvaal (sample)','Transvaal','Hoefkade 250, Den Haag',52.0680,4.3200,3.9,27,'2-4 jaar',71.2,2023,8.75,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20003,'Kinderopvang Laakhaven (sample)','Laak','Waldorpstraat 15, Den Haag',52.0600,4.3150,4.3,58,'0-4 jaar',55.0,2023,9.40,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20004,'KDV Zeeheldenkwartier (sample)','Zeeheldenkwartier','Zoutmanstraat 40, Den Haag',52.0850,4.2950,4.6,73,'0-4 jaar',24.0,2023,11.90,true,'Dutch, English');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20005,'Peutercentrum Segbroek (sample)','Segbroek','Fahrenheitstraat 100, Den Haag',52.0800,4.2850,4.4,36,'2-4 jaar',18.5,2023,9.95,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20006,'Kinderdagverblijf Bezuidenhout (sample)','Bezuidenhout','Juliana van Stolberglaan 20, Den Haag',52.0850,4.3300,4.5,64,'0-4 jaar',30.0,2023,10.50,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20007,'Kinderopvang Benoordenhout (sample)','Benoordenhout','Reinkenstraat 5, Den Haag',52.0950,4.3200,4.8,91,'0-4 jaar',15.0,2023,13.50,true,'Dutch, English, German');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20008,'Peuterspeelzaal Loosduinen (sample)','Loosduinen','Loosduinse Hoofdstraat 200, Den Haag',52.0550,4.2450,4.2,48,'2-4 jaar',33.0,2023,9.10,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20009,'Kinderdagverblijf Escamp (sample)','Escamp','Dedemsvaartweg 300, Den Haag',52.0400,4.2900,3.8,31,'0-4 jaar',60.0,2023,8.60,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20010,'Kinderopvang Ypenburg (sample)','Ypenburg','Landzichtlaan 10, Den Haag',52.0500,4.3700,4.7,55,'0-4 jaar',22.0,2023,12.25,true,'Dutch, English');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20011,'Peutercentrum Leidschenveen (sample)','Leidschenveen','Leidschenveen Centrum 8, Den Haag',52.0700,4.4000,4.5,40,'2-4 jaar',35.0,2023,10.10,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20012,'Kinderdagverblijf Scheveningen (sample)','Scheveningen','Keizerstraat 60, Den Haag',52.1100,4.2900,4.0,66,'0-4 jaar',NULL,NULL,9.75,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20013,'Peuterspeelzaal Moerwijk (sample)','Moerwijk','Melis Stokelaan 900, Den Haag',52.0500,4.3000,3.7,22,'2-4 jaar',65.0,2023,8.50,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20014,'Kinderopvang Mariahoeve (sample)','Mariahoeve','Laan van Wateringse Veld 5, Den Haag',52.0950,4.3550,4.3,38,'0-4 jaar',28.0,2023,10.30,false,'Dutch');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20015,'Kinderdagverblijf Meerdervoort International (sample)','Regentessekwartier','Laan van Meerdervoort 450, Den Haag',52.0815,4.2920,4.6,50,'0-4 jaar',26.0,2023,12.75,true,'Dutch, English');
insert into preschool(id,name,neighbourhood,address,latitude,longitude,rating,review_count,age_range,diversity_index_percent,diversity_source_year,price_per_hour_euro,international_program,languages)
values(20016,'Peutercentrum De Wereldburger (sample)','Segbroek','Laan van Meerdervoort 620, Den Haag',52.0806,4.2810,4.4,33,'2-4 jaar',31.0,2023,11.40,true,'Dutch, English, French');