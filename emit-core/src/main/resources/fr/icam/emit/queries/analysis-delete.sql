/*DELETE FROM environments WHERE uri=?;*/

UPDATE analysis 
SET deleted = 1 
WHERE url=?;