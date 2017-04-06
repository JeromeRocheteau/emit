select 
  id,
  started,
  stopped,
  measurand,
  environment
from experiments
where stopped is null
order by id desc
limit 0,1;