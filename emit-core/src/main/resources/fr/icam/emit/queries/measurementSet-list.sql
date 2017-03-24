select   
  id,
  data,  
  experiment,
  achieved,
  observer
from measurementSets
where experiment=?; 