SELECT 
  c.`id` AS id, 
  c.`atomic` AS atomic, 
  c.`name` AS name, 
  c.`category` AS category, 
  c.`issued` AS issued, 
  c.`user` AS user,
  t.`id` AS testId, 
  t.`name` AS testName, 
  t.`atomic` AS testAtomic, 
  t.`category` AS testCategory, 
  t.`issued` AS testIssued, 
  t.`user` AS testUser,
  s.`id` AS successId, 
  s.`name` AS successName, 
  s.`atomic` AS successAtomic, 
  s.`category` AS successCategory, 
  s.`issued` AS successIssued, 
  s.`user` AS successUser,
  f.`id` AS failureId, 
  f.`name` AS failureName,
  f.`atomic` AS failureAtomic, 
  f.`category` AS failureCategory, 
  f.`issued` AS failureIssued, 
  f.`user` AS failureUser
FROM `guard_callbacks` gc
INNER JOIN `callbacks` c ON c.`id` = gc.`id`
INNER JOIN `callbacks` t ON t.`id` = gc.`test`
INNER JOIN `callbacks` s ON s.`id` = gc.`success`
LEFT JOIN `callbacks` f ON f.`id` = gc.`failure`
WHERE gc.`id` = ?;