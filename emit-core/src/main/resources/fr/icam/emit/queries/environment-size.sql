select count(uri) as size 
from environments
where deleted = 0;