select uri, name  
from instruments
where deleted = 0
limit ?,5;