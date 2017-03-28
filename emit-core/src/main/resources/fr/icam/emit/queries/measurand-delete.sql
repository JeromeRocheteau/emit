/*DELETE FROM measurands WHERE process=?;*/

UPDATE measurands 
SET deleted = 1 
WHERE process=?;