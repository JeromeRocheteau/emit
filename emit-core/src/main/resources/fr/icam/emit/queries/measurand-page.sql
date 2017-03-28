select process,name  
from measurands
where deleted = 0
limit ?,5;