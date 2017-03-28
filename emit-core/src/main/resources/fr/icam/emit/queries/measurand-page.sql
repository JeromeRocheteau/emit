select process, name, deleted  
from measurands
where deleted = 0
limit ?,5;