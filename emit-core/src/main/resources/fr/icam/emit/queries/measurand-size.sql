select count(process) as size 
from measurands
where deleted = 0;