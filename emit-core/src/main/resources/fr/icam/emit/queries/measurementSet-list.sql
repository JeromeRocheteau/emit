select   
  id,
  data,  
  experiment,
  achieved,
  instrument
from measurementsets
where experiment=?;