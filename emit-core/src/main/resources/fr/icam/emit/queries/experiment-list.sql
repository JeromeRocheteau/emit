select 
  id,
  started,
  stopped,
  measurand,
  observee
from experiments
where measurand = ?;
