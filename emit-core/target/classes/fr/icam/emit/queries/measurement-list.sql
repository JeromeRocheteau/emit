select
  id,
  data,
  measurementset,
  measure
from measurements
where measurementset =?; 
