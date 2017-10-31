SELECT 
  t.`name` AS name,
  t.`prefix` AS prefix,
  t.`suffix` AS suffix,
  t.`leaf` AS leaf  
FROM `topics` t 
WHERE t.`prefix` IS NULL;