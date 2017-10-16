SELECT 
  c.`uuid` AS uuid,
  c.`broker` AS broker  
FROM `clients` c 
LIMIT ?,?;