select uri, name  
from environments
where deleted = 0
limit ?,5;