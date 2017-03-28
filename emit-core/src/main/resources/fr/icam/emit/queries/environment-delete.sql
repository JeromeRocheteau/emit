/*DELETE FROM environments WHERE uri=?;*/

UPDATE environments 
SET deleted = 1 
WHERE uri=?;