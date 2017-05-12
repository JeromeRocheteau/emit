select count(url) as size 
from analysis
where deleted = 0;