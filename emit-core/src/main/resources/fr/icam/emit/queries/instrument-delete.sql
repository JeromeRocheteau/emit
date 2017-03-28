/*DELETE FROM instruments WHERE uri=?;*/

UPDATE instruments 
SET deleted = 1 
WHERE uri=?;