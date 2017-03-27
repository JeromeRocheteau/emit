select 
  id,
  started,
  stopped,
  measurand,
  environment
from experiments
where measurand = ?;
