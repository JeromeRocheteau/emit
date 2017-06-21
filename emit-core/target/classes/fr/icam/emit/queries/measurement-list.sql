select
  id,
  data,
  measurementset,
  measure,
  feature
from measurements
where measurementset =?; 
