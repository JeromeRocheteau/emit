select   
  id,
  data,  
  experiment,
  achieved,
  instrument
from measurementSets
where experiment=?; 